package org.jevo.msg.sample;

import java.util.Observer;
import java.util.Observable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:07:32
 * To change this template use File | Settings | File Templates.
 */

public class ObserverTest {
    public static void main(String[] args) {
        MyObservable myOservable = new MyObservable();
        myOservable.addObserver(new Observer() {
            //ע�������ڲ���Observer�������ݸı�ʱ��֪ͨ�����update����
            public void update(Observable o, Object arg) {
                System.out.println("This value has been changed to " + (String) arg);
            }
        });
        String sValue = "Hello Msg";
        myOservable.changeValue(sValue);
        myOservable.notifyObservers(sValue + "!");//���ݵĸı���observable����֪ͨ��Observer��
    }
}
