package org.jevo.hotswap.sample.exam2;

import java.net.URLClassLoader;
import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-16
 * Time: 22:18:44
 * To change this template use File | Settings | File Templates.
 */

public class MyClassLoader extends URLClassLoader {
    //缓存加载class文件的最后最新修改时间
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
        //查看HotSwapURLClassLoader实例缓存下，是否已经加载过class
        //不同的HotSwapURLClassLoader实例是不共享缓存的
        clazz = findLoadedClass(name);
        System.err.println("loadClass..         1");
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);
            }
            //如果class类被修改过，则重新加载
            if (isModify(name)) {
                System.err.println("new..2");
                MyClassLoader hcl = new MyClassLoader(url);
                clazz = customLoad(name, hcl);
            }
            return (clazz);
        }

        //如果类的包名为"java."开始，则有系统默认加载器AppClassLoader加载
        System.err.println("loadClass..         2");
        if (!name.startsWith("org.jevo.")) {
            try {
                System.err.println("loadClass..        3");
                //得到系统默认的加载cl，即AppClassLoader
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
        //findClass()调用的是URLClassLoader里面重载了ClassLoader的findClass()方法
        Class clazz = ((MyClassLoader) cl).findClass(name);
        if (resolve)
            ((MyClassLoader) cl).resolveClass(clazz);
        //缓存加载class文件的最后修改时间
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
