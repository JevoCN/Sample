package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:33:33
 * To change this template use File | Settings | File Templates.
 */
//ʾ�� 1-48 @ManyToMany �� ʹ��һ������� PhoneNumber ��


@Entity
public class PhoneNumber implements Serializable {
    Set<Customer47> customers;

    @ManyToMany(mappedBy = "phones")
    public Set<Customer47> getCustomers() {
        return customers;
    }

}

