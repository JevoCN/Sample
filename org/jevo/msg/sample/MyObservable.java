package org.jevo.msg.sample;

import java.util.Observable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:06:36
 * To change this template use File | Settings | File Templates.
 */

public class MyObservable extends Observable {
    private String data;

    public void changeValue(String fValue) {
        data = fValue;
        setChanged();
    }
}

