package org.jevo.annotation.sample;

import java.lang.reflect.Field;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 21:58:44
 * To change this template use File | Settings | File Templates.
 */

public class MyAnnotationBeanProcessor {

    /**
     * 注入资源
     *
     * @param serviceObject
     * @param fieldAutoWirings // 所有实现IFieldWiring的接口的对象，我们可以在此扩展
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


