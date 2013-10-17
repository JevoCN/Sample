package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:36:36
 * To change this template use File | Settings | File Templates.
 */

//@ExcludeDefaultListeners
//默认监听程序是 orm.xml 文件中指定的一个生命周期事件监听程序类，该类应用于持续性单元（请参阅 @PersistenceUnit）中的所有实体。在调用任何其他实体监听程序（请参阅 @EntityListeners）之前，JPA 持续性提供程序首先按照 orm.xml 文件中定义的顺序调用默认监听程序（如果有）。

//如果默认监听程序行为不适用，请使用 @ExcludeDefaultListeners 批注覆盖（并阻止）针对给定 @Entity 或 @MappedSuperclass 执行的默认监听程序。

//此批注没有属性 。有关更多详细信息，请参阅 API。

//示例 1-29 显示了如何使用此批注指定不应对 Employee 实体执行默认监听程序。

//示例 1-29 @ExcludeDefaultListeners

@Entity
@ExcludeDefaultListeners
public class Employee29 implements Serializable {
  
}

