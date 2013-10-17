package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:23:42
 * To change this template use File | Settings | File Templates.
 */
//@AssociationOverrides
//�����Ҫָ����� @AssociationOverride�������ʹ��һ�� @AssociationOverrides ��עָ�����й������ǡ�



//@AssociationOverrides
@Entity
@AssociationOverrides({
//    @AssociationOverride(name="address", joinColumn=@Column(name="ADDR_ID")),
//    @AssociationOverride(name = "id", joinColumn = @Column(name = "PTID"))
        @AssociationOverride(name="address", joinColumns=@JoinColumn(name="ADDR_ID")),
    @AssociationOverride(name = "id", joinColumns = @JoinColumn(name = "PTID"))
        })
public class PartTimeEmployee2 extends Employee {
    @Column(name = "WAGE")
    protected Float hourlyWage;
}

