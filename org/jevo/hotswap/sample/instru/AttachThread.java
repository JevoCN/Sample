package org.jevo.hotswap.sample.instru;

import java.util.List;
//import com.sun.tools.attach.VirtualMachine;
//import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-5-13
 * Time: 17:15:02
 * To change this template use File | Settings | File Templates.
 */

public class AttachThread extends Thread {
//  private final List<VirtualMachineDescriptor> listBefore;
//         private final String jar;
//
//         AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
//            listBefore = vms;  // ��¼��������ʱ�� VM ����
//            jar = attachJar;
//        }
//
//        public void run() {
//            VirtualMachine vm = null;
//            List<VirtualMachineDescriptor> listAfter = null;
//            try {
//                int count = 0;
//                while (true) {
//                    listAfter = VirtualMachine.list();
//                    for (VirtualMachineDescriptor vmd : listAfter) {
//                        if (!listBefore.contains(vmd)) {
// // ��� VM �����ӣ����Ǿ���Ϊ�Ǳ���ص� VM ������
// // ��ʱ�����ǿ�ʼ������ VM
//                            vm = VirtualMachine.attach(vmd);
//                            break;
//                        }
//                    }
//                    Thread.sleep(500);
//                    count++;
//                    if (null != vm || count >= 10) {
//                        break;
//                    }
//                }
//                vm.loadAgent(jar);
//                vm.detach();
//            } catch (Exception e) {
//                 ignore
//            }
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
//         new AttachThread("TestInstrument1.jar", VirtualMachine.list()).start();

    }
}
//         ����ʱ����������������������������̵߳� main ������Ȼ���� 5 �����ڣ�������ģ�� JVM �ļ�ع��̣��������������������� Jar �ļ� :

//          java �C javaagent:TestInstrument2.jar �C cp TestInstrument2.jar TestMainInJar



