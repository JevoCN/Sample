package org.jevo.annotation.sample;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-7
 * Time: 10:05:07
 * To change this template use File | Settings | File Templates.
 */

public enum ConstantSpecificMethod {
    DATE_TIME {String getInfo() {
        return DateFormat.getDateInstance().format(new Date());
    }},
    CLASSPATH {String getInfo() {
        return System.getenv("CLASSPATH");
    }},
    VERSION {String getInfo() {
        return System.getProperty("java.version");
    }};

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values())
            System.out.println(csm.getInfo());
    }
}

