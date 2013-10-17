package org.jevo.hotswap.sample.instrum;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:42:47
 * To change this template use File | Settings | File Templates.
 */

public class Reloader extends Thread{

    private final Instrumentation inst;

    public Reloader(Instrumentation inst) {
        this.inst = inst;
            this.setDaemon(true);
    }

    public void run() {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter DOBUS, RELOAD, or QUIT: ");
            String cmdRead = br.readLine();
            String cmd = cmdRead.toUpperCase();
            if (cmd.equals("QUIT")) {
                System.exit(-1);
            } else if (cmd.equals("DOBUS")) {
//                Method m = hotClazz.getMethod("doBusiness");
//                System.out.println(m.invoke(server, null)); //这里使用反射机制来执行事务。
            } else if (cmd.equals("RELOAD")) {
                loader();
            }
        }
    }

    public void loader() {
        try {
            ClassDefinition[] cd = new ClassDefinition[1];
            Class[] classes = inst.getAllLoadedClasses();
            for (Class cls : classes) {
                if (cls.getClassLoader() == null || !cls.getClassLoader().getClass().getName().equals("sun.misc.Launcher$AppClassLoader"))
                    continue;
                String name = cls.getName().replaceAll("\\.", "/");
                cd[0] = new ClassDefinition(cls, loadClassBytes(cls, name + ".class"));
                inst.redefineClasses(cd);  //使用提供的类文件重定义提供的类集。
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private byte[] loadClassBytes(Class cls, String clsname) throws Exception {
        System.out.println(clsname + ":" + cls);
        InputStream is = cls.getClassLoader().getSystemClassLoader().getResourceAsStream(clsname);
        if (is == null) return null;
        byte[] bt = new byte[is.available()];
        is.read(bt);
        is.close();
        return bt;
    }
}
