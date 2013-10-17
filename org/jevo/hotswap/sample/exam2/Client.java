package org.jevo.hotswap.sample.exam2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
        //getDeclaredConstructor可以返回指定参数的构造函数，而getConstructor只能放回指定参数的public的构造函数区别只是后者只可以返回公有的构造函数
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
                System.out.println(m.invoke(server, null)); //打印出相关信息)
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
 * ClassCastException是JVM在检测到两个类型间转换不兼容时引发的运行时异常。此类错误通常会终止用户请求。在执行任何子系统的应用程序代码时都有可能发生ClassCastException异常。通过转换，可以指示Java编译器将给定类型的变量作为另一种变量来处理。对基础类型和用户定义类型都可以转换。Java语言规范定义了允许的转换，其中大多数可在编译时进行验证。不过，某些转换还需要运行时验证。如果在此运行时验证过程中检测到不兼容，JVM就会引发ClassCastException异常。
 * http://www.iteye.com/topic/83978
 * 
 ClassLoader是允许JVM查找和加载类的一种Java类。JVM有内置的ClassLoader。不过，应用程序可以定义自定义的ClassLoader。应用程序定义新的ClassLoader通常出于以下两种原因：

   1.        自定义和扩展JVM加载类的方式。例如，增加对新的类库(网络、加密文件等)的支持。

   2.        划分JVM名称空间，避免名称冲突。例如，可以利用划分技术同时运行同一应用程序的多个版本(基于空间的划分)。此项技术在应用服务器(如WebLogic Server)内的另一个重要用途是启用应用程序热重新部署，即在不重新启动JVM的情况下启动应用程序的新版本(基于时间的划分)。

   ClassLoader按层级方式进行组织。除系统BootClassLoader外，其它ClassLoader都必须有父ClassLoader。

   在理解类加载的时候，需要注意以下几点：

   1.        永远无法在同一ClassLoader中重新加载类。“热重新部署”需要使用新的ClassLoader。每个类对其ClassLoader的引用都是不可变的：this.getClass().getClassLoader()。

   2.        在加载类之前，ClassLoader始终会先询问其父ClassLoader(委托模型)。这意味着将永远无法重写“核心”类。

   3.        同级ClassLoader间互不了解。

   4.        由不同ClassLoader加载的同一类文件也会被视为不同的类，即便每个字节都完全相同。这是ClassCastException的一个典型原因。

   5.        可以使用Thread.setContextClassLoader(a)将ClassLoader连接到线程的上下文。

   基于以上的基本原理，可以加深大家对ClassCastException的理解，和在碰到问题时提供一种解决问题的思路。




 最简单的理解反射机制就是：从文件中创建对象。大体讲一下这个机制的实现。一般常用的两个类：Class类和Property类。Property类用来加载文件中的类名，记住是类名，然后由Class类的forName方法加载类名后返回Class对象，再使用newInstance方法返回对象(将Object类型进行强行转换)，就可以得到我们想要的对象了。这样做的好处是，我们不用在使用new关键字来创建对象，因为使用new关键字来创建对象时，会增加我们程序中，层与层之间的耦合度，也就是增加了我们修改代码的力度。(这点记住，以后会深有体会的)

 
 http://hideto.iteye.com/blog/98178  深入了解Java ClassLoader、Bytecode 、ASM、cglib
 http://blog.csdn.net/lcj8/article/details/2218151   深入了解Java ClassLoader、Bytecode 、ASM、cglib 
 **/


/**
   图解classloader加载class的流程及自定义ClassLoader http://cuixiaodong214.blog.163.com/blog/static/951639820099135859761/
 自定义classloader http://blog.sina.com.cn/s/blog_4e045abe0100a01l.html
 自定义ClassLoader实现java应用核心逻辑模块热部署 http://waterdh.iteye.com/blog/520399
 自定义ClassLoader详解_将web程序部分业务转化到本地程序执行 .       http://blog.csdn.net/ziyunyangyong/article/details/7940002
 
 */