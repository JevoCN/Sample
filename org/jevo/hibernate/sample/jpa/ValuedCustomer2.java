package org.jevo.hibernate.sample.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:54:29
 * To change this template use File | Settings | File Templates.
 */

// ʾ�� 1-15 �е����ཫ���Լ��� @DiscriminatorValue ָ��Ϊ VIP���� Customer �� ValuedCustomer �У� @DiscriminatorValue ��ֵ�������ת��Ϊ�� @DiscriminatorColumn ���� discriminatorType ָ�������ͣ����ұ������ @DiscriminatorColumn ���� length��

//ʾ�� 1-15 @DiscriminatorValue �� ����

@Entity
@DiscriminatorValue(value="VIP")
public class ValuedCustomer2 extends Customer { 

}


