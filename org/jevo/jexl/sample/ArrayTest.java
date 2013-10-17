package org.jevo.jexl.sample;

import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.Expression;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-7
 * Time: 12:59:01
 * To change this template use File | Settings | File Templates.
 */

public class ArrayTest {
    public static void main(String[] args) {
        JexlEngine jexl = new JexlEngine();
        JexlContext jc = new MapContext();

        List l = new ArrayList();
        l.add("It is from location 0");
        Integer two = new Integer(2);
        l.add(two);
        jc.set("array", l);

        Expression e = jexl.createExpression("array[1]");//从array中取数据
        Object o = e.evaluate(jc);
//    System.out.printf("\nObject @ location 1 = ", o, two);
        System.err.println("Object @ location 1 = " + o);

        e = jexl.createExpression("array[1]");//从array中取数据
        o = e.evaluate(jc);
        System.err.println("Object @ location 0 = " + o);

        e = jexl.createExpression("array[0].length()");
        o = e.evaluate(jc);
//    System.out.printf("\nThe length of the string at location 0 is : ", o, Integer.valueOf(21));
        System.err.println("The length of the string at location 0 is :" + o);
    }
}
