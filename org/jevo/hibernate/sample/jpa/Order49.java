package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:35:41
 * To change this template use File | Settings | File Templates.
 */
//@ManyToOne
//默认情况下，JPA 为指向具有多对一多重性的其他实体类的单值关联自动定义一个 ManyToOne 映射。

//使用 @ManyToOne 批注：

//将获取类型配置为 LAZY

//如果空值不适合于应用程序，则将映射配置为禁止空值（针对非基元类型）

//配置关联的目标实体（如果无法从被引用的对象类型推断出它）

//配置必须层叠到关联目标的操作：例如，如果删除了拥有实体，则确保还删除关联的目标

//示例 1-49 显示了如何使用此批注在使用一般参数的 Customer（被拥有方）和 Order（拥有方）之间配置一个多对一映射。

//示例 1-49 @ManyToOne

@Entity
public class Order49 implements Serializable {
    Customer47 customer;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST_ID", nullable = false, updatable = false)
    public Customer47 getCustomer() {
        return customer;
    }

}

