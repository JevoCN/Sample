package org.jevo.hibernate.sample.jpa;

import javax.persistence.QueryHint;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:23:43
 * To change this template use File | Settings | File Templates.
 */
//@QueryHint
//Ĭ������£�JPA �������ṩ������� @NamedQuery �� @NamedNativeQuery Ӧ��ȫ���ղ�ѯ String ָ���ķ�ʽִ�С�

//ʹ�� @QueryHint ��עָ����Ӧ���ض��� JPA ��ѯ��չ���ԣ�

//��߲�ѯ����

//���ù�Ӧ�̵� JPA �������ṩ����ʵ���е��ض�����

//ʾ�� 1-77 ��ʾ�����ʹ�� @QueryHint ��ע�Զ����ѯ�������� TopLink Essentials �ṩ�Ĺ�Ӧ�� JPA ��չ���ڸ�ʾ���У���ʾȷ����ִ�в�ѯʱʼ��ˢ�� TopLink ���档�й���ϸ��Ϣ������� ��TopLink JPA ��ѯ��ʾ��չ����

//ʾ�� 1-77 @QueryHint

@Entity
@NamedQuery(
name="findAllEmployees",
query="SELECT * FROM EMPLOYEE WHERE MGR=1",
hints={@QueryHint={name="toplink.refresh", value="true"}})
public class Employee77 implements Serializable {

}


