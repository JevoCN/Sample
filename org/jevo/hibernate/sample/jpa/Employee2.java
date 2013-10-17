package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:33:53
 * To change this template use File | Settings | File Templates.
 */

//@Entity
//使用 @Entity 批注将普通的旧式 Java 对象 (POJO) 类指定为实体，并使其可用于 JPA 服务。必须将 POJO 类指定为实体，然后才可以使用任何其他 JPA 批注。

//@Basic
//默认情况下，JPA 持续性提供程序为大多数 Java 基元类型、基元类型的包装程序以及枚举自动配置一个 @Basic 映射。

//使用 @Basic 批注：

//将获取类型配置为 LAZY

//如果空值不适合于应用程序，则将映射配置为禁止空值（针对非基元类型）


//@GeneratedValue
//默认情况下，JPA 持续性提供程序管理为实体主键提供的唯一标识符（请参阅 @Id）。

//如果要微调此机制以实现以下目的，请使用 @GeneratedValue 批注：

//如果您感觉另一个生成器类型更适合于数据库或应用，则覆盖持续性提供程序为数据库选择的身份值生成的类型

//如果此名称难于处理、是一个保留字、与事先存在的数据模型不兼容或作为数据库中的主键生成器名称无效，则覆盖持续性提供程序选择的主键生成器名称

//@Id
//使用 @Id 批注将一个或多个持久字段或属性指定为实体的主键。

//对于每个实体，必须至少指定以下项之一：

//一个 @Id

//多个 @Id 和一个 @IdClass（对于复合主键）

//一个 @EmbeddedId

//此批注没有属性 。有关更多详细信息，请参阅 API。

//默认情况下，JPA 持续性提供程序选择最合适的主键生成器（请参阅 @GeneratedValue）并负责管理主键值：您不必采取任何进一步的操作。如果要使用 JPA 持续性提供程序的默认键生成机制，则不必采取任何进一步的操作。


@Entity
public class Employee2 implements Serializable {
    //示例 1-36 显示了如何使用此批注将持久字段 empID 指定为 Employee 表的主键。
    //示例 1-36 @Id
    @Id    
    protected Long id;
    protected String name;


    ////显示了如何使用@Basic批注为基本映射指定获取类型 LAZY。
    @Basic(fetch = FetchType.LAZY)
    protected String getName() {
        return name;
    }


    //示例 1-35 显示了如何使用此批注指示持续性提供程序使用名为 CUST_SEQ、类型为 GeneratorType.SEQUENCE 的主键生成器。
    //示例 1-35 @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @Column(name = "CUST_ID")
    public Long getId() {
        return id;
    }


    //示例 1-66 Employee 实体
    @ManyToMany(mappedBy="employees")
// By default, returns a List in ascending order by empId
    public List<Project> getProjects() {
        return null;
    }

}

