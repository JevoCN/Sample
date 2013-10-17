package org.jevo.hotswap.sample.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-16
 * Time: 17:16:28
 * To change this template use File | Settings | File Templates.
 */

public class Client {
    static ClassLoader cl;
//    static ServerImpl server;
    static Object server;
    static Class hotClazz = null;

    public static void loadNewVersionOfServer() throws Exception {
//        URL[] serverURLs = new URL[]{new URL("file://D:/Project/test/out/")};
        synchronized(Client.class) {
        if(cl == null)
            cl = new MyClassLoader(new URL("file://D:/Project/test/out/"));
        }
        hotClazz = cl.loadClass("org.jevo.hotswap.sample.ServerImpl");
        //getDeclaredConstructor���Է���ָ�������Ĺ��캯������getConstructorֻ�ܷŻ�ָ��������public�Ĺ��캯������ֻ�Ǻ���ֻ���Է��ع��еĹ��캯��
//        Constructor constructore = custClass.getConstructor();
//        server = (ServerImpl)constructore.newInstance();
//        Object obj = custClass.newInstance();
//        System.err.println("type is " + (obj.getClass().getName()) + ", " + (obj instanceof ServerImpl));
//        server = (ServerImpl) custClass.newInstance();
        server = hotClazz.newInstance();
    }

    public static void test() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loadNewVersionOfServer();
        while (true) {
            System.out.println("Enter QUOTE, RELOAD, GC, or QUIT: ");
            String cmdRead = br.readLine();
            String cmd = cmdRead.toUpperCase();
            if (cmd.equals("QUIT")) {
                return;
            } else if (cmd.equals("QUOTE")) {
                Method m = hotClazz.getMethod("getQuote");
                System.out.println(m.invoke(server, null)); //��ӡ�������Ϣ)
//                System.out.println(server.getQuote());
            } else if (cmd.equals("RELOAD")) {
                loadNewVersionOfServer();
            } else if (cmd.equals("GC")) {
                System.gc();
                System.runFinalization();
            }
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**  http://hi.baidu.com/mowangsheng/item/d44d8c87db3a37c6ef083dd5
 * ClassCastException��JVM�ڼ�⵽�������ͼ�ת��������ʱ����������ʱ�쳣���������ͨ������ֹ�û�������ִ���κ���ϵͳ��Ӧ�ó������ʱ���п��ܷ���ClassCastException�쳣��ͨ��ת��������ָʾJava���������������͵ı�����Ϊ��һ�ֱ����������Ի������ͺ��û��������Ͷ�����ת����Java���Թ淶�����������ת�������д�������ڱ���ʱ������֤��������ĳЩת������Ҫ����ʱ��֤������ڴ�����ʱ��֤�����м�⵽�����ݣ�JVM�ͻ�����ClassCastException�쳣��
 * http://www.iteye.com/topic/83978
 * 
 ClassLoader������JVM���Һͼ������һ��Java�ࡣJVM�����õ�ClassLoader��������Ӧ�ó�����Զ����Զ����ClassLoader��Ӧ�ó������µ�ClassLoaderͨ��������������ԭ��

   1.        �Զ������չJVM������ķ�ʽ�����磬���Ӷ��µ����(���硢�����ļ���)��֧�֡�

   2.        ����JVM���ƿռ䣬�������Ƴ�ͻ�����磬�������û��ּ���ͬʱ����ͬһӦ�ó���Ķ���汾(���ڿռ�Ļ���)���������Ӧ�÷�����(��WebLogic Server)�ڵ���һ����Ҫ��;������Ӧ�ó��������²��𣬼��ڲ���������JVM�����������Ӧ�ó�����°汾(����ʱ��Ļ���)��

   ClassLoader���㼶��ʽ������֯����ϵͳBootClassLoader�⣬����ClassLoader�������и�ClassLoader��

   ���������ص�ʱ����Ҫע�����¼��㣺

   1.        ��Զ�޷���ͬһClassLoader�����¼����ࡣ�������²�����Ҫʹ���µ�ClassLoader��ÿ�������ClassLoader�����ö��ǲ��ɱ�ģ�this.getClass().getClassLoader()��

   2.        �ڼ�����֮ǰ��ClassLoaderʼ�ջ���ѯ���丸ClassLoader(ί��ģ��)������ζ�Ž���Զ�޷���д�����ġ��ࡣ

   3.        ͬ��ClassLoader�以���˽⡣

   4.        �ɲ�ͬClassLoader���ص�ͬһ���ļ�Ҳ�ᱻ��Ϊ��ͬ���࣬����ÿ���ֽڶ���ȫ��ͬ������ClassCastException��һ������ԭ��

   5.        ����ʹ��Thread.setContextClassLoader(a)��ClassLoader���ӵ��̵߳������ġ�

   �������ϵĻ���ԭ�����Լ����Ҷ�ClassCastException����⣬������������ʱ�ṩһ�ֽ�������˼·��




 ��򵥵���ⷴ����ƾ��ǣ����ļ��д������󡣴��彲һ��������Ƶ�ʵ�֡�һ�㳣�õ������ࣺClass���Property�ࡣProperty�����������ļ��е���������ס��������Ȼ����Class���forName�������������󷵻�Class������ʹ��newInstance�������ض���(��Object���ͽ���ǿ��ת��)���Ϳ��Եõ�������Ҫ�Ķ����ˡ��������ĺô��ǣ����ǲ�����ʹ��new�ؼ���������������Ϊʹ��new�ؼ�������������ʱ�����������ǳ����У������֮�����϶ȣ�Ҳ���������������޸Ĵ�������ȡ�(����ס���Ժ����������)

 
 http://hideto.iteye.com/blog/98178  �����˽�Java ClassLoader��Bytecode ��ASM��cglib
 http://blog.csdn.net/lcj8/article/details/2218151   �����˽�Java ClassLoader��Bytecode ��ASM��cglib 
 **/


/**
   ͼ��classloader����class�����̼��Զ���ClassLoader http://cuixiaodong214.blog.163.com/blog/static/951639820099135859761/
 �Զ���classloader http://blog.sina.com.cn/s/blog_4e045abe0100a01l.html
 �Զ���ClassLoaderʵ��javaӦ�ú����߼�ģ���Ȳ��� http://waterdh.iteye.com/blog/520399
 �Զ���ClassLoader���_��web���򲿷�ҵ��ת�������س���ִ�� .       http://blog.csdn.net/ziyunyangyong/article/details/7940002
 
 */