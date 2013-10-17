package org.jevo.jexl.sample;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 13:06:01
 * To change this template use File | Settings | File Templates.
 */

public class MethodPropertyTest {
    public static void main(String[] args) {
        JexlEngine jexl = new JexlEngine();
        JexlContext jc = new MapContext();

        MethodPropertyTest foo = new MethodPropertyTest();
        Integer number = new Integer(10);

        jc.set("foo", foo);
        jc.set("number", number);

        Expression e = jexl.createExpression("foo.getFoo()");
        Object o = e.evaluate(jc);
//        out.print("value returned by the method getFoo() is : ", o, foo.getFoo());
        System.err.println("value returned by the method getFoo() is : " + o);

        e = jexl.createExpression("foo.convert(1)");
        o = e.evaluate(jc);
//        out.print("value of " + e.getExpression() + " is : ", o, foo.convert(1L));
        System.err.println("value of " + e.getExpression() + " is : " + o);

        e = jexl.createExpression("foo.convert(1+7)");
        o = e.evaluate(jc);
//        out.print("value of " + e.getExpression() + " is : ", o, foo.convert(8L));
        System.err.println("value of " + e.getExpression() + " is : " + o);

        e = jexl.createExpression("foo.convert(1+number)");
        o = e.evaluate(jc);
//        out.print("value of " + e.getExpression() + " is : ", o, foo.convert(1 + number.intValue()));
        System.err.println("value of " + e.getExpression() + " is : " + o);

        e = jexl.createExpression("foo.bar");
        o = e.evaluate(jc);
//        out.print("value returned for the property 'bar' is : ", o, foo.get("bar"));
        System.err.println("value returned for the property 'bar' is : " + o);
    }

    public String getFoo() {
        return "This is from getFoo()";
    }

    public String get(String arg) {
        return "This is the property " + arg;
    }

    public String convert(long i) {
        return "The value is : " + i;
    }
}
