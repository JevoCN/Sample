package org.jevo.hibernate.sample.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:42:31
 * To change this template use File | Settings | File Templates.
 */
//ʾ�� 1-54 ��չ @MappedSuperclass

@Entity
@AttributeOverride(name = "address", column = @Column(name = "ADDR_ID"))
public class PartTimeEmployee54 extends Employee53 {

    @Column(name = "WAGE")
    protected Float hourlyWage;

    public PartTimeEmployee54() {
    }

    public Float getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Float wage) {
        this.hourlyWage = wage;
    }
}

