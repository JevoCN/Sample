package org.jevo.hotswap.sample.instrum;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:44:57
 * To change this template use File | Settings | File Templates.
 */

public class AgentMain {
        //agentmain 需要在 main 函数开始运行后才启动，这样的时机应该如何确定呢，这样的功能又如何实现呢？
    public static void agentmain(String agentArgs, final Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        new Reloader(inst).start();
        System.out.println("Agent Main Done");
    }
}

//http://blog.sina.com.cn/s/blog_605f5b4f01010i3b.html
//http://blog.csdn.net/kangojian/article/details/8782575