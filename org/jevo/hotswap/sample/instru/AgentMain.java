package org.jevo.hotswap.sample.instru;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;


/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:13:48
 * To change this template use File | Settings | File Templates.
 */

public class AgentMain {
    //agentmain ��Ҫ�� main ������ʼ���к��������������ʱ��Ӧ�����ȷ���أ������Ĺ��������ʵ���أ�
    public static void agentmain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        inst.addTransformer(new Transformer(), true);   //ע���ṩ��ת������
        inst.retransformClasses(TransClass.class);  //��ת���ṩ���༯��
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



