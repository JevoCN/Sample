package org.jevo.msg.sample;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:28:44
 * To change this template use File | Settings | File Templates.
 */

public class Server {
    public static void main(String[] args) {
        try {
            //�������ܶ�9001�˿ڵ�Զ�̵��õ�ע���
            LocateRegistry.createRegistry(9001);
            //����Զ�̶����ʵ��(��ͻ����ṩ�ķ���)
            final RemoteIntf service = new RemoteService();
            //���ṩ���ͻ���Զ�˵��õ�Զ�̶������ָ�����Ƽ���Զ�̵���ע���
            Naming.bind("//localhost:9001/server", service);
        } catch (final Exception e1) {
            e1.printStackTrace();
        }
    }
}

