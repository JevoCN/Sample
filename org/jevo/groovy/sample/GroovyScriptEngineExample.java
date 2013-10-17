package org.jevo.groovy.sample;

import groovy.util.GroovyScriptEngine;
import groovy.lang.Binding;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 16:04:13
 * To change this template use File | Settings | File Templates.
 */
//hell多用于推求对立的脚本或表达式，假如换成相互关联的多个脚本，使用GroovyScriptEngine会更好些。GroovyScriptEngine从您指定的位置（文件系统，URL，数据库，等等）加载Groovy脚本，并且随着脚本变化而重新加载它们。如同GroovyShell一样，GroovyScriptEngine也答应您传进参数值，并能返回脚本的值。
//假设您有一个简单的Groovy 脚本，C:\tmp\SimpleScript.groovy，具体如下：
//SimpleScript.groovy
//println "Welcome to $language"
//return "The End"

//下例演示了如何传进参数给GroovyScriptEngine，并执行SimpleScript.groovy脚本，然后返回一个值。

public class GroovyScriptEngineExample {

    public static void main(String args[]) {
        try {
            String[] roots = new  String[]{".\\src\\org\\jevo\\groovy\\sample\\"} ;//定义Groovy脚本引擎的根路径
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
//尽管GroovyScriptEngine是执行多个Groovy脚本的有效方法，但仍不能妥善处理复杂类。对于同时处理Groovy类和脚本的情况，最完善的解决方案便是GroovyClassLoader（实在，GroovyShell和GroovyScriptEngine都会用到它）




