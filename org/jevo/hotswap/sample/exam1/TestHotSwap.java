package org.jevo.hotswap.sample.exam1;

import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-16
 * Time: 17:43:35
 * To change this template use File | Settings | File Templates.
 */

public class TestHotSwap {
    public static void main(String[] args) throws Exception {
        //�����̣߳����class�ļ����޸ģ������滻
        Thread t = new Thread(new MonitorHotSwap());
        t.start();
    }
}

class MonitorHotSwap implements Runnable {
    // Hot���������޸ģ����������ȼ���
    private String className = "org.jevo.hotswap.sample.exam1.Hot";
    private Class hotClazz = null;
    private HotSwapURLClassLoader hotSwapCL = null;

    @Override
    public void run() {
        try {
            while (true) {
                initLoad();
//                Hot hot = (Hot)hotClazz.newInstance(); ����ת��ΪHot���ͣ����׳�java.lang.ClassCastException: org.jevo.hotswap.sample.Hot cannot be cast to org.jevo.hotswap.sample.Hot
                Object hot = hotClazz.newInstance();
                Method m = hotClazz.getMethod("hot");
                m.invoke(hot, null); //��ӡ�������Ϣ
                // ÿ��10�����¼���һ��
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ����class
     */
    void initLoad() throws Exception {
        hotSwapCL = HotSwapURLClassLoader.getClassLoader();
        // ���Hot�౻�޸��ˣ���ô�����¼��أ�hotClassҲ�᷵���µ�
        hotClazz = hotSwapCL.loadClass(className);
    }
}

