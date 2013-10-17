package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:00:44
 * To change this template use File | Settings | File Templates.
 */

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;

public interface IFieldWiring {

    Class<? extends Annotation> annotationClass();

    void wiring(Object object, Field field);

}