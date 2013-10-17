package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.naming.Context;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:15:47
 * To change this template use File | Settings | File Templates.
 */

//@PersistenceUnit
//Ĭ������£�JPA �������ṩ����ʹ����Ĭ�ϳ����Ե�Ԫ����ʹ�� @PersistenceContext ���� unitName ָ���ĳ����Ե�Ԫ������Ĭ�� EntityManagerFactory��

//ʹ�� @PersistenceUnit ��עָ�� EntityManagerFactory����ϣ�� JPA �������ṩ����ʹ��������

//��ȡָ����ʵ�������

//ָ�� EntityManagerFactory �ͳ����Ե�Ԫ

//����ж��Ҫָ���� @PersistenceUnit�������ʹ�� @PersistenceUnits��

//ʾ�� 1-71 ��ʾ�����ʹ�� @PersistenceUnit ��עָ��Ҫʹ�õ� EntityManagerFactory �� JNDI �����Լ���ù��������ĳ����Ե�Ԫ���ơ��� JPA �������ṩ����ʹ�� JNDI ��ȡһ��ʹ�ó����������� OrderEM ��ʵ�������ʱ������ʹ�� JNDI ���� OrderEMFactory ������Ե�Ԫ OrderEMUnit ������ EntityManagerFactory��

//ʾ�� 1-71 ʹ�� @PersistenceUnit ָ�������͵�Ԫ

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
ʾ�� 1-72 ��ʾ��һ��ʹ�� @PersistenceContext ���� unitName ��ָ�������Ե�Ԫ�������������ڸ�ʾ���У��� JPA �������ṩ����ʹ�� JNDI ��ȡһ��ʹ�ó����������� OrderEM ��ʵ�������ʱ������ʹ��������Ե�Ԫ OrderEMUnit ������Ĭ�� EntityManagerFactory��

ʾ�� 1-72 ʹ�� @PersistenceContext ���� unitName

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
