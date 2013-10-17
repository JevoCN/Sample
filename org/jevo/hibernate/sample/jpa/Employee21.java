package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:17:58
 * To change this template use File | Settings | File Templates.
 */

//@EntityListeners
//可以使用生命周期批注（请参阅 生命周期事件批注）指定实体中的方法，这些方法在指定的生命周期事件发生时执行您的逻辑。

//使用 @EntityListeners 批注将一个或多个实体监听程序类与 @Entity 或 @MappedSuperclass 关联，条件是您需要在指定的生命周期事件发生时执行逻辑，以及：

//不希望在实体 API 中公开生命周期监听程序方法。

//要在不同的实体类型之间共享生命周期监听程序逻辑。

//当实体或子类上发生生命周期事件时，JPA 持续性提供程序将按监听程序定义的顺序通知每个实体监听程序，并调用使用相应的生命周期事件类型进行批注的实体监听程序方法（如果有）。

//实体监听程序类具有以下特征：

//它是一个普通的旧式 Java 对象 (POJO) 类

//它有一个或多个具有以下签名的回调方法：

//public void <MethodName>(Object)

//可以指定参数类型 Object，或实体监听程序将与其关联的实体类的类型。

//它用一个或多个生命周期事件批注对每个回调方法进行批注。

//一个生命周期事件只能与一个回调监听程序方法关联，但某个给定的回调监听程序方法可以与多个生命周期事件关联。

//如果使用实体监听程序，则可以管理哪些实体监听程序使用 @ExcludeDefaultListeners 和 @ExcludeSuperclassListeners 调用。

//示例 1-21 显示了如何使用此批注将实体监听程序类 EmployeePersistListener（请参阅 示例 1-22）和 EmployeeRemoveListener（请参阅 示例 1-23）与实体 Employee 关联。 示例 1-23 显示了您可以将多个生命周期事件与给定的实体监听程序类方法关联，但任何给定的生命周期事件只能在实体监听程序类中出现一次。




@Entity
@EntityListeners(value={EmployeePersistListener.class, EmployeeRemoveListener.class})
public class Employee21 implements Serializable {

}

