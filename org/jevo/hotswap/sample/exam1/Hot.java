package org.jevo.hotswap.sample.exam1;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-16
 * Time: 17:43:16
 * To change this template use File | Settings | File Templates.
 */

public class Hot {

    public void hot(){
//        System.out.println(" version 1 : "+this.getClass().getClassLoader());
        System.out.println(" version 2 : "+this.getClass().getClassLoader());
    }
}

