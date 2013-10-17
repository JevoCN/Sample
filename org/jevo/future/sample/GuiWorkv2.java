package org.jevo.future.sample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-15
 * Time: 16:25:55
 * To change this template use File | Settings | File Templates.
 */

public abstract class GuiWorkv2 extends SwingWorker
{
    public abstract Object work();

    private Object _result = null;

    public Object getResult()
    {
        return _result;
    }

    private WaitDialog displayer = null;

    public Object construct(){
        try
        {
            _result = work();
        } catch (Throwable t){

        }

        return null;
    }

    public static Object runGui(GuiWorkv2 gui) {
        return gui.getResult();

    }



    public static void main(String[] args) {
        
    }
}


