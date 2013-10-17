package org.jevo.groovy.sample;

import groovy.util.GroovyScriptEngine;
import groovy.lang.Binding;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 16:04:13
 * To change this template use File | Settings | File Templates.
 */
//hell��������������Ľű�����ʽ�����绻���໥�����Ķ���ű���ʹ��GroovyScriptEngine�����Щ��GroovyScriptEngine����ָ����λ�ã��ļ�ϵͳ��URL�����ݿ⣬�ȵȣ�����Groovy�ű����������Žű��仯�����¼������ǡ���ͬGroovyShellһ����GroovyScriptEngineҲ��Ӧ����������ֵ�����ܷ��ؽű���ֵ��
//��������һ���򵥵�Groovy �ű���C:\tmp\SimpleScript.groovy���������£�
//SimpleScript.groovy
//println "Welcome to $language"
//return "The End"

//������ʾ����δ���������GroovyScriptEngine����ִ��SimpleScript.groovy�ű���Ȼ�󷵻�һ��ֵ��

public class GroovyScriptEngineExample {

    public static void main(String args[]) {
        try {
            String[] roots = new  String[]{".\\src\\org\\jevo\\groovy\\sample\\"} ;//����Groovy�ű�����ĸ�·��
            GroovyScriptEngine engine = new GroovyScriptEngine(roots);
            Binding binding = new Binding();
            binding.setVariable("language", "Groovy");
            Object value = engine.run("SimpleScript.groovy", binding);
            assert value.equals("The End");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//����GroovyScriptEngine��ִ�ж��Groovy�ű�����Ч���������Բ������ƴ������ࡣ����ͬʱ����Groovy��ͽű�������������ƵĽ����������GroovyClassLoader��ʵ�ڣ�GroovyShell��GroovyScriptEngine�����õ�����




