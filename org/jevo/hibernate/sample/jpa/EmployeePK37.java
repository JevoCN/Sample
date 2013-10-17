package org.jevo.hibernate.sample.jpa;

import java.util.Date;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:18:07
 * To change this template use File | Settings | File Templates.
 */
//ʾ�� 1-37 ��Ƕ��ĸ���������


public class EmployeePK37 implements Serializable {
    private String empName;
    private Date birthDay;

    public EmployeePK37() {
    }

    public String getName() {
        return empName;
    }

    public void setName(String name) {
        empName = name;
    }

    public Date getDateOfBirth() {
        return birthDay;
    }

    public void setDateOfBirth(Date date) {
        birthDay = date;
    }

    public int hashCode() {
        return (int) empName.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EmployeePK)) return false;
        if (obj == null) return false;
        EmployeePK37 pk = (EmployeePK37) obj;
        return pk.birthDay == birthDay && pk.empName.equals(empName);
    }
}


