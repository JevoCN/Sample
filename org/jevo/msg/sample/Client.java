package org.jevo.msg.sample;

import java.rmi.RemoteException;
import java.rmi.Naming;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:29:22
 * To change this template use File | Settings | File Templates.
 */

public class Client {
    public static void main(String[] args) {
        try {
            String ip = "127.0.0.1";
            //��ָ����ַ����Զ�̶���ʵ��
            RemoteIntf service = (RemoteIntf) Naming.lookup("//" + ip + ":9001/server");
            DispTimeImpl dispTime = new DispTimeImpl();
            service.regist(dispTime, 1);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
