package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.mail.Address;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:27:20
 * To change this template use File | Settings | File Templates.
 */

//@JoinColumns
//Ĭ������£���ʵ������У�JPA �������ṩ�������ʹ��һ�������С�

//���Ҫָ����������������У�����������������ʹ�� @JoinColumns ��ע��

//ʾ�� 1-44 ��ʾ�����ʹ�ô���עָ�����������е����ƣ� Employee ���е� ADDR_ID�����а��� Address ���� ID �е����ֵ���Լ� Employee ���е� ADDR_ZIP�����а��� Address ���� ZIP �е����ֵ����

//ʾ�� 1-44 @JoinColumns

@Entity
public class Employee44 implements Serializable {
    protected Address address;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ADDR_ID", referencedColumnName = "ID"),
        @JoinColumn(name = "ADDR_ZIP", referencedColumnName = "ZIP")
            })
    public Address getAddress() {
        return address;
    }

}

//@JoinColumn
//Ĭ������£���ʵ������У�JPA �������ṩ����ʹ��һ�������������ƣ����ֶλ��������ƣ������ݿ�ģʽ���Ա��������Զ�ȷ��Ҫʹ�õĵ��������У�����������У���

//������������ʹ�� @JoinColumn ��ע��

//Ĭ���������������ڴ�����һ�������֡���Ԥ�ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��е�������Ч

//����Ҫʹ���ⲿ���е��У��������У���������

//����Ҫʹ����������������У������ @JoinColumns��

//����Ҫʹ��һ�����ӱ������ @JoinTable��
/*
ʾ�� 1-43 ��ʾ�����ʹ�ô���עʹ JPA �����ݿ�� Employee �� ADDR_ID ���������С�

ʾ�� 1-43 @JoinColumn

@Entity
public class Employee implements Serializable {
    ...
@ManyToOne
@JoinColumn(name="ADDR_ID")
public Address getAddress() {
return address;
    }
}
*/