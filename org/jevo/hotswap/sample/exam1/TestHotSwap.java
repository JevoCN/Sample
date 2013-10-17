package org.jevo.hotswap.sample.exam1;

import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-16
 * Time: 17:43:35
 * To change this template use File | Settings | File Templates.
 */

public class TestHotSwap {
    public static void main(String[] args) throws Exception {
        //开启线程，如果class文件有修改，就热替换
        Thread t = new Thread(new MonitorHotSwap());
        t.start();
    }
}

class MonitorHotSwap implements Runnable {
    // Hot就是用于修改，用来测试热加载
    private String className = "org.jevo.hotswap.sample.exam1.Hot";
    private Class hotClazz = null;
    private HotSwapURLClassLoader hotSwapCL = null;

    @Override
    public void run() {
        try {
            while (true) {
                initLoad();
//                Hot hot = (Hot)hotClazz.newInstance(); 不能转换为Hot类型，会抛出java.lang.ClassCastException: org.jevo.hotswap.sample.Hot cannot be cast to org.jevo.hotswap.sample.Hot
                Object hot = hotClazz.newInstance();
                Method m = hotClazz.getMethod("hot");
                m.invoke(hot, null); //打印出相关信息
                // 每隔10秒重新加载一次
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载class
     */
    void initLoad() throws Exception {
        hotSwapCL = HotSwapURLClassLoader.getClassLoader();
        // 如果Hot类被修改了，那么会重新加载，hotClass也会返回新的
        hotClazz = hotSwapCL.loadClass(className);
    }
}

