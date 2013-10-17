package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:08:26
 * To change this template use File | Settings | File Templates.
 */

/**  测试类：
 * http://java.chinaitlab.com/base/873248_2.html
 *    执行结果：

    SqlMapClient[sqlMap=com/annotation/sql-map-config-B.xml,type=B]

    SqlMapClient[sqlMap=com/annotation/sql-map-config-A.xml,type=A]

    由执行结果可以说明DataSource资源已经被我们正确的注入了。


 */
public class FieldWiringTest {

    public static void main(String args[]) throws Exception {

        MyAnnotationBeanProcessor processor = new MyAnnotationBeanProcessor();

        MyService b = new MyService();

        processor.wire(b, new DataSourceWiring()); // 注入DataSource资源

        System.out.println(b.selectForObjectFromB("", null));

        System.out.println(b.selectForObjectFromA("", null));

    }

}


