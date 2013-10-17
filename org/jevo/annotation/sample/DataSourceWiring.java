package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:01:41
 * To change this template use File | Settings | File Templates.
 */

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;

public class DataSourceWiring implements IFieldWiring {

    @Override

    public void wiring(Object object, Field field) {

        Object fieldObj = ReflectUtils.getFieldValue(object, field.getName()); // ���field��Ӧ�Ķ���

        if (fieldObj != null) {

            return;

        }

        DataSource annotation = field.getAnnotation(DataSource.class);

        String type = annotation.type();

        String sqlMap = annotation.sqlMap();

        // ��������û�����ʵ�֣�����ÿ�ζ�ȥ�����µ�SqlMapClient����

        SqlMapClient sqlMapImpl = new SqlMapClient(sqlMap, type);

        // ������SqlMapClientע�뵽bean������ֶ���

        ReflectUtils.setFieldValue(object, field.getName(), SqlMapClient.class, sqlMapImpl);

    }

    @Override

    public Class<? extends Annotation> annotationClass() {

        return DataSource.class;

    }

}


