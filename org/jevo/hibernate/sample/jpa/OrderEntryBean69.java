package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.EntityManager;
import javax.naming.Context;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:11:47
 * To change this template use File | Settings | File Templates.
 */

//@PersistenceContexts
//如果要指定多个 @PersistenceContext，则必须使用一个 @PersistenceContexts 批注指定所有持续性上下文。

//示例 1-69 显示了如何使用此批注指定两个持续性上下文。


//示例 1-69 @PersistenceContexts

@Stateless
public class OrderEntryBean69 implements OrderEntry {
    @PersistenceContexts({
        @PersistenceContext(name = "OrderEM"),
    @PersistenceContext(name = "ItemEM")
            })
    Context ctx;
    public void enterOrder(int custID, Order newOrder) throws Exception {
        EntityManager em = (EntityManager) ctx.lookup("OrderEM");
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }

    public void enterItem(int orderID, Item newItem) throws Exception {
        EntityManager em = (EntityManager) ctx.lookup("ItemEM");

    }
}

