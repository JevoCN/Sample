package org.jevo.future.sample;

import javax.swing.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-15
 * Time: 16:03:55
 * To change this template use File | Settings | File Templates.
 */

public abstract class SwingWorker {
    private Object value;  //the running result
    private boolean finished = false;

    private static class ThreadVar {
        private Thread thread;

        ThreadVar(Thread t) {
            thread = t;
        }

        synchronized Thread get() {
            return thread;
        }

        synchronized void clear() {
            thread = null;
        }
    }

    private ThreadVar threadVar;

    /**
     * 返回当前线程运行结果。
     */
    protected synchronized Object getValue() {
        return value;
    }

    /**
     * 设置当前线程运行结果
     */
    private synchronized void setValue(Object x) {
        value = x;
    }

    /**
     * 调用都创建计算逻辑，将运算结果返回
     */
    public abstract Object construct();

    public void finished() {
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public void interrupt() {
        Thread t = threadVar.get();
        if (t != null) {
            t.interrupt();
        }
        threadVar.clear();
    }

    public void stop() {
        Thread t = threadVar.get();
        if(t!=null) {
            t.stop();
        }
        threadVar.clear();
    }

    /**
     * 返回 construct方法运行结果。
     */
    public Object get() {
        while (true) {
            Thread t = threadVar.get();
            if (t == null) {
                return getValue();
            }
            try {
                t.join();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
    }


    public SwingWorker() {
        final Runnable doFinished = new Runnable() {
            public void run() {
                finished();
            }
        };

        Runnable doConstruct = new Runnable() {
            public void run() {
                try {
                    setValue(construct());
                }
                finally {
                    threadVar.clear();
                }

                SwingUtilities.invokeLater(doFinished);
            }
        };

        Thread t = new Thread(doConstruct);
        threadVar = new ThreadVar(t);
    }

    /**
     * Start the worker thread.
     */
    public void start() {
        finished = false;
        Thread t = threadVar.get();
        if (t != null) {
            t.start();
        }
    }

    private Object objUser = null;

    public void setUserObject(Object obj) {
        objUser = obj;
    }

    public Object getUserObject() {
        return objUser;
    }
}


