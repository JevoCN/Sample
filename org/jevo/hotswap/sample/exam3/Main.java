package org.jevo.hotswap.sample.exam3;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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
        //添加检测线程
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
假设业务逻辑改变，要求SampleDyService的doBusiness打印出"hello girl"。新编译好的SampleDyService已经覆盖了原来的类。在不启动应用程序前提条件下如何更新新的业务逻辑呢？
  分俩部来完成
  第一步，在Main类里添加notifyReLoad方法，用新的classloader重新生成SampleDyService实例
    
     */
    public void notifyReLoad() throws Exception {
        service.close();
        // 清除相关引用

        DyLoader loader = new DyLoader();
        service = (IDyService) loader.loadFromCustomRepository(
                "org.jevo.hotswap.sample.exam3.SampleDyService").newInstance();
        service.start();

    }

    /*
第二步：使用某种机制检测来检测SampleDyService.class已经改变，如可以通过上面的例子启动一个线程检测SampleDyService.class是否被改变，如果改变则调用Main.notifyReLoad().也可以采用主动通知方式，如web应用中，提供这样的界面调用。在此例子中提供一个检测线程。
    
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

