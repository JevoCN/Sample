package org.jevo.msg.sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:29:47
 * To change this template use File | Settings | File Templates.
 */

public interface DispTimeIntf extends Remote {
    //提供给Server端调用的回调接口
    public String dispTime(String time) throws RemoteException;
}