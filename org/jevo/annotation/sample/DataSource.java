package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:05:33
 * To change this template use File | Settings | File Templates.
 */

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

@Target(ElementType.FIELD)

@Retention(RetentionPolicy.RUNTIME)

public @interface DataSource {

    /**
     * Dao������
     *
     * @return
     */

    String type() default "A"; // ���ӵ����ݿ����� A or B

    String sqlMap() default ""; // Sql-Map-Config�ļ���·�������ڼ���iBatis��SqlMapClient����

}


