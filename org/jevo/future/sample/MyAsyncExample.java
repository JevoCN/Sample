package org.jevo.future.sample;

import java.util.concurrent.*;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-11
 * Time: 17:25:44
 * To change this template use File | Settings | File Templates.
 */

public class MyAsyncExample implements Callable {
    private int num;

    public MyAsyncExample(int aInt) {
        this.num = aInt;
    }

    public String call() throws Exception {
        boolean resultOk = false;
        if (num == 0) {
            resultOk = true;
        } else if (num == 1) {
            while (true) { //infinite loop
                System.out.println("looping....");
                Thread.sleep(3000);
            }
        } else {
            throw new Exception("Callable terminated with Exception!"); // call���������׳��쳣
        }
        if (resultOk) {
            return "Task done.";
        } else {
            return "Task failed";
        }
    }

    public static void main(String[] args) {
        //���弸������
        MyAsyncExample call1 = new MyAsyncExample(0);
        MyAsyncExample call2 = new MyAsyncExample(1);
        MyAsyncExample call3 = new MyAsyncExample(2);
        //��ʼ������ִ�й��ߡ�
        ExecutorService es = Executors.newFixedThreadPool(3);
        //ִ��������������ʱ������һ��Future����
        Future future1 = es.submit(call1);
        Future future2 = es.submit(call2);
        Future future3 = es.submit(call3);
        try {
            //����1����ִ����ϣ�future1.get()�᷵���̵߳�ֵ
            System.out.println(future1.get());
            //����2����һ����ѭ��������future2.cancel(true)����ֹ���̡߳�
            Thread.sleep(3000);
            System.out.println("Thread 2 terminated? :" + future2.cancel(true));
            //����3�׳��쳣������future3.get()ʱ�������쳣���׳�
            System.out.println(future3.get());
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
