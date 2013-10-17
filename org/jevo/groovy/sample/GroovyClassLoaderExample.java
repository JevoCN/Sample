package org.jevo.groovy.sample;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 16:11:07
 * To change this template use File | Settings | File Templates.
 */
//GroovyClassLoader��һ�����Ƶ���װ������������ͼ���Java�����õ���Groovy�ࡣ��Ҳ�ܱ��롣����չʾ�����ʹ��GroovyClassLoader����Groovy�ಢ�ҵ��ø����һ��������
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


