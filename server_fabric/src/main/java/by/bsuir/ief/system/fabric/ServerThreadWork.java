package by.bsuir.ief.system.fabric;

import by.bsuir.ief.system.fabric.controller.RunServerSettingController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThreadWork implements Runnable {

    private Thread thread;
    private ServerSocket serverSocket;

    private static RunServerSettingController runServerSettingController = null;

    public synchronized static long getCountUser() {
        return countUser;
    }

    public synchronized static void setCountUser(long countUser) {
        ServerThreadWork.countUser = countUser;
    }

    private static long countUser = 0;

    private static int NUMBER_PORT = 5025;

    private static String ipAddres = "127.0.0.1";

    private static InetAddress inetAddress = null;

    public static int getNumberPort() {
        return NUMBER_PORT;
    }

    public static void setNumberPort(int numberPort) {
        NUMBER_PORT = numberPort;
    }

    private ArrayList<ClientThreadWork> clientTheadWorkList;

    public ServerThreadWork(ThreadGroup threadGroup) {
        System.out.println("Сервер начал работу...");

        thread = new Thread(threadGroup,this,"server_support");
        try
        {
            if(inetAddress != null){
                ipAddres = inetAddress.getHostAddress();
                serverSocket = new ServerSocket(NUMBER_PORT,0,inetAddress);
            }
            else
                serverSocket = new ServerSocket(NUMBER_PORT);

            clientTheadWorkList = new ArrayList<>();
        }
        catch (IOException e)
        {
            System.out.println("Ошибка прослушивания порта");
        }
        thread.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        while (true)
        {
            try {

                final Socket clientSocket = serverSocket.accept();
                System.out.println("Соединение установлено...");
                Runnable r = new ClientThreadWork(clientSocket, runServerSettingController);
                Thread t = new Thread(r);
                t.start();

            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода при создании соединения.");
            }
        }
    }

    public Thread getThread() {
        return thread;
    }

    public static RunServerSettingController getRunServerSettingController() {
        return runServerSettingController;
    }

    public static void setRunServerSettingController(RunServerSettingController runServerSettingController) {
        ServerThreadWork.runServerSettingController = runServerSettingController;
    }

    public String getIpAdressServer ()
    {
        return ipAddres;
    }

    public static String getIpAddres() {
        return ipAddres;
    }

    public static void setIpAddres(String ipAddres) {
        ServerThreadWork.ipAddres = ipAddres;
    }

    public static InetAddress getInetAddress() {
        return inetAddress;
    }

    public static void setInetAddress(InetAddress inetAddress) {
        ServerThreadWork.inetAddress = inetAddress;
    }
}
