package org.jevo.msg.sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:30:05
 * To change this template use File | Settings | File Templates.
 */

class DispTimeImpl extends UnicastRemoteObject implements DispTimeIntf {
    protected DispTimeImpl() throws RemoteException {
        super();
    }

    public String dispTime(final String time) throws RemoteException {
        System.err.println("get from Server: " + time);
        return time;
    }
}

