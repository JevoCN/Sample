package org.jevo.hotswap.sample.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:23:29
 * To change this template use File | Settings | File Templates.
 */
//http://waterdh.iteye.com/blog/520399
public class MyClassLoaderv2 extends ClassLoader {
    public static ConcurrentHashMap<String, Class<?>> classes = new ConcurrentHashMap<String, Class<?>>();

    public static MyClassLoaderv2 instance = new MyClassLoaderv2();

    //构造自定义Classloader, 并指定父Classloader
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

    //自定义Classloader可以通过重载loadClass方法，仅对指定package下的类进行加载，其余全部委托父Classloader来加载 ， 这里需要用到classloader的defineClass方法，以便我们的classloader可以载入任意指定位置的class文件。
    //自定义classloader的载入逻辑,findClassFile() 就是自己定义的从哪里找需要的类文件方法。
//    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
//        Object value = classes.get(name); // 检查缓存
//        if (value != null && value != INVALID) {
//            Class<?> klass = (Class<?>) value;
//            if (resolve)
//                resolveClass(klass);
//            return klass;
//
//        } else { // 缓存中不存在
//            byte[] data = read(findClassFile(name)); // 读取类文件
//            if (data == null)
//                return super.loadClass(name, resolve); // 交由父classloader去load类文件
//            else {
//                try {
//                    lock.lock();
//                    Object cc = classes.get(name); // 检查缓存
//                    if (cc != null) {
//                        return (Class<?>) cc;
//                    } else
//                        return instance.load(name, data, resolve); // 自己load类文件
//                } finally {
//                    lock.unlock();
//                }
//            }
//        }
//    }

    //defineClass 方法可以灵活地用来实现分布式动态计算，hadoop mapreduce应该就是使用了这一方法来保证服务器集群间的处理类的传输和运行。


    /**
    * 重新初始化，以便实现重载所指定的类
     * 其实每次reset都会产生一个新的classloader实例， 该实例会在所有这个实例装载的的类全部被回收后才被回收，这里比较奔放的全部reset掉，经过测试尚未发现内存溢出问题。
    */
   public static void reset() {
       instance = new MyClassLoaderv2();
       classes.clear();
   }

    public static void invoke(String method ,Object[] obj, Class<?>[] parameterTypes){
            try {
                Object cls = MyClassLoaderv2.instance.loadClass("类全限定名", true).newInstance();
                cls.getClass().getMethod(method,parameterTypes).invoke(cls ,obj);
            } catch (Exception e) {
                             
            }
    }

    /**
     这里只能通过反射的方式调用，由于classloader的安全机制，同样的类，父classloader装载的类 和 子classloader装载的类不能互相转换。

        需要强调的是，被重载的类重载后所有的变量都会被重新初始化，因此一些重要数据变量还是得交由父Classloader来管理。

         该方式存在一些缺点，把核心逻辑归并到一起，将变量分离出去，从而影响了应用本身的结构
     
     */

    

    //由最外层的Classloader去load全限定名指定的class，如果load不到，则委托给该classloader的父classloader，直至到root classloader。
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

