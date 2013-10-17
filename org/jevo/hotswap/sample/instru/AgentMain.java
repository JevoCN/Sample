package org.jevo.hotswap.sample.instru;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:13:48
 * To change this template use File | Settings | File Templates.
 */

public class AgentMain {
    //agentmain 需要在 main 函数开始运行后才启动，这样的时机应该如何确定呢，这样的功能又如何实现呢？
    public static void agentmain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        inst.addTransformer(new Transformer(), true);   //注册提供的转换器。
        inst.retransformClasses(TransClass.class);  //重转换提供的类集。
        System.out.println("Agent Main Done");
    }

/*
   1. public static void premain(String agentArgs, Instrumentation inst) {
   2.  try{
   3.   byte[] buf = getWrappedClass("org.axman.test.TestClass",new String[]{"test"});
   4.   Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("org.axman.test.TestClass");
   5.      ClassDefinition[] definitions = new ClassDefinition[] { new ClassDefinition(clazz, buf) };
   6.   inst.redefineClasses(definitions);
   7.
   8.     }catch(Exception e){e.printStackTrace();}
   9. }
    
     */

    /*
# public static void premain(String agentArgs, final Instrumentation inst) {
#  new Redefiner(inst).start();
# }
    
     */
}



