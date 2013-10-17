package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:04:20
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceContext
//在使用 JPA 持续性提供程序的应用程序中，可以使用实体管理器执行所有持续性操作（创建、读取、更新和删除）。Java EE 应用程序使用相关性注入或在 JNDI 名称空间中直接查找实体管理器获取实体管理器。

//使用 @PersistenceContext 批注获取实体管理器：

//使用相关性注入

//使用 JNDI 查找按名称进行

//与特定的持续性单元关联（另请参阅 @PersistenceUnit）

//具有扩展的持续性上下文

//使用特定的持续性属性进行了自定义（请参阅 @PersistenceProperty）

//如果有多个要指定的 @PersistenceContext，则必须使用 @PersistenceContexts。

//示例 1-67 显示了如何使用此批注在无状态会话中注入实体管理器， 示例 1-68 显示了如何使用此批注在 JNDI 中查找实体管理器。

//示例 1-67 使用 @PersistenceContext 和相关性注入


@Stateless
public class OrderEntryBean implements OrderEntry {
    @PersistenceContext
    EntityManager em;

    public void enterOrder(int custID, Order newOrder) {
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
}


