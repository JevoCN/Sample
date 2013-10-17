package org.jevo.hotswap.sample.instrum;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:52:15
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    public void test1() {
        System.out.println("Do This ...");
//        System.out.println("Do This Again.");
    }

    public static void main(String[] args) throws InterruptedException {
        Main c1 = new Main();
        while (true) {
            c1.test1();
            Thread.sleep(5000);
        }
    }
}

/**
 这种情况就是在你根本就不知道  premain的时候 用 agentmain 也能达到目的。

 如果  java.lang.UnsatisfiedLinkError: no attach in java.library.path

 则

  System.setProperty("java.library.path","D:\\Program Files (x86)\\Java\\jdk1.6.0_21\\jre\\bin");
  Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
  fieldSysPath.setAccessible(true);
  fieldSysPath.set(null, null);

 new AttachThread("D:\\bainiangzi\\Agent\\Agent_fat.jar", VirtualMachine.list()).start();

 vm = VirtualMachine.attach(listBefore.get(0));

 vm.loadAgent(jar);
 vm.detach();

*/ 