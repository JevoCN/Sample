package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.print.DocFlavor;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:52:23
 * To change this template use File | Settings | File Templates.
 */
//@DiscriminatorValue
//默认情况下，当 @Inheritance 属性策略为 InheritanceType.SINGLE_TABLE 或 JOINED 时，JPA 持续性提供程序使用 @DiscriminatorColumn 按实体名称区分继承层次中的类（请参阅 @Entity）。

//使用 @DiscriminatorValue 批注指定用于区分此继承层次中的实体的标识符值：

//如果实体名称不适合于此应用程序

//匹配现有的数据库模式


//示例 1-14 显示了如何使用此批注指定一个名为 DISC、类型为 STRING、长度为 20 的标识符列。在本示例中，该类的 @DiscriminatorValue 指定为 CUST。 示例 1-15 中的子类将它自己的 @DiscriminatorValue 指定为 VIP。在 Customer 和 ValuedCustomer 中， @DiscriminatorValue 的值必须可以转换为由 @DiscriminatorColumn 属性 discriminatorType 指定的类型，并且必须符合 @DiscriminatorColumn 属性 length。

//示例 1-14 @DiscriminatorColumn 和 @DiscriminatorValue — 根类

@Entity
@Table(name="CUST")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC", discriminatorType= javax.persistence.DiscriminatorType.STRING, length=20)
@DiscriminatorValue(value="CUST")
public class Customer2 {

}

