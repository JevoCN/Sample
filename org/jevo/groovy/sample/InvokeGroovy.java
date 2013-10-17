package org.jevo.groovy.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
            //从文件中读取
            //Class groovyClass = groovyCl.parseClass(new File("D:/project/openjweb/src/java/org/openjweb/groovy/Foo.groovy"));
            //直接使用Groovy字符串,也可以获得正确结果
            //Class groovyClass = groovyCl.parseClass("package org.jevo.groovy.sample; \r\n class Foo implements IFoo {public Object run(Object foo) {return 23}}");
            Class groovyClass = groovyCl.parseClass("package org.jevo.groovy.sample; \r\n class Foo implements IFoo {public Object run(Object foo) {return 2+2>1}}");//这个返回true

            IFoo foo = (IFoo) groovyClass.newInstance();
            System.out.println(foo.run(new Integer(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




