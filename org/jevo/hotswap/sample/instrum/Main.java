package org.jevo.hotswap.sample.instrum;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
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
 �������������������Ͳ�֪��  premain��ʱ�� �� agentmain Ҳ�ܴﵽĿ�ġ�

 ���  java.lang.UnsatisfiedLinkError: no attach in java.library.path

 ��

  System.setProperty("java.library.path","D:\\Program Files (x86)\\Java\\jdk1.6.0_21\\jre\\bin");
  Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
  fieldSysPath.setAccessible(true);
  fieldSysPath.set(null, null);

 new AttachThread("D:\\bainiangzi\\Agent\\Agent_fat.jar", VirtualMachine.list()).start();

 vm = VirtualMachine.attach(listBefore.get(0));

 vm.loadAgent(jar);
 vm.detach();

*/ 