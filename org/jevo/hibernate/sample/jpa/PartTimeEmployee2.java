package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:23:42
 * To change this template use File | Settings | File Templates.
 */
//@AssociationOverrides
//如果需要指定多个 @AssociationOverride，则必需使用一个 @AssociationOverrides 批注指定所有关联覆盖。



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

