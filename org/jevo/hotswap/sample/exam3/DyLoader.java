package org.jevo.hotswap.sample.exam3;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Iterator;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:28:43
 * To change this template use File | Settings | File Templates.
 */

/**
http://blog.sina.com.cn/s/blog_4e045abe0100a01l.html
 虚拟机通过Classloader来转载类。bootstrap loader 负责load jdk的class(java.*,javax.*), system class loader是bootstrap的子类,负责load 所有在chasspath指定的变量。ClassLoader将字节流转化为Class类，这些字节流可能来源文件，也可能来源于网络或者数据库。转化方法是调用ClassLoader提供的final defineClass(className, classBytes, 0, classBytes.length)方法来实现。需要记住的是虚拟机里一个类的唯一标识是通过类的包名+类名+装载此类的ClassLoader。同一个ClassLoader实例只能装载Class一次，重复装载将抛出重复类定义异常。

 自定义的ClassLoader动态外部jar文件，但加载不到jar里的配置文件
 http://bbs.csdn.net/topics/370092179
 运行jar应用程序引用其他jar包的四种方法
 http://cuixiaodong214.blog.163.com/blog/static/9516398200991351053605/
 classloader 体系学习
http://www.cnblogs.com/realviv/articles/1906110.html
 JVM基础 之ClassLoader分析（转载）
http://sishuok.com/forum/blogPost/list/324.html
 由osgi引出的classLoader的大总结（整理理解ClassLoader） .
http://blog.csdn.net/turkeyzhou/article/details/2792876
 深入分析Java ClassLoader原理 .
http://blog.csdn.net/xyang81/article/details/7292380 
 
 */
public class DyLoader extends ClassLoader {
    public DyLoader() {
        super(DyLoader.class.getClassLoader());
    }

    public Class loadFromCustomRepository(String className) {
        String classPath = System.getProperty("java.class.path");
        List classRepository = new ArrayList();

        if ((classPath != null) && !(classPath.equals(""))) {
            StringTokenizer tokenizer = new StringTokenizer(classPath,
                    File.pathSeparator);
            while (tokenizer.hasMoreTokens()) {
                classRepository.add(tokenizer.nextToken());
            }
        }
        Iterator dirs = classRepository.iterator();
        byte[] classBytes = null;

        while (dirs.hasNext()) {
            String dir = (String) dirs.next();
            //replace '.' in the class name with File.separatorChar & append .class to the name
            String classFileName = className.replace('.', File.separatorChar);
            classFileName += ".class";
            try {
                File file = new File(dir + File.separatorChar + classFileName);
                if (file.exists()) {
                    InputStream is = new FileInputStream(file);

                    classBytes = new byte[is.available()];
                    is.read(classBytes);
                    break;
                }
            }
            catch (IOException ex) {
                System.out.println("IOException raised while reading class file data");
                ex.printStackTrace();
                return null;
            }
        }
        return this.defineClass(className, classBytes, 0, classBytes.length);//加载类
    }

}

