package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:02:12
 * To change this template use File | Settings | File Templates.
 */

//ʾ�� 1-19 @EmbeddedId

@Entity
public class Employee19 implements Serializable {
    EmployeePK primaryKey;

    public Employee19() {
    }

    @EmbeddedId
    public EmployeePK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(EmployeePK pk) {
        primaryKey = pk;
    }

}


