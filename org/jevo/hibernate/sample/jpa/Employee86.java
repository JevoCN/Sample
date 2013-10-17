package org.jevo.hibernate.sample.jpa;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:31:00
 * To change this template use File | Settings | File Templates.
 */
//@Table
//默认情况下，JPA 持续性提供程序假设实体的所有持久字段均存储到一个名称为实体名称的数据库表中（请参阅 @Entity）。

//在以下条件下，使用 @Table 批注指定与实体关联的主表：

//实体名称难于处理、是一个保留字、与预先存在的数据模型不兼容或作为数据库中的表名无效

//需要控制表所属的目录或模式

//如果希望 JPA 将某些字段持久保存到主表，而将其他字段持久保存到一个或多个辅助表，请参阅 @SecondaryTable。

//示例 1-86 显示了如何使用此批注指定主表名。
//示例 1-86 @Table
//示例 1-90 显示了如何使用此批注对主表 EMP 中的列 EMP_ID 和 EMP_NAME 指定一个唯一约束。
//示例 1-90 使用唯一约束的 @Table
@Entity
@Table(name = "EMP", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMP_ID", "EMP_NAME"})})
public class Employee86 implements Serializable {
    Long id;
    //示例 1-87 显示了如何使用此批注为名为 empGen 的 TABLE 主键生成器指定分配大小。

    //示例 1-87 @TableGenerator
    @Id
    @TableGenerator(name = "empGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "empGen")
    @Column(name = "CUST_ID")
    public Long getId() {
        return id;
    }

    //示例 1-88 显示了如何使用此批注指定 JPA 持续性提供程序应将 java.util.Date 字段 startDate 持久保存为 DATE ( java.sql.Date) 数据库类型。
    @Temporal(TemporalType.DATE)
    protected java.util.Date startDate;

    //示例 1-89 显示了如何使用此批注将 Employee 字段 currentSession 指定为非持久字段。JPA 持续性提供程序将不持久保存该字段。
    @Transient
    Session currentSession;

    //示例 1-91 显示了如何使用此批注将属性 getVersionNum 指定为乐观锁定值。在该示例中，该属性的列名设置为 OPTLOCK（请参阅 @Column），而非属性的默认列名。
    int versionNum;
    @Version
    @Column(name = "OPTLOCK")
    protected int getVersionNum() {
        return versionNum;
    }

}

//@TableGenerator
//如果使用 @GeneratedValue 批注指定一个 TABLE 类型的主键生成器，可以使用 @TableGenerator 批注微调该主键生成器以：

//由于名称难于处理、是一个保留字、与预先存在的数据模型不兼容或作为数据库中的表名无效而更改主键生成器的表名称

//更改分配大小以匹配应用程序要求或数据库性能参数

//更改初始值以匹配现有的数据模型（例如，如果基于已经为其分配或保留了一组主键值的现有数据集构建）

//使用特定目录或模式配置主键生成器的表

//在主键生成器表的一列或多列商配置一个唯一的约束

//@Temporal
//使用 @Temporal 批注指定 JPA 持续性提供程序应只为 java.util.Date 和 java.util.Calendar 类型的字段或属性持久保存的数据库类型。

//该批注可以与 @Basic 一起使用。

//@Transient
//默认情况下，JPA 持续性提供程序假设实体的所有字段均为持久字段。

//使用 @Transient 批注指定实体的非持久字段或属性，例如，一个在运行时使用但并非实体状态一部分的字段或属性。

//JPA 持续性提供程序不会对批注为 @Transient 的属性或字段持久保存（或创建数据库模式）。

//该批注可以与 @Entity、 @MappedSuperclass 和 @Embeddable 一起使用。

//该批注没有属性。有关更多详细信息，请参阅 API。

//@UniqueConstraint
//默认情况下，JPA 持续性提供程序假设所有列均可以包含重复值。

//使用 @UniqueConstraint 批注指定将在为主表或辅助表生成的 DDL 中包含一个唯一约束。或者，您可以在列级别指定唯一约束（请参阅 @Column）。

//@Version
//默认情况下，JPA 持续性提供程序假设应用程序负责数据一致性。

//使用 @Version 批注通过指定用作其乐观锁定值的实体类的版本字段或属性来启用 JPA 管理的乐观锁定（推荐做法）。

//选择版本字段或属性时，确保：

//每个实体只有一个版本字段或属性

//选择一个持久保存到主表的属性或字段（请参阅 @Table）

//您的应用程序不修改版本属性或字段

//此批注没有属性 。有关更多详细信息，请参阅 API。

