package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:47:40
 * To change this template use File | Settings | File Templates.
 */

//@NamedNativeQuery
//在使用 JPA 持续性提供程序的应用程序中，可以使用实体管理器动态创建和执行查询，也可以预定义查询并在运行时按名称执行。

//使用 @NamedNativeQuery 批注创建与 @Entity 或 @MappedSuperclass 关联的预定义查询，这些查询：

//使用基础数据库的原生 SQL

//经常被使用

//比较复杂并且难于创建

//可以在不同实体之间共享

//返回实体、标量值或两者的组合（另请参阅 @ColumnResult、 @EntityResult、 @FieldResult 和 @SqlResultSetMapping）

//如果有多个要定义的 @NamedNativeQuery，则必须使用 @NamedNativeQueries。

//要预定义适合于任何数据库的可移植查询，请参阅 @NamedQuery。

//示例 1-56 显示了如何使用 @NamedNativeQuery 批注定义一个使用基础数据库的原生 SQL 的查询。 示例 1-57 显示了如何使用 EntityManager 获取此查询以及如何通过 Query 方法 getResultList 执行该查询。

//示例 1-56 使用 @NamedNativeQuery 实现一个 Oracle 层次查询



@Entity
@NamedNativeQuery(
        name = "findAllEmployees",
        query = "SELECT * FROM EMPLOYEE"
)
public class Employee56 implements Serializable {


/*    示例 1-57 执行一个命名原生查询
    Query queryEmployees = em.createNamedQuery("findAllEmployees");
    Collection employees = queryEmployees.getResultList();
*/

}


