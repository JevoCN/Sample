package org.jevo.hotswap.sample.hot;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.io.InputStream;
import java.util.TimerTask;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:53:21
 * To change this template use File | Settings | File Templates.
 */

public class ReloadTask extends TimerTask {

    private Instrumentation inst;

    protected ReloadTask(Instrumentation inst) {
        this.inst = inst;
    }

    @Override
    public void run() {
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

