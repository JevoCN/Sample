package org.jevo.msg.sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:29:47
 * To change this template use File | Settings | File Templates.
 */

public interface DispTimeIntf extends Remote {
    //�ṩ��Server�˵��õĻص��ӿ�
    public String dispTime(String time) throws RemoteException;
}