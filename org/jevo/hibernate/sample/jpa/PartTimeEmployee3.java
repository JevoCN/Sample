package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:31:20
 * To change this template use File | Settings | File Templates.
 */
//@AttributeOverride
//Ĭ������£�JPA �������ṩ�����Զ���������̳г����ж���ĳ־����Լ������ӳ�䡣

//������ʵ��̳е��ж��岻��ȷ����ʹ�� @AttributeOverride ��ע�Զ���һ���� @MappedSuperclass �� @Embeddable �̳еĻ���ӳ���Ը������ֶλ����Թ����� @Column�������磬����̳е����������ȴ��ڵ�����ģ�Ͳ����ݣ�������Ϊ���ݿ��е�������Ч����

//����ж��Ҫ���е� @AttributeOverride ���ģ������ʹ�� @AttributeOverrides��

//Ҫ�Զ������ӳ���Ը������� @JoinColumn����ʹ�� @AssociationOverride��


//ʾ�� 1-4 ��ʾ�� ʾ�� 1-5 �е�ʵ����չ�� @MappedSuperclass�� ʾ�� 1-5 ��ʾ�����ʹ��ʵ�������е� @AttributeOverride ���� @MappedSuperclass Employee �ж��壨Ĭ������£��� @Column���Ա����ӳ�䵽 Address��


//1-5 @AttributeOverride


@Entity
@AttributeOverride(name = "address", column = @Column(name = "ADDR_STRING"))
public class PartTimeEmployee3 extends Employee {
    @Column(name = "WAGE")
    protected Float hourlyWage;

}
