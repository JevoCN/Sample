package org.jevo.annotation.sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
        //获取AnnotationTest的Class实例
        Class<MyAnnotationTest> c = MyAnnotationTest.class;
        //获取需要处理的方法Method实例
        Method method = c.getMethod("execute", new Class[]{});
        //判断该方法是否包含MyAnnotation注解
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            //获取该方法的MyAnnotation注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            //执行该方法
            method.invoke(annotationTest, new Object[]{});
            //获取myAnnotation
            String value = myAnnotation.name();
            System.out.println(value);
        }
        //获取方法上的所有注解
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
