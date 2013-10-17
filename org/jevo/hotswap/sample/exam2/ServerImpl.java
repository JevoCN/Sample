package org.jevo.hotswap.sample.exam2;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-16
 * Time: 17:18:10
 * To change this template use File | Settings | File Templates.
 */

public class ServerImpl implements ServerItf {
    // catch the class being unloaded from the VM
//    static Object reporter = new Reporter(ServerImpl.class);

    public String getQuote() {
        return "i love you";
//        return "you love me";
    }
}
// file ServerImpl.java. Place this file

// in a subdirectory named 'server'. 
//class Reporter {
//    Class cls;
//
//    Reporter(Class cls) {
//        this.cls = cls;
//        System.out.println("ServerImpl class " + cls.hashCode()
//                + " loaded into VM");
//    }
//
//    protected void finalize() {
//        System.out.println("ServerImpl class " + cls.hashCode()
//                + " unloaded from VM");
//    }
//}
