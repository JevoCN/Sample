package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.print.DocFlavor;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:52:23
 * To change this template use File | Settings | File Templates.
 */
//@DiscriminatorValue
//Ĭ������£��� @Inheritance ���Բ���Ϊ InheritanceType.SINGLE_TABLE �� JOINED ʱ��JPA �������ṩ����ʹ�� @DiscriminatorColumn ��ʵ���������ּ̳в���е��ࣨ����� @Entity����

//ʹ�� @DiscriminatorValue ��עָ���������ִ˼̳в���е�ʵ��ı�ʶ��ֵ��

//���ʵ�����Ʋ��ʺ��ڴ�Ӧ�ó���

//ƥ�����е����ݿ�ģʽ


//ʾ�� 1-14 ��ʾ�����ʹ�ô���עָ��һ����Ϊ DISC������Ϊ STRING������Ϊ 20 �ı�ʶ���С��ڱ�ʾ���У������ @DiscriminatorValue ָ��Ϊ CUST�� ʾ�� 1-15 �е����ཫ���Լ��� @DiscriminatorValue ָ��Ϊ VIP���� Customer �� ValuedCustomer �У� @DiscriminatorValue ��ֵ�������ת��Ϊ�� @DiscriminatorColumn ���� discriminatorType ָ�������ͣ����ұ������ @DiscriminatorColumn ���� length��

//ʾ�� 1-14 @DiscriminatorColumn �� @DiscriminatorValue �� ����

@Entity
@Table(name="CUST")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DISC", discriminatorType= javax.persistence.DiscriminatorType.STRING, length=20)
@DiscriminatorValue(value="CUST")
public class Customer2 {

}

