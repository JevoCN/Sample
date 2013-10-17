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
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:28:43
 * To change this template use File | Settings | File Templates.
 */

/**
http://blog.sina.com.cn/s/blog_4e045abe0100a01l.html
 �����ͨ��Classloader��ת���ࡣbootstrap loader ����load jdk��class(java.*,javax.*), system class loader��bootstrap������,����load ������chasspathָ���ı�����ClassLoader���ֽ���ת��ΪClass�࣬��Щ�ֽ���������Դ�ļ���Ҳ������Դ������������ݿ⡣ת�������ǵ���ClassLoader�ṩ��final defineClass(className, classBytes, 0, classBytes.length)������ʵ�֡���Ҫ��ס�����������һ�����Ψһ��ʶ��ͨ����İ���+����+װ�ش����ClassLoader��ͬһ��ClassLoaderʵ��ֻ��װ��Classһ�Σ��ظ�װ�ؽ��׳��ظ��ඨ���쳣��

 �Զ����ClassLoader��̬�ⲿjar�ļ��������ز���jar��������ļ�
 http://bbs.csdn.net/topics/370092179
 ����jarӦ�ó�����������jar�������ַ���
 http://cuixiaodong214.blog.163.com/blog/static/9516398200991351053605/
 classloader ��ϵѧϰ
http://www.cnblogs.com/realviv/articles/1906110.html
 JVM���� ֮ClassLoader������ת�أ�
http://sishuok.com/forum/blogPost/list/324.html
 ��osgi������classLoader�Ĵ��ܽᣨ�������ClassLoader�� .
http://blog.csdn.net/turkeyzhou/article/details/2792876
 �������Java ClassLoaderԭ�� .
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
        return this.defineClass(className, classBytes, 0, classBytes.length);//������
    }

}

