package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:35:41
 * To change this template use File | Settings | File Templates.
 */
//@ManyToOne
//Ĭ������£�JPA Ϊָ����ж��һ�����Ե�����ʵ����ĵ�ֵ�����Զ�����һ�� ManyToOne ӳ�䡣

//ʹ�� @ManyToOne ��ע��

//����ȡ��������Ϊ LAZY

//�����ֵ���ʺ���Ӧ�ó�����ӳ������Ϊ��ֹ��ֵ����Էǻ�Ԫ���ͣ�

//���ù�����Ŀ��ʵ�壨����޷��ӱ����õĶ��������ƶϳ�����

//���ñ�����������Ŀ��Ĳ��������磬���ɾ����ӵ��ʵ�壬��ȷ����ɾ��������Ŀ��

//ʾ�� 1-49 ��ʾ�����ʹ�ô���ע��ʹ��һ������� Customer����ӵ�з����� Order��ӵ�з���֮������һ�����һӳ�䡣

//ʾ�� 1-49 @ManyToOne

@Entity
public class Order49 implements Serializable {
    Customer47 customer;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "CUST_ID", nullable = false, updatable = false)
    public Customer47 getCustomer() {
        return customer;
    }

}

