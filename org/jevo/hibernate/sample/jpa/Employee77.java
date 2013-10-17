package org.jevo.hibernate.sample.jpa;

import javax.persistence.QueryHint;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:23:43
 * To change this template use File | Settings | File Templates.
 */
//@QueryHint
//默认情况下，JPA 持续性提供程序假设 @NamedQuery 或 @NamedNativeQuery 应完全按照查询 String 指定的方式执行。

//使用 @QueryHint 批注指定供应商特定的 JPA 查询扩展，以：

//提高查询性能

//利用供应商的 JPA 持续性提供程序实现中的特定特性

//示例 1-77 显示了如何使用 @QueryHint 批注自定义查询以利用由 TopLink Essentials 提供的供应商 JPA 扩展：在该示例中，提示确保在执行查询时始终刷新 TopLink 缓存。有关详细信息，请参阅 “TopLink JPA 查询提示扩展”。

//示例 1-77 @QueryHint

@Entity
@NamedQuery(
name="findAllEmployees",
query="SELECT * FROM EMPLOYEE WHERE MGR=1",
hints={@QueryHint={name="toplink.refresh", value="true"}})
public class Employee77 implements Serializable {

}


