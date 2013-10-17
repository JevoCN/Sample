package org.jevo.groovy.sample;

import javax.script.ScriptEngineManager;
import javax.script.Invocable;
import javax.script.ScriptEngine;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 17:27:45
 * To change this template use File | Settings | File Templates.
 */

public class GroovyJSR223Example {

    public static void main(String args[]) {
        try {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("groovy");
            String HelloLanguage = "def hello(language) {return \"Hello $language\"}";
            engine.eval(HelloLanguage);
            Invocable inv = (Invocable) engine;
            Object[] params = {new String("Groovy")};
            Object result = inv.invokeFunction("hello", params);
            assert result.equals("Hello Groovy");
            System.err.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


