package org.jevo.future.sample;

import java.util.concurrent.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
                    throw new Exception("Callable terminated with Exception!"); // call方法可以抛出异常
                }
            }
        });
        executor.execute(future);
        long t = System.currentTimeMillis();
        try {

//            String result = future.get(3000, TimeUnit.MILLISECONDS); //取得结果，同时设置超时执行时间为5秒。
            String result = future.get(); //取得结果，同时设置超时执行时间为5秒。
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
