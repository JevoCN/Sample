package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:04:34
 * To change this template use File | Settings | File Templates.
 */

import java.util.Map;

@SuppressWarnings("unchecked")

public class MyService {

    @DataSource(type = "B", sqlMap = "com/annotation/sql-map-config-B.xml")

    private SqlMapClient sqlMapB = null;

    @DataSource(type = "A", sqlMap = "com/annotation/sql-map-config-A.xml")

    private SqlMapClient sqlMapA = null;

    // get、set方法 略

    // 模拟在DB-B数据库取得数据

    public String selectForObjectFromB(String sql, Map in) {

        return sqlMapB.selectForObject("", null);

    }

    // 模拟在DB-A数据库取得数据

    public String selectForObjectFromA(String sql, Map in) {

        return sqlMapA.selectForObject("", null);

    }

}


