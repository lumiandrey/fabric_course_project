package by.bsuir.ief.system.fabric.model.storage;

import org.jetbrains.annotations.NotNull;
import connection.client_support.Request;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ConnectToServer {

    private static String ipAdress = "127.0.0.1";
    private static int PORT = 5025;
    private static String token = "";

    private static InetAddress inetAddress = null;

    public static String getIpAdress() {
        return ipAdress;
    }

    public static void setIpAdress(String ipAdress) {
        ConnectToServer.ipAdress = ipAdress;
    }

    public static int getPORT() {
        return PORT;
    }

    public static void setPORT(int PORT) {
        ConnectToServer.PORT = PORT;
    }

    public static InetAddress getInetAddress() {
        return inetAddress;
    }

    public static void setInetAddress(InetAddress inetAddress) {
        ConnectToServer.inetAddress = inetAddress;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        ConnectToServer.token = token;
    }

    @NotNull
    public static Request.Builder setConnectData(@NotNull final Request.Builder builder) throws UnknownHostException {

        return builder.url(ipAdress, PORT)
                .addHeader("X-Auth", token);
    }
}
