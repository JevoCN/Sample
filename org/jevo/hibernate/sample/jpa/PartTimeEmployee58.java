package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:50:02
 * To change this template use File | Settings | File Templates.
 */

//@NamedQueries
//如果需要指定多个 @NamedQuery，则必须使用一个 @NamedQueries 批注指定所有命名查询。

//示例 1-58 @NamedQueries


@Entity
@NamedQueries({
@NamedQuery(
name="findAllEmployeesByFirstName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName = :firstname"
    ),
@NamedQuery(
name="findAllEmployeesByLasttName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.lasstName = :lastname"
    )
})
public class PartTimeEmployee58 extends Employee {
    
}


//@NamedQuery
//在使用 JPA 持续性提供程序的应用程序中，可以使用实体管理器动态创建和执行查询，也可以预定义查询并在运行时按名称执行。

//使用 @NamedQuery 批注创建与 @Entity 或 @MappedSuperclass 关联的预定义查询，这些查询：

//使用 JPA 查询语言（请参阅 JSR-000220 Enterprise JavaBeans v3.0 规范，第 4 章）进行基于任何基础数据库的可移植执行

//经常被使用

//比较复杂并且难于创建

//可以在不同实体之间共享

//只返回实体（从不返回标量值），并只返回一个类型的实体

//如果有多个要定义的 @NamedQuery，则必须使用 @NamedQueries。

//要在已知的基础数据库中预定义原生 SQL 查询，请参阅 @NamedNativeQuery。使用原生 SQL 查询，您可以返回实体（包括不同类型的实体）、标量值或同时返回两者。

/*
示例 1-59 显示了如何使用 @NamedQuery 批注定义一个JPA 查询语言查询，该查询使用名为 firstname 的参数。 示例 1-60 显示了如何使用 EntityManager 获取此查询并使用 Query 方法 setParameter 设置 firstname 参数。

示例 1-59 使用 @NamedQuery 实现一个带参数的查询

@Entity
@NamedQuery(
name="findAllEmployeesByFirstName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName = :firstname"
)
public class Employee implements Serializable {
    ...
}
*/

/*
示例 1-60 执行命名查询

Query queryEmployeesByFirstName = em.createNamedQuery("findAllEmployeesByFirstName");
queryEmployeeByFirstName.setParameter("firstName", "John");
Collection employees = queryEmployessByFirstName.getResultList();

*/