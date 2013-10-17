package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:17:58
 * To change this template use File | Settings | File Templates.
 */

//@EntityListeners
//����ʹ������������ע������� ���������¼���ע��ָ��ʵ���еķ�������Щ������ָ�������������¼�����ʱִ�������߼���

//ʹ�� @EntityListeners ��ע��һ������ʵ������������� @Entity �� @MappedSuperclass ����������������Ҫ��ָ�������������¼�����ʱִ���߼����Լ���

//��ϣ����ʵ�� API �й����������ڼ������򷽷���

//Ҫ�ڲ�ͬ��ʵ������֮�乲���������ڼ��������߼���

//��ʵ��������Ϸ������������¼�ʱ��JPA �������ṩ���򽫰������������˳��֪ͨÿ��ʵ��������򣬲�����ʹ����Ӧ�����������¼����ͽ�����ע��ʵ��������򷽷�������У���

//ʵ������������������������

//����һ����ͨ�ľ�ʽ Java ���� (POJO) ��

//����һ��������������ǩ���Ļص�������

//public void <MethodName>(Object)

//����ָ���������� Object����ʵ������������������ʵ��������͡�

//����һ���������������¼���ע��ÿ���ص�����������ע��

//һ�����������¼�ֻ����һ���ص��������򷽷���������ĳ�������Ļص��������򷽷������������������¼�������

//���ʹ��ʵ�������������Թ�����Щʵ���������ʹ�� @ExcludeDefaultListeners �� @ExcludeSuperclassListeners ���á�

//ʾ�� 1-21 ��ʾ�����ʹ�ô���ע��ʵ����������� EmployeePersistListener������� ʾ�� 1-22���� EmployeeRemoveListener������� ʾ�� 1-23����ʵ�� Employee ������ ʾ�� 1-23 ��ʾ�������Խ�������������¼��������ʵ����������෽�����������κθ��������������¼�ֻ����ʵ������������г���һ�Ρ�




@Entity
@EntityListeners(value={EmployeePersistListener.class, EmployeeRemoveListener.class})
public class Employee21 implements Serializable {

}

