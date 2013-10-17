package org.jevo.hotswap.sample.test;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.io.File;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:18:08
 * To change this template use File | Settings | File Templates.
 */
/*
�������������������У����������������������е�ԭ���������ģ�

1.���Ȼ����Զ����MyClassLoaderȥ����libPath�����е�jar��

2.����jar���󣬱����web�����classpath��Ҳ����Դ�������뵽classLoader��homePath���������Զ����MyClassLoader�����ҵ�web�������е���Դ��web�������е���(Ҳ����web������Ҫִ�е�ҵ���߼�)

3.Ȼ���Զ�������MyClassLoader����Ϊ��ǰ�̵߳�ClassLoader

4.�����÷�����Ƶ���web��ҵ���߼��࣬�Լ�ҵ���߼���ĳ������


 */
public class MyClassLoaderTest {
    public void startToRun() {
        String libPath = "D:/Java/WorkspaceForMyeclipse/exam2.0/WebRoot/WEB-INF/lib/ ";
        String mainClass = "com.onnet.utils.schedule.ExamTipAndDeleteSchedule";
        String homePath = "D:/Java/WorkspaceForMyeclipse/exam2.0/WebRoot/WEB-INF/classes/";
        String methodName = "tipAndDelete";
        try {
            File lib = new File(libPath);
            File files[] = lib.listFiles();
            URL[] urls = MyClassLoader.Path2URL(new File(libPath));
            MyClassLoader loader = new MyClassLoader(urls,
                    Thread.currentThread().getContextClassLoader());
            //���ַ�ʽ�����ԣ����������·��
//          loader.addURL(new File(homePath).toURI().toURL());
//          loader.addClassPath(new File(homePath));
            loader.addClassPath(homePath);
            Thread.currentThread().setContextClassLoader(loader);
            Class clazz = loader.loadClass(mainClass);
            Method m = clazz.getMethod(methodName, null);
            m.invoke(clazz.newInstance(), null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new MyClassLoaderTest().startToRun();
    }


}
