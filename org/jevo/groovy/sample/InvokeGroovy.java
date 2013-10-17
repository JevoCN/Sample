package org.jevo.groovy.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 15:10:36
 * To change this template use File | Settings | File Templates.
 */

import groovy.lang.GroovyClassLoader;

import java.io.File;

public class InvokeGroovy {
    public static void main(String[] args) {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            //���ļ��ж�ȡ
            //Class groovyClass = groovyCl.parseClass(new File("D:/project/openjweb/src/java/org/openjweb/groovy/Foo.groovy"));
            //ֱ��ʹ��Groovy�ַ���,Ҳ���Ի����ȷ���
            //Class groovyClass = groovyCl.parseClass("package org.jevo.groovy.sample; \r\n class Foo implements IFoo {public Object run(Object foo) {return 23}}");
            Class groovyClass = groovyCl.parseClass("package org.jevo.groovy.sample; \r\n class Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}");//�������true

            IFoo foo = (IFoo) groovyClass.newInstance();
            System.out.println(foo.run(new Integer(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




