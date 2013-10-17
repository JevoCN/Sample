package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:33:53
 * To change this template use File | Settings | File Templates.
 */

//@Entity
//ʹ�� @Entity ��ע����ͨ�ľ�ʽ Java ���� (POJO) ��ָ��Ϊʵ�壬��ʹ������� JPA ���񡣱��뽫 POJO ��ָ��Ϊʵ�壬Ȼ��ſ���ʹ���κ����� JPA ��ע��

//@Basic
//Ĭ������£�JPA �������ṩ����Ϊ����� Java ��Ԫ���͡���Ԫ���͵İ�װ�����Լ�ö���Զ�����һ�� @Basic ӳ�䡣

//ʹ�� @Basic ��ע��

//����ȡ��������Ϊ LAZY

//�����ֵ���ʺ���Ӧ�ó�����ӳ������Ϊ��ֹ��ֵ����Էǻ�Ԫ���ͣ�


//@GeneratedValue
//Ĭ������£�JPA �������ṩ�������Ϊʵ�������ṩ��Ψһ��ʶ��������� @Id����

//���Ҫ΢���˻�����ʵ������Ŀ�ģ���ʹ�� @GeneratedValue ��ע��

//������о���һ�����������͸��ʺ������ݿ��Ӧ�ã��򸲸ǳ������ṩ����Ϊ���ݿ�ѡ������ֵ���ɵ�����

//������������ڴ�����һ�������֡������ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��е�����������������Ч���򸲸ǳ������ṩ����ѡ�����������������

//@Id
//ʹ�� @Id ��ע��һ�������־��ֶλ�����ָ��Ϊʵ���������

//����ÿ��ʵ�壬��������ָ��������֮һ��

//һ�� @Id

//��� @Id ��һ�� @IdClass�����ڸ���������

//һ�� @EmbeddedId

//����עû������ ���йظ�����ϸ��Ϣ������� API��

//Ĭ������£�JPA �������ṩ����ѡ������ʵ������������������ @GeneratedValue���������������ֵ�������ز�ȡ�κν�һ���Ĳ��������Ҫʹ�� JPA �������ṩ�����Ĭ�ϼ����ɻ��ƣ��򲻱ز�ȡ�κν�һ���Ĳ�����


@Entity
public class Employee2 implements Serializable {
    //ʾ�� 1-36 ��ʾ�����ʹ�ô���ע���־��ֶ� empID ָ��Ϊ Employee ���������
    //ʾ�� 1-36 @Id
    @Id    
    protected Long id;
    protected String name;


    ////��ʾ�����ʹ��@Basic��עΪ����ӳ��ָ����ȡ���� LAZY��
    @Basic(fetch = FetchType.LAZY)
    protected String getName() {
        return name;
    }


    //ʾ�� 1-35 ��ʾ�����ʹ�ô���עָʾ�������ṩ����ʹ����Ϊ CUST_SEQ������Ϊ GeneratorType.SEQUENCE ��������������
    //ʾ�� 1-35 @GeneratedValue
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @Column(name = "CUST_ID")
    public Long getId() {
        return id;
    }


    //ʾ�� 1-66 Employee ʵ��
    @ManyToMany(mappedBy="employees")
// By default, returns a List in ascending order by empId
    public List<Project> getProjects() {
        return null;
    }

}

