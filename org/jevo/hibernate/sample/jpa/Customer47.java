package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:32:54
 * To change this template use File | Settings | File Templates.
 */
//@ManyToMany
//默认情况下，JPA 为具有多对多多重性的为多值关联自动定义一个 @ManyToMany 映射。

//使用 @ManyToMany 批注：

//将获取类型配置为 LAZY

//如果空值不适合于应用程序，则将映射配置为禁止空值（针对非基元类型）

//由于所使用的 Collection 不是使用一般参数定义的，因此配置关联的目标实体

//配置必须层叠到关联目标的操作：例如，如果删除了拥有实体，则确保还删除关联的目标

//配置由持续性提供程序使用的连接表的详细信息（请参阅 @JoinTable）

//示例 1-47 和 示例 1-48 显示了如何使用此批注在使用一般参数的 Customer 和 PhoneNumber 之间配置一个多对多映射。

//示例 1-47 @ManyToMany — 使用一般参数的 Customer 类


@Entity
public class Customer47 implements Serializable {
    Set<PhoneNumber> phones;

    @ManyToMany
    @JoinTable(
            name = "CUST_PHONE",
            joinColumns =
            @JoinColumn(name = "CUST_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "PHONE_ID", referencedColumnName = "ID")
    )
    public Set<PhoneNumber> getPhones() {
        return phones;
    }

}

