package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.EntityManager;
import javax.naming.Context;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:11:47
 * To change this template use File | Settings | File Templates.
 */

//@PersistenceContexts
//���Ҫָ����� @PersistenceContext�������ʹ��һ�� @PersistenceContexts ��עָ�����г����������ġ�

//ʾ�� 1-69 ��ʾ�����ʹ�ô���עָ�����������������ġ�


//ʾ�� 1-69 @PersistenceContexts

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

