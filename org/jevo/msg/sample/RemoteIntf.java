package org.jevo.msg.sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:24:34
 * To change this template use File | Settings | File Templates.
 */

public interface RemoteIntf extends Remote {
    public void regist(DispTimeIntf client, int second) throws RemoteException;
    public void unregist(DispTimeIntf client) throws RemoteException;
}
