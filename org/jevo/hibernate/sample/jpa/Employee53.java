package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.mail.Address;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:40:48
 * To change this template use File | Settings | File Templates.
 */
//@MappedSuperclass
//Ĭ������£�JPA �������ṩ�������ʵ������г־��ֶξ��ڸ�ʵ���ж��塣

//ʹ�� @MappedSuperclass ��עָ��һ��ʵ������м̳г־��ֶεĳ��ࡣ�����ʵ���๲��ͨ�õĳ־��ֶλ�����ʱ���⽫��һ�������ģʽ��

//���������ʵ������ʹ���κ�ֱ�Ӻ͹�ϵӳ����ע���� @Basic �� @ManyToMany���Ըó�����ֶκ����Խ�����ע��������û����Ըó��౾��ı���ڣ������Щӳ��ֻ�������������ࡣ�̳еĳ־��ֶλ�������������ı�

//������������ʹ�� @AttributeOverride �� @AssociationOverride ��ע�����ǳ����ӳ�����á�

//����עû�����ԡ��йظ�����ϸ��Ϣ������� API��

//ʾ�� 1-53 ��ʾ�����ʹ�ô���ע�� Employee ָ��Ϊӳ�䳬�ࡣ ʾ�� 1-54 ��ʾ�������չʵ���еĴ˳��࣬�Լ������ʵ������ʹ�� @AttributeOverride �Ը��ǳ��������õ����á�

//ʾ�� 1-53 @MappedSuperclass

@MappedSuperclass
public class Employee53 {
    @Id
    protected Integer empId;

    @Version
    protected Integer version;

    @ManyToOne
    @JoinColumn(name = "ADDR")
    protected Address address;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer id) {
        this.empId = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addr) {
        this.address = addr;
    }
}


