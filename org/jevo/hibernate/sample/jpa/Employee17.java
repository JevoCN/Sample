package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:56:18
 * To change this template use File | Settings | File Templates.
 */
//@Embedded

//默认情况下，JPA 持续性提供程序假设每个实体均持久保存到它自己的数据库表。

//使用 @Embedded 批注指定一个持久字段，该字段的 @Embeddable 类型可以存储为拥有实体的固有部分，并共享该实体的身份。嵌入对象的每个持久属性或字段均映射到拥有实体的数据库表。

//可以结合使用 @Embedded 和 @Embeddable 以建立严格所有权关系的模型，以便在删除了拥有对象的情况下还将删除被拥有的对象。

//嵌入的对象不应映射到多个表。

//默认情况下， @Embeddable 类中指定的列定义（请参阅 @Column）适用于 @Embedded 类。如果要覆盖这些列定义，请使用 @AttributeOverride。

//此批注没有属性。

//示例 1-17 显示了如何使用该批注指定： @Embeddable 类 EmploymentPeriod（请参阅 示例 1-16）可以使用指定的属性覆盖（请参阅 @AttributeOverride）嵌入到实体类中。如果不需要属性覆盖，则可以完全忽略 @Embedded 批注：JPA 持续性提供程序将推断出 EmploymentPeriod 是从它的 @Embeddable 批注进行嵌套。


//示例 1-17 @Embedded

@Entity
public class Employee17 implements Serializable {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDate", column = @Column(name = "EMP_START")),
        @AttributeOverride(name = "endDate", column = @Column(name = "EMP_END"))
            })
    public EmploymentPeriod getEmploymentPeriod() {
        return new EmploymentPeriod();
    }

}

