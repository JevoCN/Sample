package org.jevo.annotation.sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-31
 * Time: 21:22:18
 * To change this template use File | Settings | File Templates.
 */

public class MyAnnotationTest {

    @MyAnnotation(name = "a", type = MyAnnotation.MyEnum.B)
    public void execute() {
        System.out.println("method");
    }

    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        MyAnnotationTest annotationTest = new MyAnnotationTest();
        //��ȡAnnotationTest��Classʵ��
        Class<MyAnnotationTest> c = MyAnnotationTest.class;
        //��ȡ��Ҫ����ķ���Methodʵ��
        Method method = c.getMethod("execute", new Class[]{});
        //�жϸ÷����Ƿ����MyAnnotationע��
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            //��ȡ�÷�����MyAnnotationע��ʵ��
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            //ִ�и÷���
            method.invoke(annotationTest, new Object[]{});
            //��ȡmyAnnotation
            String value = myAnnotation.name();
            System.out.println(value);
        }
        //��ȡ�����ϵ�����ע��
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
