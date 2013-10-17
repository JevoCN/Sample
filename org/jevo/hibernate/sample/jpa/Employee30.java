package org.jevo.hibernate.sample.jpa;

import javax.persistence.MappedSuperclass;
import javax.persistence.EntityListeners;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:37:28
 * To change this template use File | Settings | File Templates.
 */

//@ExcludeSuperclassListeners
//����̳в���е� @Entity �� @MappedSuperclass �ඨ���� @EntityListeners����Ĭ������£�JPA �������ṩ�����ڵ��������������֮ǰ���ó����������

//����������������Ϊ�����ã���ʹ�� @ExcludeSuperclassListeners ��ע���ǣ�����ֹ����Ը��� @Entity �� @MappedSuperclass ִ�еĳ����������

//@ExcludeSuperclassListeners ��ע��Ӱ��Ĭ�ϼ������������ @ExcludeDefaultListeners����

//����עû������ ���йظ�����ϸ��Ϣ������� API��

//ʾ�� 1-29 ��ʾ�����ʹ�ô���עָ����Ӧ�� PartTimeEmployee ʵ��ִ�г���������� EmployeeListener������ִ��Ĭ�ϼ������������������� PartTimeEmployeeListener1 �� PartTimeEmployeeListener2��

//ʾ�� 1-30 ���༶���ʵ���������

@MappedSuperclass
@EntityListeners(value={EmployeePersistListener.class})
public class Employee30 {

}


