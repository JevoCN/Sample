package org.jevo.hotswap.sample.exam2;

import java.net.URLClassLoader;
import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-16
 * Time: 22:18:44
 * To change this template use File | Settings | File Templates.
 */

public class MyClassLoader extends URLClassLoader {
    //�������class�ļ�����������޸�ʱ��
    public static Map<String, Long> cacheLastModifyTimeMap = new HashMap<String, Long>();
    public static URL url = null;

    public MyClassLoader(URL url) {
//        super(new URL[]{url}, Thread.currentThread().getContextClassLoader());
        super(new URL[]{url});
//        URL[] serverURLs = new URL[]{new URL("file://D:/Project/test/out/")};
        this.url = url;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }
    
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.err.println("loadClass..start  " + name);
        Class clazz = null;
        //�鿴HotSwapURLClassLoaderʵ�������£��Ƿ��Ѿ����ع�class
        //��ͬ��HotSwapURLClassLoaderʵ���ǲ��������
        clazz = findLoadedClass(name);
        System.err.println("loadClass..         1");
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);
            }
            //���class�౻�޸Ĺ��������¼���
            if (isModify(name)) {
                System.err.println("new..2");
                MyClassLoader hcl = new MyClassLoader(url);
                clazz = customLoad(name, hcl);
            }
            return (clazz);
        }

        //�����İ���Ϊ"java."��ʼ������ϵͳĬ�ϼ�����AppClassLoader����
        System.err.println("loadClass..         2");
        if (!name.startsWith("org.jevo.")) {
            try {
                System.err.println("loadClass..        3");
                //�õ�ϵͳĬ�ϵļ���cl����AppClassLoader
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return (clazz);
                }
            } catch (ClassNotFoundException e) {
                // Ignore
            }
            System.err.println("loadClass..        3 no reuturn.");
        }

        System.err.println("loadClass..         4");
        return customLoad(name, this);
    }

    public Class customLoad(String name, ClassLoader cl) throws ClassNotFoundException {
//        System.err.println("customLoad..1 " + name);
        return customLoad(name, false, cl);
    }

    public Class customLoad(String name, boolean resolve, ClassLoader cl)
            throws ClassNotFoundException {
//         System.err.println("customLoad..2 " + name);
        //findClass()���õ���URLClassLoader����������ClassLoader��findClass()����
        Class clazz = ((MyClassLoader) cl).findClass(name);
        if (resolve)
            ((MyClassLoader) cl).resolveClass(clazz);
        //�������class�ļ�������޸�ʱ��
        long lastModifyTime = getClassLastModifyTime(name);
        cacheLastModifyTimeMap.put(name, lastModifyTime);
        System.err.println("customLoad..         " + name);
        return clazz;
    }


    private boolean isModify(String name) {
        long lastmodify = getClassLastModifyTime(name);
        long previousModifyTime = cacheLastModifyTimeMap.get(name);
        if (lastmodify > previousModifyTime) {
            System.err.println("modify return true");
            return true;
        }
        System.err.println("modify return false");
        return false;
    }

    private long getClassLastModifyTime(String name) {
        String path = getClassCompletePath(name);
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException(new FileNotFoundException(name));
        }
        return file.lastModified();
    }

    private String getClassCompletePath(String name) {
//        String simpleName = name.substring(name.lastIndexOf(".") + 1);
//        System.err.println("simpleName = " + name);
        String simpleName = name.replaceAll("\\.", "/");
//        System.err.println("simpleName = " + simpleName);
        return url.getPath() + simpleName + ".class";
    }

}
