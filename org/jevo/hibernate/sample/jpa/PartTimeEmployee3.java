package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:31:20
 * To change this template use File | Settings | File Templates.
 */
//@AttributeOverride
//默认情况下，JPA 持续性提供程序自动假设子类继承超类中定义的持久属性及其基本映射。

//如果针对实体继承的列定义不正确，请使用 @AttributeOverride 批注自定义一个从 @MappedSuperclass 或 @Embeddable 继承的基本映射以更改与字段或属性关联的 @Column。（例如，如果继承的列名与事先存在的数据模型不兼容，或者作为数据库中的列名无效）。

//如果有多个要进行的 @AttributeOverride 更改，则必须使用 @AttributeOverrides。

//要自定义关联映射以更改它的 @JoinColumn，请使用 @AssociationOverride。


//示例 1-4 显示了 示例 1-5 中的实体扩展的 @MappedSuperclass。 示例 1-5 显示了如何使用实体子类中的 @AttributeOverride 覆盖 @MappedSuperclass Employee 中定义（默认情况下）的 @Column，以便基本映射到 Address。


//1-5 @AttributeOverride


@Entity
@AttributeOverride(name = "address", column = @Column(name = "ADDR_STRING"))
public class PartTimeEmployee3 extends Employee {
    @Column(name = "WAGE")
    protected Float hourlyWage;

}
