package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:51:13
 * To change this template use File | Settings | File Templates.
 */

// ʾ�� 1-13 �е����ཫ���Լ��� @DiscriminatorValue ָ��Ϊ VIP���� Customer �� ValuedCustomer �У� @DiscriminatorValue ��ֵ�������ת��Ϊ�� @DiscriminatorColumn ���� discriminatorType ָ�������ͣ����ұ������ @DiscriminatorColumn ���� length��


//ʾ�� 1-13 @DiscriminatorValue �� ����

@Entity
@DiscriminatorValue(value="VIP")
public class ValuedCustomer extends Customer {
 
}


