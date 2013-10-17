package org.jevo.join.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-15
 * Time: 17:18:48
 * To change this template use File | Settings | File Templates.
 */

public class ParentThread extends Thread {
    ChildThread t1;

    public ParentThread(ChildThread t1) {
        super("ParentThread");
        this.t1 = t1;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        try {
            t1.join();   //ChildThread �߳�t1�����󣬲������д��д����Ĵ��롣
            System.out.println(threadName + " end.");
        } catch (Exception e) {
            System.out.println("Exception from " + threadName + ".run");
        }
    }

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        ChildThread t1 = new ChildThread();
        ParentThread t = new ParentThread(t1);
        try {
            t1.start();
            Thread.sleep(2000);
            t.start();
//            t.join();//�˴�ע�ͺ󣬽�ֱ�����е���������.
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }

}
