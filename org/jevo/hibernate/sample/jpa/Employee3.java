package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:35:59
 * To change this template use File | Settings | File Templates.
 */

//@Column
//默认情况下，JPA 持续性提供程序假设每个实体的持久字段存储在其名称与持久字段的名称相匹配的数据库表列中。

//使用 @Column 批注：

//将持久字段与其他名称关联（如果默认列名难于处理、与事先存在的数据模型不兼容或作为数据库中的列名无效）

//将持久字段与辅助表中的列关联（请参阅 @SecondaryTable）

//微调数据库中列的特征



//显示了如何使用此批注使 JPA 将 empId 持久保存到辅助表 EMP_HR 中的列 EMP_NUM。默认情况下，JPA 将 empName 持久保存到主表 Employee 中的列 empName。
@Entity
@SecondaryTable(name = "EMP_HR")
public class Employee3 implements Serializable {

    @Column(name = "EMP_NUM", table = "EMP_HR")
    private Long empId;

    private String empName;

}
//@SecondaryTable
//默认情况下，JPA 持续性提供程序假设实体的所有持久字段均存储到一个名称为实体名称的数据库表中：该表称作主表（请参阅 @Table）。

//如果希望 JPA 分别将实体的某些持久字段持久保存到主表和其他数据库表，请使用 @SecondaryTable 批注将实体与其他数据库表关联。在该示例中，您使用 @Column 批注将实体的持久字段与表关联。

//如果要将两个或更多辅助表与实体关联，则可以使用 @SecondaryTables。
//示例 1-78 显示了如何使用此批注指定一个名为 EMP_HR 的辅助表。在该示例中，默认情况下，JPA 将实体持久字段 empId 持久保存到名为 Employee 的主表中的列 empId，并将 empSalary 持久保存到辅助表 EMP_HR 中的列 empSalary。有关详细信息，请参阅 @Column。

/*示例 1-78 @SecondaryTable

@Entity
@SecondaryTable(name="EMP_HR")
public class Employee implements Serializable {
    ...
private Long empId;

@Column(table="EMP_HR", name="EMP_SALARY"))
private Float empSalary;
    ...
}
*/

//@SecondaryTables
//如果需要指定多个 @SecondaryTable，可以使用一个 @SecondaryTables 批注指定所有辅助表。

//表 1-41 列出了此批注的属性 。有关更多详细信息，请参阅 API。

/*
示例 1-79 显示了如何使用此批注指定两个名为 EMP_HR 和 EMP_TR 的辅助表。在该示例中，默认情况下，JPA 将实体持久字段 empId 持久保存到名为 Employee 的主表中的列 empId。JPA 将 empSalary 持久保存到辅助表 EMP_HR 中的列 empSalary，并将 empClass 持久保存到辅助表 EMP_TR 中的列 EMP_HR。有关详细信息，请参阅 @Column。

示例 1-79 @SecondaryTables

@Entity
@SecondaryTables({
@SecondaryTable(name="EMP_HR"),
@SecondaryTable(name="EMP_TR")
    })
public class Employee implements Serializable {
    ...
private Long empId;

@Column(table="EMP_HR", name="EMP_SALARY"))
private Float empSalary;

@Column(table="EMP_TR", name="EMP_CLASS"))
private Float empClass;
    ...
}

 */


//@SequenceGenerator
//如果使用 @GeneratedValue 批注指定一个 SEQUENCE 类型的主键生成器，则可以使用 @SequenceGenerator 批注微调该主键生成器以：

//更改分配大小以匹配应用程序要求或数据库性能参数

//更改初始值以匹配现有的数据模型（例如，如果基于已经为其分配或保留了一组主键值的现有数据集构建）

//使用现有数据模型中预定义的序列

/*
示例 1-80 显示了如何使用此批注为名为 CUST_SEQ 的 SEQUENCE 主键生成器指定分配大小。

示例 1-80 @SequenceGenerator

@Entity
public class Employee implements Serializable {
    ...
@Id
@SequenceGenerator(name="CUST_SEQ", allocationSize=25)
@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
@Column(name="CUST_ID")
public Long getId() {
return id;
    }
    ...


 */


