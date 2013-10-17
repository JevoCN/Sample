package org.jevo.future.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-15
 * Time: 16:06:14
 * To change this template use File | Settings | File Templates.
 */

public abstract class GuiWork extends SwingWorker {

    private WaitDialog w_dlg = null;

    public GuiWork(WaitDialog dlg) {
        w_dlg = dlg;
    }

    public void finished()
    {
        if(w_dlg!=null)
            w_dlg.dispose();
    }

    public static void main(String[] args) {
        WaitDialog dlg = new WaitDialog();
        GuiWork worker = new GuiWork(dlg) {
            public Object construct() {
                return "hello world";

            }

            public void finished() {
                super.finished();
                //取线程运行返回的结果
                Object obj = this.get();
                System.err.println("return is " + obj);
            }
        };
        
        worker.start();
        dlg.setVisible(true);
    }
}

