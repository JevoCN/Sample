package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:45:19
 * To change this template use File | Settings | File Templates.
 */
//@DiscriminatorColumn
//默认情况下，当 @Inheritance 属性策略为 InheritanceType.SINGLE_TABLE 或 JOINED 时，JPA 持续性提供程序将创建一个名为 DTYPE 的标识符列以区分继承层次中的类。

//使用 @DiscriminatorColumn 批注：

//指定一个标识符列名（如果数据模型中的列名不是默认列名 DTYPE）。

//指定一个适用于应用程序或事先存在的数据模型的标识符列长度

//微调数据库中的标识符列的特征

//示例 1-12 显示了如何使用此批注指定一个名为 DISC、类型为 STRING、长度为 20 的标识符列。在本示例中，该类的 @DiscriminatorValue 指定为 CUST。 示例 1-13 中的子类将它自己的 @DiscriminatorValue 指定为 VIP。在 Customer 和 ValuedCustomer 中， @DiscriminatorValue 的值必须可以转换为由 @DiscriminatorColumn 属性 discriminatorType 指定的类型，并且必须符合 @DiscriminatorColumn 属性 length。

//示例 1-12 @DiscriminatorColumn 和 @DiscriminatorValue — 根类


@Entity
@Table(name = "CUST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 20)
@DiscriminatorValue(value = "CUST")
public class Customer {

//@OneToMany
//默认情况下，JPA 为具有一对多多重性的多值关联定义一个 OneToMany 映射。
//使用 @OneToMany 批注：
//将获取类型配置为 LAZY
//由于所使用的 Collection 不是使用一般参数定义的，因此配置关联的目标实体
//配置必须层叠到关联目标的操作：例如，如果删除了拥有实体，则确保还删除关联的目标
//配置持续性提供程序对单向一对多关系使用的连接表（请参阅 @JoinTable）的详细信息
    //示例 1-61 和 示例 1-62 显示了如何使用此批注在使用一般参数的 Customer（被拥有方）和 Order（拥有方）之间配置一个一对多映射
    //示例 1-61 @OneToMany - 使用一般参数的 Customer 类
    Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch=FetchType.LAZY)
    public Set<Order> getOrders() {
        return orders;
    }

//@OneToOne
//默认情况下，JPA 为指向另一个具有一对一多重性的实体的单值关联定义一个 OneToOne 映射，并从被引用的对象类型推断出关联的目标实体。
//使用 @OneToOne 批注：
//将获取类型配置为 LAZY
//如果空值不适合于应用程序，则将映射配置为禁止空值（针对非基元类型）
//配置关联的目标实体（如果无法从被引用的对象类型推断出它）
//配置必须层叠到关联目标的操作：例如，如果删除了拥有实体，则确保还删除关联的目标
//    示例 1-63 和 示例 1-64 显示了如何使用此批注在 Customer（拥有方）和 CustomerRecord（被拥有方）之间配置一个一对一映射。
//    示例 1-63 @OneToOne - Customer 类    
    CustomerRecord customerRecord;
    @OneToOne(optional = false)     
    @JoinColumn(name = "CUSTREC_ID", unique = true, nullable = false, updatable = false)
    public CustomerRecord getCustomerRecord() {
        return customerRecord;
    }

}

//@Inheritance
//默认情况下，JPA 持续性提供程序自动管理继承层次中实体的持续性。
//使用 @Inheritance 批注自定义持续性提供程序的继承层次支持，以提高应用程序性能或匹配现有的数据模型。
//使用此批注指定 Customer 的所有子类将使用 InheritanceType.JOINED。 示例 1-40 中的子类将映射到它自己的表（该表针对 ValuedCustomer 的每个持久属性包含一列）和一个外键列（包含 Customer 表的主键）。
//默认情况下， InheritanceType.SINGLE_TABLE 应用于 Customer 及其所有子类。在该示例中，默认标识符表列 DTYPE（请参阅 @DiscriminatorColumn）指定为具有标识符类型 INTEGER，且 Customer 的 @DiscriminatorValue 指定为 1。 示例 1-42 显示了如何将子类 ValuedCustomer 的标识符值指定为 2。在该示例中， Customer 和 ValuedCustomer 的所有持久属性将映射到一个表。

//示例 1-41 @Inheritance — 指定其标识符列的根类
/*
@Entity
@DiscriminatorColumn(discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="1")
public class Customer {
    ...
}
示例 1-42 @Inheritance — 指定其标识符值的子类

@Entity
@DiscriminatorValue(value="2")
public class ValuedCustomer extends Customer {
    ...
}

*/