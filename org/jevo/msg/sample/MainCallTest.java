package org.jevo.msg.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-13
 * Time: 22:23:00
 * To change this template use File | Settings | File Templates.
 */

public class MainCallTest {
    private CallBackIntf callBack;

    public void setCallBack(CallBackIntf callBack) {
        this.callBack = callBack;
    }

    public void doMsg() {
        callBack.msgExec();
    }

    public static void main(String[] args) {
        MainCallTest mcall = new MainCallTest();
        mcall.setCallBack(new CallBackIntf() {
            public void msgExec() {
                System.out.println("do some thing for this msg.");
            }
        });

        mcall.doMsg();

    }
}
