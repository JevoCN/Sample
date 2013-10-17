package org.jevo.proxy.sample;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-21
 * Time: 16:46:05
 * To change this template use File | Settings | File Templates.
 */

public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //������Ҫ�����������
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //ͨ���ֽ��뼼����̬��������ʵ��
        return enhancer.create();
    }

    //ʵ��MethodInterceptor�ӿڷ���
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("ǰ�ô���");
        //ͨ����������ø����еķ���
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("���ô���");
        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //ͨ����������ķ�ʽ����������
        Target1 proxyImp = (Target1) proxy.getProxy(Target1.class);
        proxyImp.say();
    }

}
