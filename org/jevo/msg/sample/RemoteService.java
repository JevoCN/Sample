package org.jevo.msg.sample;

import java.util.*;
import java.text.DateFormat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-13
 * Time: 23:27:45
 * To change this template use File | Settings | File Templates.
 */

public class RemoteService extends UnicastRemoteObject implements RemoteIntf {
    //����ͷ���Զ�̶���
    Map<DispTimeIntf, Runnable> clients = new HashMap<DispTimeIntf, Runnable>();

    //������ʽд�����췽�������׳��쳣
    public RemoteService() throws RemoteException {
    }

    //����������Ҫ���пͻ��˵�Զ�̶���(�ӿ�),�Ա�ص�
    @Override
    public void regist(final DispTimeIntf client, final int seconds) {
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer t = new Timer();
                TimerTask callback_task = new TimerTask() {
                    public void run() {
                        try {
                            Date now = new Date();
                            DateFormat d = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
                            String time = d.format(now);
                            //���ÿͻ���Ӧ��
                            try {
                                client.dispTime(time);
                            } catch (Exception e) {
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.schedule(callback_task, 0, seconds * 1000);
            }
        });
        t.run();
        clients.put(client, t);
    }

    @Override
    public void unregist(DispTimeIntf client) {
        Thread t = (Thread) clients.remove(client);
        t.interrupt();
    }
    
}
