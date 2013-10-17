package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:22:21
 * To change this template use File | Settings | File Templates.
 */
//@PrimaryKeyJoinColumns
//Ĭ������£�JPA �������ṩ�������ÿ��ʵ����һ������������

//���Ҫָ��һ�����������������ɵ���������ʹ�� @PrimaryKeyJoinColumns ��ע��

//ʾ�� 1-76 ��ʾ�����ʹ�ô���עָ��һ������ CUST_ID �� CUST_TYPE ��ɵĸ���������

//ʾ�� 1-76 @PrimaryKeyJoinColumns


@Entity
@Table(name="VCUST")
@DiscriminatorValue("VCUST")
@PrimaryKeyJoinColumns({
@PrimaryKeyJoinColumn(name="CUST_ID",referencedColumnName="ID"),
@PrimaryKeyJoinColumn(name="CUST_TYPE",referencedColumnName="TYPE")
})
public class ValuedCustomer76 extends Customer74 { 

}


