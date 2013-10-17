package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:19:37
 * To change this template use File | Settings | File Templates.
 */
//@AssociationOverride
//Ĭ������£�JPA �������ṩ�����Զ���������̳г����ж���ĳ־����Լ������ӳ�䡣

//����̳е��ж����ʵ�岻��ȷ�����磬����̳е��������Ѿ����ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��е�������Ч������ʹ�� @AssociationOverride ��ע�Զ���� @MappedSuperclass �� @Embeddable �̳е� @OneToOne �� @ManyToOne ӳ�䣬�Ը������ֶλ����Թ����� @JoinColumn��

//����ж��Ҫ���е� @AssociationOverride ���ģ������ʹ�� @AssociationOverrides��

//Ҫ�Զ������ӳ���Ը������� @Column����ʹ�� @AttributeOverride��

//�� 1-4 �г��˴���ע������ ��

//ʾ�� 1-4 ��ʾ�� ʾ�� 1-5 �е�ʵ����չ�� @MappedSuperclass�� ʾ�� 1-5 ��ʾ�������ʵ��������ʹ�� @AssociationOverride ���� @MappedSuperclass Employee �ж��壨Ĭ������£��� @JoinColumn �Ա������ Address��    

//ʾ�� 1-2 @AssociationOverride

@Entity
@AssociationOverride(name = "address", joinColumns = @JoinColumn(name = "ADDR_ID"))
public class PartTimeEmployee extends Employee {
    @Column(name = "WAGE")
    protected Float hourlyWage;

}





