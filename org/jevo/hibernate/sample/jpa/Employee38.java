package org.jevo.hibernate.sample.jpa;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.util.Date;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:19:04
 * To change this template use File | Settings | File Templates.
 */
//@IdClass
//ʹ�� @IdClass ��עΪʵ��ָ��һ�����������ࣨͨ��������������Ԫ���ͻ� JDK ����������ɣ�����ԭ�����ݿ�ӳ��ʱ����ʱ���ݿ���ɶ�����ɣ���ͨ�������ָ���������

//�����������������������

//����һ����ͨ�ľ�ʽ Java ���� (POJO) �ࡣ

//������Ϊ public�����ұ�����һ�� public �޲������캯����

//���ʹ�û������Եķ��ʣ�������������Ա���Ϊ public �� protected��

//�������ǿ����л��ġ�

//�����붨�� equals �� hashCode ������

//��Щ������ֵ����Ե�����������ӳ�䵽�����ݿ����͵����ݿ������һ�¡�

//�����ֶλ����Ե����ͺ����Ʊ�����ʹ�� @Id ������ע��ʵ�������ֶλ����Ե����ͺ��������Ӧ��

//���ߣ�������ʹ�����������Ϊ��ʵ��ӵ�е�Ƕ���ࣨ����� @EmbeddedId����

//ʾ�� 1-37 ��ʾ��һ����Ƕ��ĸ��������ࡣ�ڸ����У��ֶ� empName �� birthDay �����ƺ����ͱ����Ӧ��ʵ���������Ե����ƺ����͡� ʾ�� 1-38 ��ʾ�����ʹ�������Ƕ��ĸ��������ࣨʹ�� @IdClass ��ע������ EJB 3.0 ʵ�塣����ʵ�����ֶ� empName �� birthDay ��������ʹ�ã���˻�����ʹ�� @Id ��ע���������ע��


@IdClass(EmployeePK37.class)
@Entity
public class Employee38 {
    @Id
    String empName;
    @Id
    Date birthDay;

}

