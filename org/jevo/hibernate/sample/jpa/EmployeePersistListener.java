package org.jevo.hibernate.sample.jpa;

import javax.persistence.PrePersist;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:18:25
 * To change this template use File | Settings | File Templates.
 */

//ʾ�� 1-22 EmployeePersistListener

public class EmployeePersistListener {
    @PrePersist
    public void employeePrePersist(Object employee) {

    }

}

