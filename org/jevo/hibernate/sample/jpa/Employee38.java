package org.jevo.hibernate.sample.jpa;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.util.Date;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:19:04
 * To change this template use File | Settings | File Templates.
 */
//@IdClass
//使用 @IdClass 批注为实体指定一个复合主键类（通常由两个或更多基元类型或 JDK 对象类型组成）。从原有数据库映射时（此时数据库键由多列组成），通常将出现复合主键。

//复合主键类具有下列特征：

//它是一个普通的旧式 Java 对象 (POJO) 类。

//它必须为 public，并且必须有一个 public 无参数构造函数。

//如果使用基于属性的访问，则主键类的属性必须为 public 或 protected。

//它必须是可序列化的。

//它必须定义 equals 和 hashCode 方法。

//这些方法的值相等性的语义必须与键映射到的数据库类型的数据库相等性一致。

//它的字段或属性的类型和名称必须与使用 @Id 进行批注的实体主键字段或属性的类型和名称相对应。

//或者，您可以使复合主键类成为由实体拥有的嵌入类（请参阅 @EmbeddedId）。

//示例 1-37 显示了一个非嵌入的复合主键类。在该类中，字段 empName 和 birthDay 的名称和类型必须对应于实体类中属性的名称和类型。 示例 1-38 显示了如何使用这个非嵌入的复合主键类（使用 @IdClass 批注）配置 EJB 3.0 实体。由于实体类字段 empName 和 birthDay 在主键中使用，因此还必须使用 @Id 批注对其进行批注。


@IdClass(EmployeePK37.class)
@Entity
public class Employee38 {
    @Id
    String empName;
    @Id
    Date birthDay;

}

