package org.jevo.hotswap.sample.instru;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassReader;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassDefinition;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;

import net.sf.cglib.asm.ClassAdapter;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:17:47
 * To change this template use File | Settings | File Templates.
 */

public class Redefiner extends Thread {
    private final Instrumentation inst;

    public Redefiner(Instrumentation inst) {
        this.inst = inst;
        this.setDaemon(true);
    }

    public void run() {
        //����Ӧ������ServerSocket����ȡ�ӿ���̨��¼�����������
        //�����Ե�����Ϊ�˼򵥽���ʱ��ĳָ�����ļ��л�ȡ��
        HashMap<String, byte[]> map = new HashMap<String, byte[]>();
        long prevLastModified = 0L;
        String lastCMD = "";
        while (true) {
            try {
                Thread.sleep(1000);
                File f = new File("d:/a.txt");
                long lm = f.lastModified();
                if (prevLastModified == lm) continue;
                prevLastModified = lm;
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();//���ļ��ж�ȡ����
                br.close();
                String[] cols = line.split(":");
                if (cols.length < 3) continue;
                String CMD = cols[0];
                if (CMD.equals(lastCMD)) continue;
                lastCMD = CMD;
                String className = cols[1];
                String[] methods = cols[2].split(",");
                if (CMD.equals("STOP")) break;//�˳�,Ӧ�ü�Ȩ����֤
                if (CMD.equals("DEBUG")) {
                    if (!map.containsKey(className)) {
                        map.put(className, getOriginClass(className));//����ԭʼ��class
                    }
                    byte[] buf = getWrappedClass(className, methods);
                    //��װ���class�Ƿ�Ҫ�����Լ����Ű졣������Ҫ�ռ䣬������ÿ��������Ҫ�������ʱ�ռ䣬�Լ����ݵ���Ƶ����������
                    Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
                    ClassDefinition[] definitions = new ClassDefinition[]{new ClassDefinition(clazz, buf)};
                    inst.redefineClasses(definitions);
                    System.out.println("redefine to debug..");
                } else if (CMD.equals("RESET")) {
                    byte[] buf = map.get(className);
                    if (buf == null) continue;
                    Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
                    ClassDefinition[] definitions = new ClassDefinition[]{new ClassDefinition(clazz, buf)};
                    inst.redefineClasses(definitions);
                    System.out.println("redefine to reset..");
                } else ;

            } catch (Exception e) {
            }
        }
    }

    private static byte[] getWrappedClass(String className, String[] methods) {
        try {
            String path = className.replace('.', '/') + ".class";
            ClassReader reader = new ClassReader(ClassLoader.getSystemResourceAsStream(path));
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new MyClassAdapter(writer, methods);
            reader.accept(classAdapter, ClassReader.EXPAND_FRAMES);
            return writer.toByteArray();
        } catch (Exception e) {
            System.out.println(">>>>>>");
            e.printStackTrace(System.out);
        }
        return null;
    }

    private static byte[] getOriginClass(String className) {
        try {
            String path = className.replace('.', '/') + ".class";
            ClassReader reader = new ClassReader(ClassLoader.getSystemResourceAsStream(path));
            return reader.b;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
