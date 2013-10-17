package org.jevo.hotswap.sample.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:23:29
 * To change this template use File | Settings | File Templates.
 */
//http://waterdh.iteye.com/blog/520399
public class MyClassLoaderv2 extends ClassLoader {
    public static ConcurrentHashMap<String, Class<?>> classes = new ConcurrentHashMap<String, Class<?>>();

    public static MyClassLoaderv2 instance = new MyClassLoaderv2();

    //�����Զ���Classloader, ��ָ����Classloader
    public MyClassLoaderv2() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> load(String name, byte[] data, boolean resolve) {
        Class<?> klass = defineClass(name, data, 0, data.length);
        if (resolve)
            resolveClass(klass);
        classes.put(name, klass);
        return klass;
    }

    //�Զ���Classloader����ͨ������loadClass����������ָ��package�µ�����м��أ�����ȫ��ί�и�Classloader������ �� ������Ҫ�õ�classloader��defineClass�������Ա����ǵ�classloader������������ָ��λ�õ�class�ļ���
    //�Զ���classloader�������߼�,findClassFile() �����Լ�����Ĵ���������Ҫ�����ļ�������
//    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        Object value = classes.get(name); // ��黺��
//        if (value != null && value != INVALID) {
//            Class<?> klass = (Class<?>) value;
//            if (resolve)
//                resolveClass(klass);
//            return klass;
//
//        } else { // �����в�����
//            byte[] data = read(findClassFile(name)); // ��ȡ���ļ�
//            if (data == null)
//                return super.loadClass(name, resolve); // ���ɸ�classloaderȥload���ļ�
//            else {
//                try {
//                    lock.lock();
//                    Object cc = classes.get(name); // ��黺��
//                    if (cc != null) {
//                        return (Class<?>) cc;
//                    } else
//                        return instance.load(name, data, resolve); // �Լ�load���ļ�
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }

    //defineClass ����������������ʵ�ֲַ�ʽ��̬���㣬hadoop mapreduceӦ�þ���ʹ������һ��������֤��������Ⱥ��Ĵ�����Ĵ�������С�


    /**
    * ���³�ʼ�����Ա�ʵ��������ָ������
     * ��ʵÿ��reset�������һ���µ�classloaderʵ���� ��ʵ�������������ʵ��װ�صĵ���ȫ�������պ�ű����գ�����Ƚϱ��ŵ�ȫ��reset��������������δ�����ڴ�������⡣
    */
   public static void reset() {
       instance = new MyClassLoaderv2();
       classes.clear();
   }

    public static void invoke(String method ,Object[] obj, Class<?>[] parameterTypes){
            try {
                Object cls = MyClassLoaderv2.instance.loadClass("��ȫ�޶���", true).newInstance();
                cls.getClass().getMethod(method,parameterTypes).invoke(cls ,obj);
            } catch (Exception e) {
                             
            }
    }

    /**
     ����ֻ��ͨ������ķ�ʽ���ã�����classloader�İ�ȫ���ƣ�ͬ�����࣬��classloaderװ�ص��� �� ��classloaderװ�ص��಻�ܻ���ת����

        ��Ҫǿ�����ǣ������ص������غ����еı������ᱻ���³�ʼ�������һЩ��Ҫ���ݱ������ǵý��ɸ�Classloader������

         �÷�ʽ����һЩȱ�㣬�Ѻ����߼��鲢��һ�𣬽����������ȥ���Ӷ�Ӱ����Ӧ�ñ���Ľṹ
     
     */

    

    //��������Classloaderȥloadȫ�޶���ָ����class�����load��������ί�и���classloader�ĸ�classloader��ֱ����root classloader��
//    protected synchronized Class<?> loadClass(String name, boolean resolve)
//            throws ClassNotFoundException {
//// First, check if the class has already been loaded
//        Class c = findLoadedClass(name);
//        if (c == null) {
//            try {
//                if (parent != null) {
//                    c = parent.loadClass(name, false);
//                } else {
//                    c = findBootstrapClass0(name);
//                }
//            } catch (ClassNotFoundException e) {
//                // If still not found, then invoke findClass in order
//                // to find the class.
//                c = findClass(name);
//            }
//        }
//        if (resolve) {
//            resolveClass(c);
//        }
//        return c;
//    }


}

