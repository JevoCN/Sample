package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.AttributeOverrides;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:32:21
 * To change this template use File | Settings | File Templates.
 */

//@AttributeOverrides
//�����Ҫָ����� @AttributeOverride�������ʹ��һ�� @AttributeOverrides ��עָ���������Ը��ǡ�

//ʾ�� 1-6 ��ʾ�����ʹ�ô���עָ���������Ը��ǡ�


@Entity
@AttributeOverrides({
    @AttributeOverride(name = "address", column = @Column(name = "ADDR_ID")),
    @AttributeOverride(name = "id", column = @Column(name = "PTID"))
        })
public class PartTimeEmployee4 extends Employee {

    @Column(name = "WAGE")
    protected Float hourlyWage;

    public PartTimeEmployee4() {
//        ...
    }

    public Float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Float wage) {
        this.hourlyWage = wage;
    }
}

