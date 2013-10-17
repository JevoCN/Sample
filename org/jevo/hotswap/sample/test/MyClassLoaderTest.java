package org.jevo.hotswap.sample.test;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.io.File;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:18:08
 * To change this template use File | Settings | File Templates.
 */
/*
接下来就能启用命令行，运行这个程序，这个程序运行的原理是这样的：

1.首先会用自定义的MyClassLoader去加载libPath下所有的jar包

2.加载jar包后，必须把web程序的classpath，也就是源码的类加入到classLoader，homePath，这样，自定义的MyClassLoader就能找到web程序所有的资源，web程序运行的类(也就是web程序需要执行的业务逻辑)

3.然后将自定义的这个MyClassLoader设置为当前线程的ClassLoader

4.最后采用反射机制调用web的业务逻辑类，以及业务逻辑的某个方法


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
            //三种方式都可以，都是添加类路径
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
