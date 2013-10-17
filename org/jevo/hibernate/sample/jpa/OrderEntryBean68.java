package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.naming.Context;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:10:05
 * To change this template use File | Settings | File Templates.
 */

//示例 1-68 使用 @PersistenceContext 和 JNDI 查找

@Stateless
public class OrderEntryBean68 implements OrderEntry {
    @PersistenceContext(name = "OrderEM")
    EntityManager em;
    Context ctx;

    public void enterOrder(int custID, Order newOrder) throws Exception{
        em = (EntityManager) ctx.lookup("OrderEM");
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
}

