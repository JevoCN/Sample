package org.jevo.hibernate.sample.jpa;

import javax.persistence.PostRemove;
import javax.persistence.PreRemove;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:19:36
 * To change this template use File | Settings | File Templates.
 */

//ʾ�� 1-23 EmployeeRemoveListener

public class EmployeeRemoveListener {
    @PreRemove
    @PostRemove
    public void employeePreRemove(Object employee) {

    }

}

