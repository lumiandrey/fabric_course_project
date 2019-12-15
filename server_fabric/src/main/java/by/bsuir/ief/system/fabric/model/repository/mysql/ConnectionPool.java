package by.bsuir.ief.system.fabric.model.repository.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private static volatile ConnectionPool ourInstance = null;
    /**
     * будет хранить строку подключения к бд
     */
    private static String url;
    private static String login;
    private static String password;
    private static int initConnCnt;

    private Vector<Connection> availableConns = new Vector<>();
    private Vector<Connection> usedConns = new Vector<>();

    private ConnectionPool() {
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.addElement(getConnection());
        }
    }

    public static ConnectionPool getInstance() {
        if (ourInstance == null)
            synchronized (ConnectionPool.class) {
                if (ourInstance == null)
                    ourInstance = new ConnectionPool();
            }
        return ourInstance;
    }

    public static void initPool(final String HostSQL, final String nameDB, final String login, final String password, String driver, int initConnCnt) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = HostSQL + nameDB + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        ConnectionPool.login = login;
        ConnectionPool.password = password;
        ConnectionPool.initConnCnt = initConnCnt;
    }

    /**
     * Создает новое подключение
     *
     * @return нопое подключение
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Функция забирает из availableConns очередной Connection и добавляет его в usedConns,
     * затем возвращает это соединение, тем самым он становится используемым
     *
     * @return соединение, которое помещенно как используемое
     * @throws SQLException
     */
    public synchronized Connection retrieve() throws SQLException {
        Connection newConn = null;
        if (availableConns.size() == 0) {
            newConn = getConnection();
        } else {
            newConn = availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);

        System.out.println("connecton is get" + newConn.toString());
        return newConn;
    }

    /**
     * Когда соединение становится не нужным, то выполняется эта функция
     *
     * @param c соединение, которое было использованно и больше не надо.
     * @throws NullPointerException
     */
    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            System.out.println("connecton is back" + c.toString());
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns");
            }
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }


}
