package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
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

    // get��set���� ��

    // ģ����DB-B���ݿ�ȡ������

    public String selectForObjectFromB(String sql, Map in) {

        return sqlMapB.selectForObject("", null);

    }

    // ģ����DB-A���ݿ�ȡ������

    public String selectForObjectFromA(String sql, Map in) {

        return sqlMapA.selectForObject("", null);

    }

}


