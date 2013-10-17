package org.jevo.join.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
            t1.join();   //ChildThread 线程t1结束后，才能运行此行代码后的代码。
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
//            t.join();//此处注释后，将直接运行到结束代码.
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }

}
