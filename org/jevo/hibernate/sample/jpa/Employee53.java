package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.mail.Address;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:40:48
 * To change this template use File | Settings | File Templates.
 */
//@MappedSuperclass
//默认情况下，JPA 持续性提供程序假设实体的所有持久字段均在该实体中定义。

//使用 @MappedSuperclass 批注指定一个实体类从中继承持久字段的超类。当多个实体类共享通用的持久字段或属性时，这将是一个方便的模式。

//您可以像对实体那样使用任何直接和关系映射批注（如 @Basic 和 @ManyToMany）对该超类的字段和属性进行批注，但由于没有针对该超类本身的表存在，因此这些映射只适用于它的子类。继承的持久字段或属性属于子类的表。

//可以在子类中使用 @AttributeOverride 或 @AssociationOverride 批注来覆盖超类的映射配置。

//该批注没有属性。有关更多详细信息，请参阅 API。

//示例 1-53 显示了如何使用此批注将 Employee 指定为映射超类。 示例 1-54 显示了如何扩展实体中的此超类，以及如何在实体类中使用 @AttributeOverride 以覆盖超类中设置的配置。

//示例 1-53 @MappedSuperclass

@MappedSuperclass
public class Employee53 {
    @Id
    protected Integer empId;

    @Version
    protected Integer version;

    @ManyToOne
    @JoinColumn(name = "ADDR")
    protected Address address;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer id) {
        this.empId = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addr) {
        this.address = addr;
    }
}


