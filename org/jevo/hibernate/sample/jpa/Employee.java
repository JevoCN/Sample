package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import javax.mail.Address;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:18:51
 * To change this template use File | Settings | File Templates.
 */

/*
���ʹ�� @AssociationOverride���� Employee ����������У�
ID
VERSION
ADDR_ID
WAGE

�����ʹ�� @AssociationOverride���� Employee ����������У�
ID
VERSION
ADDRESS
WAGE
 */

@MappedSuperclass
public class Employee {
    @Id
    protected Integer id;
    @Version
    protected Integer version;
    @ManyToOne
    protected Address address;

    @ManyToOne
    @JoinColumn(name = "ADDR_ID")
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

//@MappedSuperclass
//public class Employee2 {
//    @Id
//    protected Integer id;
//    @Version
//    protected Integer version;
//    protected String address;
//}

