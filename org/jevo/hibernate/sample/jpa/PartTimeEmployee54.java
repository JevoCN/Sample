package org.jevo.hibernate.sample.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:42:31
 * To change this template use File | Settings | File Templates.
 */
//示例 1-54 扩展 @MappedSuperclass

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

