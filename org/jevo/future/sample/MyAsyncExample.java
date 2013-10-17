package org.jevo.future.sample;

import java.util.concurrent.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
            throw new Exception("Callable terminated with Exception!"); // call方法可以抛出异常
        }
        if (resultOk) {
            return "Task done.";
        } else {
            return "Task failed";
        }
    }

    public static void main(String[] args) {
        //定义几个任务
        MyAsyncExample call1 = new MyAsyncExample(0);
        MyAsyncExample call2 = new MyAsyncExample(1);
        MyAsyncExample call3 = new MyAsyncExample(2);
        //初始了任务执行工具。
        ExecutorService es = Executors.newFixedThreadPool(3);
        //执行任务，任务启动时返回了一个Future对象，
        Future future1 = es.submit(call1);
        Future future2 = es.submit(call2);
        Future future3 = es.submit(call3);
        try {
            //任务1正常执行完毕，future1.get()会返回线程的值
            System.out.println(future1.get());
            //任务2进行一个死循环，调用future2.cancel(true)来中止此线程。
            Thread.sleep(3000);
            System.out.println("Thread 2 terminated? :" + future2.cancel(true));
            //任务3抛出异常，调用future3.get()时会引起异常的抛出
            System.out.println(future3.get());
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
