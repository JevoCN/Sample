package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
     * Dao的类型
     *
     * @return
     */

    String type() default "A"; // 连接的数据库类型 A or B

    String sqlMap() default ""; // Sql-Map-Config文件的路径，用于加载iBatis的SqlMapClient对象

}


