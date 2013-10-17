package org.jevo.hotswap.sample.test;

import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:16:15
 * To change this template use File | Settings | File Templates.
 */

/**
 * �Զ����������������URLClassLoader�󲿷ַ�������jar�ļ���ӵ�ClassLoader����jar��·��������jar����ӵ�
 * ClassLoader,��ClassPath·�������е����Լ���Դ���ص�ClassLoader
 */
public class MyClassLoader extends URLClassLoader {

    private Set<String> names = new HashSet<String>();// ����Զ����������

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
     * url �ȿ�����һ���ļ�·��(����ļ�·��������url��ʽ)������������Դ�ļ���������ࣻҲ������һ��jar�ļ���url��ʽ
     *
     * @param urls
     */
    public void addURL(URL... urls) {
        for (URL url : urls) {
            System.out.println("������·����" + url.toString());
            super.addURL(url);
        }
    }

    /**
     * ����jar�ļ���ClassLoader
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
     * ����ClassPath��ClassLoader,classPath�����е���Դ�ļ�����ȫ�����ص�ClassLoader
     *
     * @param classPath ��·��
     */
    public void addClassPath(String classPath) {
        if (isEmpty(classPath)) return;
        addClassPath(new File(classPath));
    }

    /**
     * �����·����ClassLoader
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
     * ���jar����·������������˵��lib��
     *
     * @param path
     */
    public void addJarPath(File path) {
        URL[] urls = Path2URL(path);
        if (urls == null || urls.length == 0) return;
        addURL(urls);
    }

    /**
     * @param path jar��·��
     * @return ClassLoader֧�ֵ�URL����
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
        System.out.println("������Դ:" + name);
        return super.findResource(name);
    }

    private boolean isEmpty(String classPath) {
        if (classPath != null && classPath.trim().length() > 0) return false;
        return true;
    }

}
