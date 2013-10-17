package org.jevo.hotswap.sample;

import java.net.URLClassLoader;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
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

        //���ȼ������������Ƿ��Ѿ��������װ����װ�ص������ռ����ˣ�����Ѿ�װ�أ�ֱ�ӷ��أ����������
        clazz = findLoadedClass(name);  //��������Ϊ name���Ѿ������ع�����
        if (clazz != null) {
            if (resolve) {
                resolveClass(clazz);   //����ָ���� Java ��
            }
            //���class�౻�޸Ĺ��������¼���
            if (isModify(name)) {
                MyClassLoader hcl = new MyClassLoader(url);
                clazz = customLoad(name, hcl);
            }
            return (clazz);
        }

        //�����İ���Ϊ"java."��ʼ������ϵͳĬ�ϼ���������
        if (!name.startsWith("org.jevo.")) {
            try {
                //�õ�ϵͳĬ�ϵļ���cl����AppClassLoader
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);   //��������Ϊ name����
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
        //���ñ����������findClass��������������ͼ��ȡ��Ӧ���ֽ���,�����ȡ�ĵ��������defineClass�������������͵��������� �����׳��쳣
        Class clazz = ((MyClassLoader) cl).findClass(name); //��������Ϊ name����
        if (resolve)
            ((MyClassLoader) cl).resolveClass(clazz);
        //�������class�ļ�������޸�ʱ��
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
