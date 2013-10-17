package org.jevo.annotation.sample;

import java.lang.reflect.Field;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-31
 * Time: 21:58:44
 * To change this template use File | Settings | File Templates.
 */

public class MyAnnotationBeanProcessor {

    /**
     * ע����Դ
     *
     * @param serviceObject
     * @param fieldAutoWirings // ����ʵ��IFieldWiring�ĽӿڵĶ������ǿ����ڴ���չ
     * @throws Exception
     */

    public void wire(Object serviceObject, IFieldWiring fieldAutoWirings) throws Exception {

        Class<?> cls = serviceObject.getClass();

        IFieldWiring[] ifieldAutoWirings = new IFieldWiring[]{fieldAutoWirings};

        for (Field field : cls.getDeclaredFields()) {

            for (IFieldWiring fieldAutoWiring : ifieldAutoWirings) {

                if (field.isAnnotationPresent(fieldAutoWiring.annotationClass())) {

                    fieldAutoWiring.wiring(serviceObject, field);

                    break;

                }

            }

        }

    }

}


