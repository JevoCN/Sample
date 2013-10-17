package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:04:20
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceContext
//��ʹ�� JPA �������ṩ�����Ӧ�ó����У�����ʹ��ʵ�������ִ�����г����Բ�������������ȡ�����º�ɾ������Java EE Ӧ�ó���ʹ�������ע����� JNDI ���ƿռ���ֱ�Ӳ���ʵ���������ȡʵ���������

//ʹ�� @PersistenceContext ��ע��ȡʵ���������

//ʹ�������ע��

//ʹ�� JNDI ���Ұ����ƽ���

//���ض��ĳ����Ե�Ԫ������������� @PersistenceUnit��

//������չ�ĳ�����������

//ʹ���ض��ĳ��������Խ������Զ��壨����� @PersistenceProperty��

//����ж��Ҫָ���� @PersistenceContext�������ʹ�� @PersistenceContexts��

//ʾ�� 1-67 ��ʾ�����ʹ�ô���ע����״̬�Ự��ע��ʵ��������� ʾ�� 1-68 ��ʾ�����ʹ�ô���ע�� JNDI �в���ʵ���������

//ʾ�� 1-67 ʹ�� @PersistenceContext �������ע��


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


