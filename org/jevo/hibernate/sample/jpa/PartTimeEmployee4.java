package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.AttributeOverrides;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:32:21
 * To change this template use File | Settings | File Templates.
 */

//@AttributeOverrides
//如果需要指定多个 @AttributeOverride，则必需使用一个 @AttributeOverrides 批注指定所有属性覆盖。

//示例 1-6 显示了如何使用此批注指定两个属性覆盖。


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

