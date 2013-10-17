package org.jevo.hotswap.sample.instru;

import java.util.List;
//import com.sun.tools.attach.VirtualMachine;
//import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
//            listBefore = vms;  // 记录程序启动时的 VM 集合
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
// // 如果 VM 有增加，我们就认为是被监控的 VM 启动了
// // 这时，我们开始监控这个 VM
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
//         运行时，可以首先运行上面这个启动新线程的 main 函数，然后，在 5 秒钟内（仅仅简单模拟 JVM 的监控过程）运行如下命令启动测试 Jar 文件 :

//          java – javaagent:TestInstrument2.jar – cp TestInstrument2.jar TestMainInJar



