package org.jevo.hibernate.sample.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:35:59
 * To change this template use File | Settings | File Templates.
 */

//@Column
//Ĭ������£�JPA �������ṩ�������ÿ��ʵ��ĳ־��ֶδ洢����������־��ֶε�������ƥ������ݿ�����С�

//ʹ�� @Column ��ע��

//���־��ֶ����������ƹ��������Ĭ���������ڴ��������ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��е�������Ч��

//���־��ֶ��븨�����е��й���������� @SecondaryTable��

//΢�����ݿ����е�����



//��ʾ�����ʹ�ô���עʹ JPA �� empId �־ñ��浽������ EMP_HR �е��� EMP_NUM��Ĭ������£�JPA �� empName �־ñ��浽���� Employee �е��� empName��
@Entity
@SecondaryTable(name = "EMP_HR")
public class Employee3 implements Serializable {

    @Column(name = "EMP_NUM", table = "EMP_HR")
    private Long empId;

    private String empName;

}
//@SecondaryTable
//Ĭ������£�JPA �������ṩ�������ʵ������г־��ֶξ��洢��һ������Ϊʵ�����Ƶ����ݿ���У��ñ������������� @Table����

//���ϣ�� JPA �ֱ�ʵ���ĳЩ�־��ֶγ־ñ��浽������������ݿ����ʹ�� @SecondaryTable ��ע��ʵ�����������ݿ��������ڸ�ʾ���У���ʹ�� @Column ��ע��ʵ��ĳ־��ֶ���������

//���Ҫ����������ศ������ʵ������������ʹ�� @SecondaryTables��
//ʾ�� 1-78 ��ʾ�����ʹ�ô���עָ��һ����Ϊ EMP_HR �ĸ������ڸ�ʾ���У�Ĭ������£�JPA ��ʵ��־��ֶ� empId �־ñ��浽��Ϊ Employee �������е��� empId������ empSalary �־ñ��浽������ EMP_HR �е��� empSalary���й���ϸ��Ϣ������� @Column��

/*ʾ�� 1-78 @SecondaryTable

@Entity
@SecondaryTable(name="EMP_HR")
public class Employee implements Serializable {
    ...
private Long empId;

@Column(table="EMP_HR", name="EMP_SALARY"))
private Float empSalary;
    ...
}
*/

//@SecondaryTables
//�����Ҫָ����� @SecondaryTable������ʹ��һ�� @SecondaryTables ��עָ�����и�����

//�� 1-41 �г��˴���ע������ ���йظ�����ϸ��Ϣ������� API��

/*
ʾ�� 1-79 ��ʾ�����ʹ�ô���עָ��������Ϊ EMP_HR �� EMP_TR �ĸ������ڸ�ʾ���У�Ĭ������£�JPA ��ʵ��־��ֶ� empId �־ñ��浽��Ϊ Employee �������е��� empId��JPA �� empSalary �־ñ��浽������ EMP_HR �е��� empSalary������ empClass �־ñ��浽������ EMP_TR �е��� EMP_HR���й���ϸ��Ϣ������� @Column��

ʾ�� 1-79 @SecondaryTables

@Entity
@SecondaryTables({
@SecondaryTable(name="EMP_HR"),
@SecondaryTable(name="EMP_TR")
    })
public class Employee implements Serializable {
    ...
private Long empId;

@Column(table="EMP_HR", name="EMP_SALARY"))
private Float empSalary;

@Column(table="EMP_TR", name="EMP_CLASS"))
private Float empClass;
    ...
}

 */


//@SequenceGenerator
//���ʹ�� @GeneratedValue ��עָ��һ�� SEQUENCE ���͵������������������ʹ�� @SequenceGenerator ��ע΢���������������ԣ�

//���ķ����С��ƥ��Ӧ�ó���Ҫ������ݿ����ܲ���

//���ĳ�ʼֵ��ƥ�����е�����ģ�ͣ����磬��������Ѿ�Ϊ����������һ������ֵ���������ݼ�������

//ʹ����������ģ����Ԥ���������

/*
ʾ�� 1-80 ��ʾ�����ʹ�ô���עΪ��Ϊ CUST_SEQ �� SEQUENCE ����������ָ�������С��

ʾ�� 1-80 @SequenceGenerator

@Entity
public class Employee implements Serializable {
    ...
@Id
@SequenceGenerator(name="CUST_SEQ", allocationSize=25)
@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
@Column(name="CUST_ID")
public Long getId() {
return id;
    }
    ...


 */


