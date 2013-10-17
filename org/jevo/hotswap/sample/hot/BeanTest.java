package org.jevo.hotswap.sample.hot;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-18
 * Time: 22:54:52
 * To change this template use File | Settings | File Templates.
 */

/**
 以上是基本实现代码，需要组件为：
 1．  HotAgent（预加载）
 2．  ClassTransform（在加载class的时候可以修改class的字节码），本例中没用到
 3．  ReloadTask（class定时加载器，以上代码仅供参考）
 4．  META-INF/MANIFEST.MF内容为：（参数一：支持class重定义；参数二：预加载类）
 Can-Redefine-Classes: true
 Premain-Class: org.jevo.hotswap.sample.hot.HotAgent
 5．  将以上组件打包成jar文件（到此，组件已经完成，下面为编写测试类文件）。
 6．  新建一个java工程，编写一个java逻辑类，并编写一个Test类，在该测试类中调用逻辑类的方法，下面看下测试类代码

 运行测试类： java –javaagent:agent.jar org.jevo.hotswap.sample.hot.BeanTest
            -javaagent:D:\Project\test\lib\myagent.jar

 */
public class BeanTest {
    public void test1() {
//        System.out.println("============================");
        System.out.println("重新定义类");
    }

    public static void main(String[] args) throws InterruptedException {
        BeanTest c1 = new BeanTest();
        while (true) {
            c1.test1();
            Thread.sleep(5000);
        }
    }

}

