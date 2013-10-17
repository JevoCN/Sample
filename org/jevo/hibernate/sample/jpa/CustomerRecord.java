package org.jevo.hibernate.sample.jpa;

import javax.persistence.OneToOne;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:56:20
 * To change this template use File | Settings | File Templates.
 */
//ʾ�� 1-64 @OneToOne - CustomerRecord ��
@Entity
public class CustomerRecord implements Serializable {
    Customer customer;

    @OneToOne(optional = false, mappedBy = "customerRecord")
    public Customer getCustomer() {
        return customer;
    }

}

