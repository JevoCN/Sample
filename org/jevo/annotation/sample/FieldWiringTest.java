package org.jevo.annotation.sample;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-3-31
 * Time: 22:08:26
 * To change this template use File | Settings | File Templates.
 */

/**  �����ࣺ
 * http://java.chinaitlab.com/base/873248_2.html
 *    ִ�н����

    SqlMapClient[sqlMap=com/annotation/sql-map-config-B.xml,type=B]

    SqlMapClient[sqlMap=com/annotation/sql-map-config-A.xml,type=A]

    ��ִ�н������˵��DataSource��Դ�Ѿ���������ȷ��ע���ˡ�


 */
public class FieldWiringTest {

    public static void main(String args[]) throws Exception {

        MyAnnotationBeanProcessor processor = new MyAnnotationBeanProcessor();

        MyService b = new MyService();

        processor.wire(b, new DataSourceWiring()); // ע��DataSource��Դ

        System.out.println(b.selectForObjectFromB("", null));

        System.out.println(b.selectForObjectFromA("", null));

    }

}


