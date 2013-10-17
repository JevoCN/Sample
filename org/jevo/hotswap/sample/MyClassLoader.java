package org.jevo.hotswap.sample;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 11:46:14
 * To change this template use File | Settings | File Templates.
 */

public class MyClassLoader extends URLClassLoader {
    public static Map<String, Long> cacheLastModifyTimeMap = new HashMap<String, Long>();
    public static URL url = null;

    public MyClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;

        //首先检查请求的类型是否已经被这个类装载器装载到命名空间中了，如果已经装载，直接返回；否则继续。
        clazz = findLoadedClass(name);  //查找名称为 name的已经被加载过的类
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);   //链接指定的 Java 类
            }
            //如果class类被修改过，则重新加载
            if (isModify(name)) {
                MyClassLoader hcl = new MyClassLoader(url);
                clazz = customLoad(name, hcl);
            }
            return (clazz);
        }

        //如果类的包名为"java."开始，则有系统默认加载器加载
        if (!name.startsWith("org.jevo.")) {
            try {
                //得到系统默认的加载cl，即AppClassLoader
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);   //加载名称为 name的类
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return (clazz);
                }
            } catch (ClassNotFoundException e) {
                // Ignore
            }
        }

        return customLoad(name, this);
    }

    public Class customLoad(String name, ClassLoader cl) throws ClassNotFoundException {
        return customLoad(name, false, cl);
    }

    public Class customLoad(String name, boolean resolve, ClassLoader cl)
            throws ClassNotFoundException {
        //调用本类加载器的findClass（…）方法，试图获取对应的字节码,如果获取的到，则调用defineClass（…）导入类型到方法区； 否则抛出异常
        Class clazz = ((MyClassLoader) cl).findClass(name); //查找名称为 name的类
        if (resolve)
            ((MyClassLoader) cl).resolveClass(clazz);
        //缓存加载class文件的最后修改时间
        long lastModifyTime = getClassLastModifyTime(name);
        cacheLastModifyTimeMap.put(name, lastModifyTime);
        return clazz;
    }


    private boolean isModify(String name) {
        long lastmodify = getClassLastModifyTime(name);
        long previousModifyTime = cacheLastModifyTimeMap.get(name);
        if (lastmodify > previousModifyTime) {
            return true;
        }
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
        String simpleName = name.replaceAll("\\.", "/");
        return url.getPath() + simpleName + ".class";
    }

}
