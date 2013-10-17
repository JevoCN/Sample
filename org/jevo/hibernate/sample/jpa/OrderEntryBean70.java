package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceProperty;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:13:35
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceProperty
//Ĭ������£�JPA �������ṩ���������ʹ�� @PersistenceContext ��ȡ��ʵ���������ʹ��Ĭ�����ԡ�

//ʹ�� @PersistenceProperty ��עָ�����ԣ�������Ӧ���ض������ԣ����Ա�������������ṩ����

//�Զ���ʵ���������Ϊ

//���ù�Ӧ�̵� JPA �������ṩ����ʵ���е��ض�����

//����ʵ�������ʱ�����Դ��ݸ��������ṩ�����޷�ʶ������Ա��򵥵غ��ԡ�

//ʾ�� 1-70 ��ʾ�����ʹ�� @PersistenceProperty ��ע�Զ����ѯ�������� TopLink Essentials �ṩ�Ĺ�Ӧ�� JPA ��չ���ڸ�ʾ���У�������ȷ���ڴ˳�������������ʹ��һ�������� TopLink ���档�й���ϸ��Ϣ������� ��TopLink JPA Persistence.xml �ļ���չ����

//�� 1-70 @PersistenceProperty



@Stateless
public class OrderEntryBean70 implements OrderEntry {
    @PersistenceContext(
            properties = {@PersistenceProperty={name = "toplink.cache.type.default", value = "CacheType.Full"}})
    EntityManager em;

    public void enterOrder(int custID, Order newOrder) {
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
}

