package org.jevo.groovy.sample;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 15:57:28
 * To change this template use File | Settings | File Templates.
 */

//GroovyShell答应在Java类中（甚至Groovy类）求任意Groovy表达式的值。您可使用Binding对象输进参数给表达式，并终极通过GroovyShell返回Groovy表达式的计算结果。
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
//GroovyShell是推求动态类型表达式的有效工具。典型的应用就是推求用户输进Groovy动态表达式的值，通常会有一个用户界面（user inte***ce,UI），如电子表格程序（spreadsheet application）。




