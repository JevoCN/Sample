package org.jevo.groovy.sample;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 16:11:07
 * To change this template use File | Settings | File Templates.
 */
//GroovyClassLoader是一个定制的类装载器，负责解释加载Java类中用到的Groovy类。它也能编译。下例展示了如何使用GroovyClassLoader加载Groovy类并且调用该类的一个方法。
public class GroovyClassLoaderExample {

    public static void main(String args[]) {
        try {
            GroovyClassLoader loader = new GroovyClassLoader();
            Class fileCreator = loader.parseClass(new File("GroovySimpleFileCreator.groovy"));
            GroovyObject object = (GroovyObject) fileCreator.newInstance();
            object.invokeMethod("createFile", "C:\\temp\\emptyFile.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


