package org.jevo.hotswap.sample.instrum;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:44:57
 * To change this template use File | Settings | File Templates.
 */

public class AgentMain {
        //agentmain ��Ҫ�� main ������ʼ���к��������������ʱ��Ӧ�����ȷ���أ������Ĺ��������ʵ���أ�
    public static void agentmain(String agentArgs, final Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        new Reloader(inst).start();
        System.out.println("Agent Main Done");
    }
}

//http://blog.sina.com.cn/s/blog_605f5b4f01010i3b.html
//http://blog.csdn.net/kangojian/article/details/8782575