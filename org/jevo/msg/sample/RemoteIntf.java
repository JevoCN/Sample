package org.jevo.msg.sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:24:34
 * To change this template use File | Settings | File Templates.
 */

public interface RemoteIntf extends Remote {
    public void regist(DispTimeIntf client, int second) throws RemoteException;
    public void unregist(DispTimeIntf client) throws RemoteException;
}
