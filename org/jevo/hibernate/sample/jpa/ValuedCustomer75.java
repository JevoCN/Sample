package org.jevo.hibernate.sample.jpa;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:20:32
 * To change this template use File | Settings | File Templates.
 */
//@PrimaryKeyJoinColumn
//Ĭ������£���һ��ʵ��ʹ�� InheritanceType.JOINED������� @Inheritance����չ��һ��ʵ��ʱ��JPA �������ṩ������������������볬�������������ͬ����

//ʹ�� @PrimaryKeyJoinColumn ��ע��

//���������������������г��������������в�ͬ��

//ʹ�� @SecondaryTable ��ע�����������ӵ�����

//�� @OneToOne ӳ���У�����ʵ�����������������ʵ��������

//ʹ�ø������������� @PrimaryKeyJoinColumns��

//ʾ�� 1-74 ��ʾ��һ��ʵ����� Customer�� ʾ�� 1-75 ��ʾ�����ʹ�� @PrimaryKeyJoinColumn �� ValuedCustomer�� Customer ��һ�����ࣩ��������ָ������������ CUST_ID��

//ʾ�� 1-74 @PrimaryKeyJoinColumn - InheritanceType.JOINED ����


//ʾ�� 1-75 @PrimaryKeyJoinColumn - InheritanceType.JOINED ����

@Entity
@Table(name="VCUST")
@DiscriminatorValue("VCUST")
@PrimaryKeyJoinColumn(name="CUST_ID")
public class ValuedCustomer75 extends Customer74 {
     
}


