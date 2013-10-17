package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.Map;
import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:37:23
 * To change this template use File | Settings | File Templates.
 */
//@MapKey
//默认情况下，JPA 持续性提供程序假设关联实体的主键为 java.util.Map 类型的关联的 Map 键：

//如果主键是批注为 @Id 的非复合主键，则该字段或属性的类型实例将用作 Map 键。

//如果主键是批注为 @IdClass 的复合主键，则主键类的实例将用作 Map 键。

//使用 @MapKey 批注：

//将某个其他字段或属性指定为 Map 键（如果关联实体的主键不适合于应用程序）

//指定一个嵌入的复合主键类（请参阅 @EmbeddedId）

//指定的字段或属性必须具有唯一约束（请参阅 @UniqueConstraint）。

//在 示例 1-52 中，Project 对作为 Map 的 Employee 实例拥有一对多关系。 示例 1-52 显示了如何使用 @MapKey 批注指定此 Map 的键为 Employee 字段 empPK，它是一个类型为 EmployeePK（请参阅 示例 1-52）的嵌入式复合主键（请参阅 示例 1-51）。

//示例 1-50 使用 @MapKey 的 Project 实体




@Entity
public class Project {
    @OneToMany(mappedBy = "project")
    @MapKey(name = "empPK")
    public Map<EmployeePK, Employee> getEmployees() {
        return null;
    }

//@OrderBy
//默认情况下，JPA 持续性提供程序按关联实体的主键以升序顺序检索 Collection 关联的成员。
//将 @OrderBy 批注与 @OneToMany 和 @ManyToMany 一起使用以便：
//指定一个或多个作为排序依据的其他字段或属性
//为每个这样的字段或属性名指定不同的排序（升序或降序）
//    示例 1-65 显示了如何使用 @OrderBy 批注指定 Project 方法 getEmployees 应按 Employee 字段 lastname 以升序顺序并按 Employee 字段 seniority 以降序顺序返回 Employee 的 List。 示例 1-66 显示了默认情况下， Employee 方法 getProjects 按 Employee 主键 empId 以升序顺序返回 List。
//    示例 1-65 Project 实体  
    @ManyToMany
    @OrderBy("lastname ASC", "seniority DESC")
    public List<Employee2> getEmployeeList() {
        return null;
    }

}

