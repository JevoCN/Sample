package org.jevo.hotswap.sample.exam3;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:32:26
 * To change this template use File | Settings | File Templates.
 */

public class SampleDyService implements IDyService {
    public SampleDyService() {
    }

    public void doBusiness() {
//        System.out.println("hello boy 2");
        System.out.println("hello boy 333");
    }

    public void start() {
        System.out.println("Start SampleDyService: 2");
        System.out.println(SampleDyService.class.getClassLoader());
    }

    public void close() {
        System.out.println("close SampleDyService: 2");
    }
}
