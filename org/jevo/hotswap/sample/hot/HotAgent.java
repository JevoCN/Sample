package org.jevo.hotswap.sample.hot;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Timer;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:51:09
 * To change this template use File | Settings | File Templates.
 */

/**
 Tomcat�е�class�ļ���ͨ��org.apache.catalina.loader. WebappClassLoaderװ�ص�,ͬ�����ǿ����������ԣ����Թ�����jsp�������ƣ����Բ���Ͳ�˵�ˣ�ֻ˵һ�½����
  ���Ȳ��������£����ڱ���classloader ���ص�class�ļ�������classloaderʼ����ͬһ��WebappClassLoader���������������ˣ������������ʵ����Ͳ�������Ϊtomcat��ʹ��һ���µ�classloader�������޸Ĺ���class�ˣ����Ҷ�����״̬��ʵ����֮ǰ��ʵ��ӵ�е����Ժ�״̬�������棬�����´�ִ��ʱӵ�����µ�class���߼���������Ȳ��������֮������ʵÿ��ʵ��ֻ�Ǳ����˸�ʵ����״̬���ԣ�����ͨ�����л�������ܿ��������а�����״̬�����յ��߼����Ǵ�����class�ļ��У���
 �����class�ض�����ͨ����java.lang.instrumentʵ�ֵģ�����ɲο�����ĵ���
  �������ǿ�һ�����ͨ�������޸��ڴ��е�class�ֽ��룺
 */
public class HotAgent {
    protected static Set<String> clsnames = new TreeSet<String>();

    public static void premain(String agentArgs, Instrumentation inst) throws Exception {
        ClassFileTransformer transformer = new ClassTransform(inst);
        inst.addTransformer(transformer);
        System.out.println("�Ƿ�֧������ض��壺" + inst.isRedefineClassesSupported());
        Timer timer = new Timer();
        timer.schedule(new ReloadTask(inst), 2000, 2000);
    }
}

