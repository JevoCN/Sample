package org.jevo.hotswap.sample.hot;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:54:52
 * To change this template use File | Settings | File Templates.
 */

/**
 �����ǻ���ʵ�ִ��룬��Ҫ���Ϊ��
 1��  HotAgent��Ԥ���أ�
 2��  ClassTransform���ڼ���class��ʱ������޸�class���ֽ��룩��������û�õ�
 3��  ReloadTask��class��ʱ�����������ϴ�������ο���
 4��  META-INF/MANIFEST.MF����Ϊ��������һ��֧��class�ض��壻��������Ԥ�����ࣩ
 Can-Redefine-Classes: true
 Premain-Class: org.jevo.hotswap.sample.hot.HotAgent
 5��  ��������������jar�ļ������ˣ�����Ѿ���ɣ�����Ϊ��д�������ļ�����
 6��  �½�һ��java���̣���дһ��java�߼��࣬����дһ��Test�࣬�ڸò������е����߼���ķ��������濴�²��������

 ���в����ࣺ java �Cjavaagent:agent.jar org.jevo.hotswap.sample.hot.BeanTest
            -javaagent:D:\Project\test\lib\myagent.jar

 */
public class BeanTest {
    public void test1() {
//        System.out.println("============================");
        System.out.println("���¶�����");
    }

    public static void main(String[] args) throws InterruptedException {
        BeanTest c1 = new BeanTest();
        while (true) {
            c1.test1();
            Thread.sleep(5000);
        }
    }

}

