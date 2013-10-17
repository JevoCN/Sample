package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:47:40
 * To change this template use File | Settings | File Templates.
 */

//@NamedNativeQuery
//��ʹ�� JPA �������ṩ�����Ӧ�ó����У�����ʹ��ʵ���������̬������ִ�в�ѯ��Ҳ����Ԥ�����ѯ��������ʱ������ִ�С�

//ʹ�� @NamedNativeQuery ��ע������ @Entity �� @MappedSuperclass ������Ԥ�����ѯ����Щ��ѯ��

//ʹ�û������ݿ��ԭ�� SQL

//������ʹ��

//�Ƚϸ��Ӳ������ڴ���

//�����ڲ�ͬʵ��֮�乲��

//����ʵ�塢����ֵ�����ߵ���ϣ�������� @ColumnResult�� @EntityResult�� @FieldResult �� @SqlResultSetMapping��

//����ж��Ҫ����� @NamedNativeQuery�������ʹ�� @NamedNativeQueries��

//ҪԤ�����ʺ����κ����ݿ�Ŀ���ֲ��ѯ������� @NamedQuery��

//ʾ�� 1-56 ��ʾ�����ʹ�� @NamedNativeQuery ��ע����һ��ʹ�û������ݿ��ԭ�� SQL �Ĳ�ѯ�� ʾ�� 1-57 ��ʾ�����ʹ�� EntityManager ��ȡ�˲�ѯ�Լ����ͨ�� Query ���� getResultList ִ�иò�ѯ��

//ʾ�� 1-56 ʹ�� @NamedNativeQuery ʵ��һ�� Oracle ��β�ѯ



@Entity
@NamedNativeQuery(
        name = "findAllEmployees",
        query = "SELECT * FROM EMPLOYEE"
)
public class Employee56 implements Serializable {


/*    ʾ�� 1-57 ִ��һ������ԭ����ѯ
    Query queryEmployees = em.createNamedQuery("findAllEmployees");
    Collection employees = queryEmployees.getResultList();
*/

}


