package org.jevo.proxy.sample;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-21
 * Time: 16:41:42
 * To change this template use File | Settings | File Templates.
 */

public class TargetProxy implements InvocationHandler {
    private Target sub;

    public TargetProxy() {
    }

    public TargetProxy(Target obj) {
        sub = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method);
        method.invoke(sub, args);
        System.out.println("after calling " + method);
        return null;
    }

    public static void main(String[] args) throws Throwable {
        Target targetImpl = new TargetImpl(); //在这里指定被代理类
        InvocationHandler ds = new TargetProxy(targetImpl); //初始化代理类 
        Class cls = targetImpl.getClass();

        Target target = (Target) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);
        target.say();

//        Class c = Proxy.getProxyClass(cls.getClassLoader(),cls.getInterfaces()) ;
//        Constructor ct=c.getConstructor(new Class[]{InvocationHandler.class});
//        Target target =( Target) ct.newInstance(new Object[]{ds});
//        target.request();

    }

}

