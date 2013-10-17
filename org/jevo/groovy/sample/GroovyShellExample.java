package org.jevo.groovy.sample;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 15:57:28
 * To change this template use File | Settings | File Templates.
 */

//GroovyShell��Ӧ��Java���У�����Groovy�ࣩ������Groovy���ʽ��ֵ������ʹ��Binding����������������ʽ�����ռ�ͨ��GroovyShell����Groovy���ʽ�ļ�������
public class GroovyShellExample {

    public static void main(String args[]) {
        Binding binding = new Binding();
        binding.setVariable("x", 10);
        binding.setVariable("language", "Groovy");

        GroovyShell shell = new GroovyShell(binding);
        Object value = shell.evaluate("println \"Welcome to $language\"; y = x * 2; z = x * 3; return x ");

        assert value.equals(10);
        assert binding.getVariable("y").equals(20);
        assert binding.getVariable("z").equals(30);
        System.err.println(value +", " + value.equals(10));
        System.err.println(binding.getVariable("y") +", " + binding.getVariable("y").equals(20));
        System.err.println(binding.getVariable("z") +", " + binding.getVariable("z").equals(30));
    }

}
//GroovyShell������̬���ͱ��ʽ����Ч���ߡ����͵�Ӧ�þ��������û����Groovy��̬���ʽ��ֵ��ͨ������һ���û����棨user inte***ce,UI��������ӱ�����spreadsheet application����




