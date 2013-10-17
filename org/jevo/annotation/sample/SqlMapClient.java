package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:02:46
 * To change this template use File | Settings | File Templates.
 */

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.commons.lang.builder.ToStringStyle;

@SuppressWarnings("unchecked")

public class SqlMapClient {

    public SqlMapClient(String s, String t) {

        sqlMap = s;

        type = t;

    }

    public SqlMapClient() {

    }

    private String type = null;

    private String sqlMap = null;

    // get、set方法 略

    // 用于演示查询后返回一个String的返回结果

    public String selectForObject(String sql, Map in) {

        return this.toString();

    }

    @Override

    public String toString() {

        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("sqlMap", sqlMap)

                .append("type", type).toString();

    }

}

