package org.jevo.jexl.sample;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.Expression;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 12:38:34
 * To change this template use File | Settings | File Templates.
 */

public class jextTest {
    public static void main(String[] args) {
        //数学公式解析和计算
        String expression = "(a+b)/c*5";
        JexlContext jexlContext = new MapContext();
        jexlContext.set("a", 4);
        jexlContext.set("b", 2);
        jexlContext.set("c", 2);
        jexlContext.set("d", 5);
        Expression e = (Expression) new JexlEngine().createExpression(expression);
        Number num = (Number) e.evaluate(jexlContext);
        System.out.println(num);


        expression = "a || b && c || d";
        jexlContext = new MapContext();
        jexlContext.set("a", true);
        jexlContext.set("b", false);
        jexlContext.set("c", true);
        jexlContext.set("d", false);
        org.apache.commons.jexl2.Expression e1 = new JexlEngine().createExpression(expression);
        Boolean num1 = (Boolean) e1.evaluate(jexlContext);
        System.out.println(num1);


        JexlEngine jexl = new JexlEngine();
        jexl.setCache(512);//Sets a cache for expressions of the defined size有何用
        jexl.setLenient(false);//Sets whether this engine considers unknown variables, methods and constructors as errors or evaluates them as null or zero.
        jexl.setSilent(false); //Sets whether this engine throws JexlException during evaluation when an error is triggered.
        jexl.setStrict(false); //Sets whether this engine behaves in strict or lenient mode.

        String calculate = "(b - a) > a";
        e = jexl.createExpression(calculate);
        JexlContext context = new MapContext();
        context.set("a", "3");
        context.set("b", "5");
        Object res = e.evaluate(context);//silent为false时evaluate方法会抛异常，为true不抛异常但res为null
        System.out.println(res);


    }
}
