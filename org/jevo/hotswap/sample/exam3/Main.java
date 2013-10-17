package org.jevo.hotswap.sample.exam3;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:32:58
 * To change this template use File | Settings | File Templates.
 */

public class Main {
    private IDyService service = null;

//    public Main() throws Exception {
//        DyLoader loader = new DyLoader();
//        service = (IDyService) loader.loadFromCustomRepository(
//                "com.gctech.service.test.dyloader.SampleDyService").newInstance();
//        service.start();
//
//        while (true) {
//
//            service.doBusiness();
//            Thread.sleep(1000 * 3);
//        }
//    }

    public Main() throws Exception {
        DyLoader loader = new DyLoader();
        service = (IDyService) loader.loadFromCustomRepository(
                "org.jevo.hotswap.sample.exam3.SampleDyService").newInstance();
        service.start();
        //��Ӽ���߳�
        DyServciceChecker checker = new DyServciceChecker();
        checker.setMain(this);
        checker.start();

        while (true) {
            service.doBusiness();
            Thread.sleep(1000 * 3);
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }


    /*
����ҵ���߼��ı䣬Ҫ��SampleDyService��doBusiness��ӡ��"hello girl"���±���õ�SampleDyService�Ѿ�������ԭ�����ࡣ�ڲ�����Ӧ�ó���ǰ����������θ����µ�ҵ���߼��أ�
  �����������
  ��һ������Main�������notifyReLoad���������µ�classloader��������SampleDyServiceʵ��
    
     */
    public void notifyReLoad() throws Exception {
        service.close();
        // ����������

        DyLoader loader = new DyLoader();
        service = (IDyService) loader.loadFromCustomRepository(
                "org.jevo.hotswap.sample.exam3.SampleDyService").newInstance();
        service.start();

    }

    /*
�ڶ�����ʹ��ĳ�ֻ��Ƽ�������SampleDyService.class�Ѿ��ı䣬�����ͨ���������������һ���̼߳��SampleDyService.class�Ƿ񱻸ı䣬����ı������Main.notifyReLoad().Ҳ���Բ�������֪ͨ��ʽ����webӦ���У��ṩ�����Ľ�����á��ڴ��������ṩһ������̡߳�
    
     */

    public class DyServciceChecker extends Thread {
        Main main = null;

        public DyServciceChecker() {
        }

        public void setMain(Main main) {
            this.main = main;

        }

        public void run() {
            int itest = 0;
            while (!interrupted()) {
                try {
                    boolean isChanged = false;
//                    boolean isChanged = check();
                    if(itest > 5) {
                        itest = 0;
                        isChanged = true;
                    }
                    if (isChanged) {
                        System.err.println("change...");
                        main.notifyReLoad();

                    } else {
                        Thread.sleep(1000 * 5);
                    }
                    System.err.println("..." + isChanged);
                    itest++;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }


}

