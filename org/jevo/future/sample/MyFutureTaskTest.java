package org.jevo.future.sample;

import java.util.concurrent.*;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-11
 * Time: 17:38:04
 * To change this template use File | Settings | File Templates.
 */

public class MyFutureTaskTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception{
                try {
                    Thread.sleep(5* 1000);
                    return "Hello Welcome!";
                }
                catch(Exception e) {
                    throw new Exception("Callable terminated with Exception!"); // call���������׳��쳣
                }
            }
        });
        executor.execute(future);
        long t = System.currentTimeMillis();
        try {

//            String result = future.get(3000, TimeUnit.MILLISECONDS); //ȡ�ý����ͬʱ���ó�ʱִ��ʱ��Ϊ5�롣
            String result = future.get(); //ȡ�ý����ͬʱ���ó�ʱִ��ʱ��Ϊ5�롣
            System.err.println("result is " + result + ", time is " + (System.currentTimeMillis() - t));
        } catch (InterruptedException e) {
            future.cancel(true);
            System.err.println("Interrupte time is " + (System.currentTimeMillis() - t));
        } catch (ExecutionException e) {
            future.cancel(true);
            System.err.println("Throw Exception time is " + (System.currentTimeMillis() - t));
//        } catch (TimeoutException e) {
//            future.cancel(true);
//            System.err.println("Timeout time is " + (System.currentTimeMillis() - t));
        } finally {
            executor.shutdown();
        }

    }
}
