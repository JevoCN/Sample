package org.jevo.hotswap.sample.exam3;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
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
