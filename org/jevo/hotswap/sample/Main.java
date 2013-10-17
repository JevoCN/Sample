package org.jevo.hotswap.sample;

import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 11:47:56
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    static ClassLoader cl;
    static Object server;
    static Class hotClazz = null;

    public static void loadNewVersionOfServer() throws Exception {
        synchronized (Main.class) {
            if (cl == null)
                cl = new MyClassLoader(new URL("file://D:/Project/test/out/"));
        }
        hotClazz = cl.loadClass("org.jevo.hotswap.sample.DyService");
        server = hotClazz.newInstance();
    }

    public static void test() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loadNewVersionOfServer();
        while (true) {
            System.out.println("Enter DOBUS, RELOAD, or QUIT: ");
            String cmdRead = br.readLine();
            String cmd = cmdRead.toUpperCase();
            if (cmd.equals("QUIT")) {
                return;
            } else if (cmd.equals("DOBUS")) {
                Method m = hotClazz.getMethod("doBusiness");
                System.out.println(m.invoke(server, null)); //这里使用反射机制来执行事务。
            } else if (cmd.equals("RELOAD")) {
                loadNewVersionOfServer();
            }
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
