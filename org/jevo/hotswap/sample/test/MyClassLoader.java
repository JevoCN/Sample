package org.jevo.hotswap.sample.test;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:16:15
 * To change this template use File | Settings | File Templates.
 */

/**
 * 自定义类加载器，重载URLClassLoader大部分方法，将jar文件添加到ClassLoader，将jar包路径下所有jar包添加到
 * ClassLoader,将ClassPath路径下所有的类以及资源加载到ClassLoader
 */
public class MyClassLoader extends URLClassLoader {

    private Set<String> names = new HashSet<String>();// 存放自定义类的名称

    public MyClassLoader(URL[] urls, ClassLoader parent,
                         URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * url 既可以是一个文件路径(这个文件路径必须是url格式)，里面存放了资源文件，存放了类；也可以是一个jar文件的url格式
     *
     * @param urls
     */
    public void addURL(URL... urls) {
        for (URL url : urls) {
            System.out.println("加载类路径：" + url.toString());
            super.addURL(url);
        }
    }

    /**
     * 加载jar文件到ClassLoader
     *
     * @param files
     */
    public void addJar(File... files) {
        if (files == null || files.length == 0) return;
        for (File file : files) {
            try {
                addURL(file.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载ClassPath到ClassLoader,classPath下所有的资源文件，类全部加载到ClassLoader
     *
     * @param classPath 类路径
     */
    public void addClassPath(String classPath) {
        if (isEmpty(classPath)) return;
        addClassPath(new File(classPath));
    }

    /**
     * 添加类路径到ClassLoader
     *
     * @param path
     */
    public void addClassPath(File path) {
        if (path.isFile()) return;
        try {
            addURL(path.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加jar包的路径，即我们所说的lib包
     *
     * @param path
     */
    public void addJarPath(File path) {
        URL[] urls = Path2URL(path);
        if (urls == null || urls.length == 0) return;
        addURL(urls);
    }

    /**
     * @param path jar包路径
     * @return ClassLoader支持的URL数组
     */
    public static URL[] Path2URL(File path) {
        if (path.isFile()) return new URL[0];
        File files[] = path.listFiles();
        URL urls[] = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            try {
                urls[i] = files[i].toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    public URL findResource(String name) {
        System.out.println("查找资源:" + name);
        return super.findResource(name);
    }

    private boolean isEmpty(String classPath) {
        if (classPath != null && classPath.trim().length() > 0) return false;
        return true;
    }

}
