package org.jevo.msg.sample;

import java.util.Observer;
import java.util.Observable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:07:32
 * To change this template use File | Settings | File Templates.
 */

public class ObserverTest {
    public static void main(String[] args) {
        MyObservable myOservable = new MyObservable();
        myOservable.addObserver(new Observer() {
            //注册匿名内部类Observer，当数据改变时将通知该类的update方法
            public void update(Observable o, Object arg) {
                System.out.println("This value has been changed to " + (String) arg);
            }
        });
        String sValue = "Hello Msg";
        myOservable.changeValue(sValue);
        myOservable.notifyObservers(sValue + "!");//数据的改变由observable主动通知给Observer。
    }
}
