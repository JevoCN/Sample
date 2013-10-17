package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.naming.Context;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:15:47
 * To change this template use File | Settings | File Templates.
 */

//@PersistenceUnit
//默认情况下，JPA 持续性提供程序使用与默认持续性单元或您使用 @PersistenceContext 属性 unitName 指定的持续性单元关联的默认 EntityManagerFactory。

//使用 @PersistenceUnit 批注指定 EntityManagerFactory，您希望 JPA 持续性提供程序使用它来：

//获取指定的实体管理器

//指定 EntityManagerFactory 和持续性单元

//如果有多个要指定的 @PersistenceUnit，则必须使用 @PersistenceUnits。

//示例 1-71 显示了如何使用 @PersistenceUnit 批注指定要使用的 EntityManagerFactory 的 JNDI 名称以及与该工厂关联的持续性单元名称。当 JPA 持续性提供程序使用 JNDI 获取一个使用持续性上下文 OrderEM 的实体管理器时，它将使用 JNDI 名称 OrderEMFactory 与持续性单元 OrderEMUnit 关联的 EntityManagerFactory。

//示例 1-71 使用 @PersistenceUnit 指定工厂和单元

@Stateless
public class OrderEntryBean71 implements OrderEntry {
    @PersistenceContext(name = "OrderEM")
    @PersistenceUnit(name = "OrderEMFactory", unitName = "OrderEMUnit")
    EntityManager em;
    Context ctx;
    public void enterOrder(int custID, Order newOrder) throws Exception {
        em = (EntityManager) ctx.lookup("OrderEM");
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
}

/*
示例 1-72 显示了一个使用 @PersistenceContext 属性 unitName 仅指定持续性单元的其他方法。在该示例中，当 JPA 持续性提供程序使用 JNDI 获取一个使用持续性上下文 OrderEM 的实体管理器时，它将使用与持续性单元 OrderEMUnit 关联的默认 EntityManagerFactory。

示例 1-72 使用 @PersistenceContext 属性 unitName

@Stateless
public class OrderEntryBean implements OrderEntry {
@PersistenceContext(name="OrderEM", unitName="OrderEMUnit")
EntityManager em;
public void enterOrder(int custID, Order newOrder) {
em = (EntityManager)ctx.lookup("OrderEM");
Customer cust = em.find(Customer.class, custID);
cust.getOrders().add(newOrder);
newOrder.setCustomer(cust);
    }
}

 */
