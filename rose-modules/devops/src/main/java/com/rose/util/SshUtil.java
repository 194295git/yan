package com.rose.util;

/**
 * @author rose
 * @create 2022/11/5
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * @author zhaosongbin
 */
public class SshUtil {

    private final static String host = "192.168.56.20";
    private final static int port = 22;
    private final static String username = "root";
    private final static String password = "vagrant";
    private final static String directory = "/home/";
    private static SshUtil ftp = new SshUtil();
    private static Connection con = new Connection(host, port);

    /**
     * 执行传来的linux指令
     *
     * @param command
     * @return
     */
    public static List<String> execCom(String command) {
        Session session = ftp.session();
        BufferedReader br = null;
        List<String> msgList = new ArrayList<String>();
        try {
            session.requestPTY("vt100", 80, 24, 640, 480, null);
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                msgList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();
        con.close();
        return msgList;
    }

    public Session session() {
        Session session = null;
        try {
            con.connect();
            // 远程服务器的用户名密码
            con.authenticateWithPassword(username, password);
            session = con.openSession();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return session;
    }

    public static void main(String[] args) {
        execCom("ll");

    }

}
