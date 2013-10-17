package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:45:19
 * To change this template use File | Settings | File Templates.
 */
//@DiscriminatorColumn
//Ĭ������£��� @Inheritance ���Բ���Ϊ InheritanceType.SINGLE_TABLE �� JOINED ʱ��JPA �������ṩ���򽫴���һ����Ϊ DTYPE �ı�ʶ���������ּ̳в���е��ࡣ

//ʹ�� @DiscriminatorColumn ��ע��

//ָ��һ����ʶ���������������ģ���е���������Ĭ������ DTYPE����

//ָ��һ��������Ӧ�ó�������ȴ��ڵ�����ģ�͵ı�ʶ���г���

//΢�����ݿ��еı�ʶ���е�����

//ʾ�� 1-12 ��ʾ�����ʹ�ô���עָ��һ����Ϊ DISC������Ϊ STRING������Ϊ 20 �ı�ʶ���С��ڱ�ʾ���У������ @DiscriminatorValue ָ��Ϊ CUST�� ʾ�� 1-13 �е����ཫ���Լ��� @DiscriminatorValue ָ��Ϊ VIP���� Customer �� ValuedCustomer �У� @DiscriminatorValue ��ֵ�������ת��Ϊ�� @DiscriminatorColumn ���� discriminatorType ָ�������ͣ����ұ������ @DiscriminatorColumn ���� length��

//ʾ�� 1-12 @DiscriminatorColumn �� @DiscriminatorValue �� ����


@Entity
@Table(name = "CUST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 20)
@DiscriminatorValue(value = "CUST")
public class Customer {

//@OneToMany
//Ĭ������£�JPA Ϊ����һ�Զ�����ԵĶ�ֵ��������һ�� OneToMany ӳ�䡣
//ʹ�� @OneToMany ��ע��
//����ȡ��������Ϊ LAZY
//������ʹ�õ� Collection ����ʹ��һ���������ģ�������ù�����Ŀ��ʵ��
//���ñ�����������Ŀ��Ĳ��������磬���ɾ����ӵ��ʵ�壬��ȷ����ɾ��������Ŀ��
//���ó������ṩ����Ե���һ�Զ��ϵʹ�õ����ӱ������ @JoinTable������ϸ��Ϣ
    //ʾ�� 1-61 �� ʾ�� 1-62 ��ʾ�����ʹ�ô���ע��ʹ��һ������� Customer����ӵ�з����� Order��ӵ�з���֮������һ��һ�Զ�ӳ��
    //ʾ�� 1-61 @OneToMany - ʹ��һ������� Customer ��
    Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch=FetchType.LAZY)
    public Set<Order> getOrders() {
        return orders;
    }

//@OneToOne
//Ĭ������£�JPA Ϊָ����һ������һ��һ�����Ե�ʵ��ĵ�ֵ��������һ�� OneToOne ӳ�䣬���ӱ����õĶ��������ƶϳ�������Ŀ��ʵ�塣
//ʹ�� @OneToOne ��ע��
//����ȡ��������Ϊ LAZY
//�����ֵ���ʺ���Ӧ�ó�����ӳ������Ϊ��ֹ��ֵ����Էǻ�Ԫ���ͣ�
//���ù�����Ŀ��ʵ�壨����޷��ӱ����õĶ��������ƶϳ�����
//���ñ�����������Ŀ��Ĳ��������磬���ɾ����ӵ��ʵ�壬��ȷ����ɾ��������Ŀ��
//    ʾ�� 1-63 �� ʾ�� 1-64 ��ʾ�����ʹ�ô���ע�� Customer��ӵ�з����� CustomerRecord����ӵ�з���֮������һ��һ��һӳ�䡣
//    ʾ�� 1-63 @OneToOne - Customer ��    
    CustomerRecord customerRecord;
    @OneToOne(optional = false)     
    @JoinColumn(name = "CUSTREC_ID", unique = true, nullable = false, updatable = false)
    public CustomerRecord getCustomerRecord() {
        return customerRecord;
    }

}

//@Inheritance
//Ĭ������£�JPA �������ṩ�����Զ�����̳в����ʵ��ĳ����ԡ�
//ʹ�� @Inheritance ��ע�Զ���������ṩ����ļ̳в��֧�֣������Ӧ�ó������ܻ�ƥ�����е�����ģ�͡�
//ʹ�ô���עָ�� Customer ���������ཫʹ�� InheritanceType.JOINED�� ʾ�� 1-40 �е����ཫӳ�䵽���Լ��ı��ñ���� ValuedCustomer ��ÿ���־����԰���һ�У���һ������У����� Customer �����������
//Ĭ������£� InheritanceType.SINGLE_TABLE Ӧ���� Customer �����������ࡣ�ڸ�ʾ���У�Ĭ�ϱ�ʶ������ DTYPE������� @DiscriminatorColumn��ָ��Ϊ���б�ʶ������ INTEGER���� Customer �� @DiscriminatorValue ָ��Ϊ 1�� ʾ�� 1-42 ��ʾ����ν����� ValuedCustomer �ı�ʶ��ֵָ��Ϊ 2���ڸ�ʾ���У� Customer �� ValuedCustomer �����г־����Խ�ӳ�䵽һ����

//ʾ�� 1-41 @Inheritance �� ָ�����ʶ���еĸ���
/*
@Entity
@DiscriminatorColumn(discriminatorType=DiscriminatorType.INTEGER)
@DiscriminatorValue(value="1")
public class Customer {
    ...
}
ʾ�� 1-42 @Inheritance �� ָ�����ʶ��ֵ������

@Entity
@DiscriminatorValue(value="2")
public class ValuedCustomer extends Customer {
    ...
}

*/