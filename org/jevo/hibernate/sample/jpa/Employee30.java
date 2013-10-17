package org.jevo.hibernate.sample.jpa;

import javax.persistence.MappedSuperclass;
import javax.persistence.EntityListeners;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:37:28
 * To change this template use File | Settings | File Templates.
 */

//@ExcludeSuperclassListeners
//如果继承层次中的 @Entity 和 @MappedSuperclass 类定义了 @EntityListeners，则默认情况下，JPA 持续性提供程序将在调用子类监听程序之前调用超类监听程序。

//如果超类监听程序行为不适用，则使用 @ExcludeSuperclassListeners 批注覆盖（并阻止）针对给定 @Entity 或 @MappedSuperclass 执行的超类监听程序。

//@ExcludeSuperclassListeners 批注不影响默认监听程序（请参阅 @ExcludeDefaultListeners）。

//此批注没有属性 。有关更多详细信息，请参阅 API。

//示例 1-29 显示了如何使用此批注指定不应对 PartTimeEmployee 实体执行超类监听程序 EmployeeListener，而是执行默认监听程序和子类监听程序 PartTimeEmployeeListener1 和 PartTimeEmployeeListener2。

//示例 1-30 超类级别的实体监听程序

@MappedSuperclass
@EntityListeners(value={EmployeePersistListener.class})
public class Employee30 {

}


