package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.naming.Context;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:17:37
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceUnits
//���Ҫָ����� @PersistenceUnit�������ʹ��һ�� @PersistenceUnits ��עָ�����г����������ġ�

//ʾ�� 1-73 ��ʾ�����ʹ�ô���עָ�����������Ե�Ԫ���ڸ�ʾ���У� @PersistenceContext ���� unitName �� @PersistenceUnit ���� unitName �����Ӧ�Ա���������������ĺͳ����Ե�Ԫ��

//ʾ�� 1-73 @PersistenceUnits

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

