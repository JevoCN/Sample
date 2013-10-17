package org.jevo.annotation.sample;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 21:53:34
 * To change this template use File | Settings | File Templates.
 */

public class ReflectUtils {

    /**
     * 取得字段值
     *
     * @param obj
     * @param fieldName
     * @return
     */

    public static Object getFieldValue(Object obj, String fieldName) {

        if (obj == null || fieldName == null || "".equals(fieldName)) {
            return null;
        }

        Class<?> clazz = obj.getClass();

        try {

            String methodname = "get" + StringUtils.capitalise(fieldName);

            Method method = clazz.getDeclaredMethod(methodname);

            method.setAccessible(true);

            return method.invoke(obj);

        } catch (Exception e) {

            try {

                Field field = clazz.getDeclaredField(fieldName);

                field.setAccessible(true);

                return field.get(obj);

            } catch (Exception e1) {

                e1.printStackTrace();

            }

        }

        return null;

    }

    public static void setFieldValue(Object target, String fname, Class<?> fieldClass, Object fieldObj) {

        if (!fieldClass.isAssignableFrom(fieldObj.getClass())) {

            return;

        }

        Class<?> clazz = target.getClass();

        try {

            Method method = clazz.getDeclaredMethod("set" + Character.toUpperCase(fname.charAt(0))

                    + fname.substring(1), fieldClass);

            method.setAccessible(true);

            method.invoke(target, fieldObj);

        } catch (Exception e) {

            try {

                Field field = clazz.getDeclaredField(fname);

                field.setAccessible(true);

                field.set(target, fieldObj);

            } catch (Exception e1) {

                e1.printStackTrace();

            }

        }

    }

}

