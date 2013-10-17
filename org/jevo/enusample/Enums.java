package org.jevo.enusample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-6
 * Time: 23:28:45
 * To change this template use File | Settings | File Templates.
 */

//: net/mindview/util/Enums.java

import java.util.*;

public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        System.err.println("Leng = " + values.length);
        return values[rand.nextInt(values.length)];
    }
} ///:~
