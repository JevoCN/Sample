package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:50:02
 * To change this template use File | Settings | File Templates.
 */

//@NamedQueries
//�����Ҫָ����� @NamedQuery�������ʹ��һ�� @NamedQueries ��עָ������������ѯ��

//ʾ�� 1-58 @NamedQueries


@Entity
@NamedQueries({
@NamedQuery(
name="findAllEmployeesByFirstName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName = :firstname"
    ),
@NamedQuery(
name="findAllEmployeesByLasttName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.lasstName = :lastname"
    )
})
public class PartTimeEmployee58 extends Employee {
    
}


//@NamedQuery
//��ʹ�� JPA �������ṩ�����Ӧ�ó����У�����ʹ��ʵ���������̬������ִ�в�ѯ��Ҳ����Ԥ�����ѯ��������ʱ������ִ�С�

//ʹ�� @NamedQuery ��ע������ @Entity �� @MappedSuperclass ������Ԥ�����ѯ����Щ��ѯ��

//ʹ�� JPA ��ѯ���ԣ������ JSR-000220 Enterprise JavaBeans v3.0 �淶���� 4 �£����л����κλ������ݿ�Ŀ���ֲִ��

//������ʹ��

//�Ƚϸ��Ӳ������ڴ���

//�����ڲ�ͬʵ��֮�乲��

//ֻ����ʵ�壨�Ӳ����ر���ֵ������ֻ����һ�����͵�ʵ��

//����ж��Ҫ����� @NamedQuery�������ʹ�� @NamedQueries��

//Ҫ����֪�Ļ������ݿ���Ԥ����ԭ�� SQL ��ѯ������� @NamedNativeQuery��ʹ��ԭ�� SQL ��ѯ�������Է���ʵ�壨������ͬ���͵�ʵ�壩������ֵ��ͬʱ�������ߡ�

/*
ʾ�� 1-59 ��ʾ�����ʹ�� @NamedQuery ��ע����һ��JPA ��ѯ���Բ�ѯ���ò�ѯʹ����Ϊ firstname �Ĳ����� ʾ�� 1-60 ��ʾ�����ʹ�� EntityManager ��ȡ�˲�ѯ��ʹ�� Query ���� setParameter ���� firstname ������

ʾ�� 1-59 ʹ�� @NamedQuery ʵ��һ���������Ĳ�ѯ

@Entity
@NamedQuery(
name="findAllEmployeesByFirstName",
query="SELECT OBJECT(emp) FROM Employee emp WHERE emp.firstName = :firstname"
)
public class Employee implements Serializable {
    ...
}
*/

/*
ʾ�� 1-60 ִ��������ѯ

Query queryEmployeesByFirstName = em.createNamedQuery("findAllEmployeesByFirstName");
queryEmployeeByFirstName.setParameter("firstName", "John");
Collection employees = queryEmployessByFirstName.getResultList();

*/