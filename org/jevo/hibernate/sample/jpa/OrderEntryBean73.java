package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.naming.Context;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:17:37
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceUnits
//如果要指定多个 @PersistenceUnit，则必须使用一个 @PersistenceUnits 批注指定所有持续性上下文。

//示例 1-73 显示了如何使用此批注指定两个持续性单元。在该示例中， @PersistenceContext 属性 unitName 和 @PersistenceUnit 属性 unitName 必须对应以便关联持续性上下文和持续性单元。

//示例 1-73 @PersistenceUnits

@Stateless
public class OrderEntryBean73 implements OrderEntry {
        Context ctx;
    
    @PersistenceContexts({
        @PersistenceContext(name = "OrderEM", unitName = "OrderEMUnit"),
    @PersistenceContext(name = "ItemEM", unitName = "ItemEMUnit")
            })
    @PersistenceUnits({
        @PersistenceUnit(name = "OrderEMFactory", unitName = "OrderEMUnit"),
        @PersistenceUnit(name = "ItemEMFactory", unitName = "ItemEMUnit")
            })
    public void enterOrder(int custID, Order newOrder) throws Exception {
        EntityManager em = (EntityManager) ctx.lookup("OrderEM");
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }

    public void enterItem(int orderID, Item newItem) throws Exception {
        EntityManager em = (EntityManager) ctx.lookup("ItemEM");
        ...
    }
}

