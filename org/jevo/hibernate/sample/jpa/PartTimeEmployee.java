package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:19:37
 * To change this template use File | Settings | File Templates.
 */
//@AssociationOverride
//默认情况下，JPA 持续性提供程序自动假设子类继承超类中定义的持久属性及其关联映射。

//如果继承的列定义对实体不正确（例如，如果继承的列名与已经存在的数据模型不兼容或作为数据库中的列名无效），请使用 @AssociationOverride 批注自定义从 @MappedSuperclass 或 @Embeddable 继承的 @OneToOne 或 @ManyToOne 映射，以更改与字段或属性关联的 @JoinColumn。

//如果有多个要进行的 @AssociationOverride 更改，则必须使用 @AssociationOverrides。

//要自定义基本映射以更改它的 @Column，请使用 @AttributeOverride。

//表 1-4 列出了此批注的属性 。

//示例 1-4 显示了 示例 1-5 中的实体扩展的 @MappedSuperclass。 示例 1-5 显示了如何在实体子类中使用 @AssociationOverride 覆盖 @MappedSuperclass Employee 中定义（默认情况下）的 @JoinColumn 以便关联到 Address。    

//示例 1-2 @AssociationOverride

@Entity
@AssociationOverride(name = "address", joinColumns = @JoinColumn(name = "ADDR_ID"))
public class PartTimeEmployee extends Employee {
    @Column(name = "WAGE")
    protected Float hourlyWage;

}





