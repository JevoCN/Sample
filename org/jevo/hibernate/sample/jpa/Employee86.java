package org.jevo.hibernate.sample.jpa;

import org.hibernate.Session;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:31:00
 * To change this template use File | Settings | File Templates.
 */
//@Table
//Ĭ������£�JPA �������ṩ�������ʵ������г־��ֶξ��洢��һ������Ϊʵ�����Ƶ����ݿ���У������ @Entity����

//�����������£�ʹ�� @Table ��עָ����ʵ�����������

//ʵ���������ڴ�����һ�������֡���Ԥ�ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��еı�����Ч

//��Ҫ���Ʊ�������Ŀ¼��ģʽ

//���ϣ�� JPA ��ĳЩ�ֶγ־ñ��浽�������������ֶγ־ñ��浽һ����������������� @SecondaryTable��

//ʾ�� 1-86 ��ʾ�����ʹ�ô���עָ����������
//ʾ�� 1-86 @Table
//ʾ�� 1-90 ��ʾ�����ʹ�ô���ע������ EMP �е��� EMP_ID �� EMP_NAME ָ��һ��ΨһԼ����
//ʾ�� 1-90 ʹ��ΨһԼ���� @Table
@Entity
@Table(name = "EMP", uniqueConstraints = {@UniqueConstraint(columnNames = {"EMP_ID", "EMP_NAME"})})
public class Employee86 implements Serializable {
    Long id;
    //ʾ�� 1-87 ��ʾ�����ʹ�ô���עΪ��Ϊ empGen �� TABLE ����������ָ�������С��

    //ʾ�� 1-87 @TableGenerator
    @Id
    @TableGenerator(name = "empGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "empGen")
    @Column(name = "CUST_ID")
    public Long getId() {
        return id;
    }

    //ʾ�� 1-88 ��ʾ�����ʹ�ô���עָ�� JPA �������ṩ����Ӧ�� java.util.Date �ֶ� startDate �־ñ���Ϊ DATE ( java.sql.Date) ���ݿ����͡�
    @Temporal(TemporalType.DATE)
    protected java.util.Date startDate;

    //ʾ�� 1-89 ��ʾ�����ʹ�ô���ע�� Employee �ֶ� currentSession ָ��Ϊ�ǳ־��ֶΡ�JPA �������ṩ���򽫲��־ñ�����ֶΡ�
    @Transient
    Session currentSession;

    //ʾ�� 1-91 ��ʾ�����ʹ�ô���ע������ getVersionNum ָ��Ϊ�ֹ�����ֵ���ڸ�ʾ���У������Ե���������Ϊ OPTLOCK������� @Column�����������Ե�Ĭ��������
    int versionNum;
    @Version
    @Column(name = "OPTLOCK")
    protected int getVersionNum() {
        return versionNum;
    }

}

//@TableGenerator
//���ʹ�� @GeneratedValue ��עָ��һ�� TABLE ���͵�����������������ʹ�� @TableGenerator ��ע΢���������������ԣ�

//�����������ڴ�����һ�������֡���Ԥ�ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��еı�����Ч�����������������ı�����

//���ķ����С��ƥ��Ӧ�ó���Ҫ������ݿ����ܲ���

//���ĳ�ʼֵ��ƥ�����е�����ģ�ͣ����磬��������Ѿ�Ϊ����������һ������ֵ���������ݼ�������

//ʹ���ض�Ŀ¼��ģʽ���������������ı�

//���������������һ�л����������һ��Ψһ��Լ��

//@Temporal
//ʹ�� @Temporal ��עָ�� JPA �������ṩ����ӦֻΪ java.util.Date �� java.util.Calendar ���͵��ֶλ����Գ־ñ�������ݿ����͡�

//����ע������ @Basic һ��ʹ�á�

//@Transient
//Ĭ������£�JPA �������ṩ�������ʵ��������ֶξ�Ϊ�־��ֶΡ�

//ʹ�� @Transient ��עָ��ʵ��ķǳ־��ֶλ����ԣ����磬һ��������ʱʹ�õ�����ʵ��״̬һ���ֵ��ֶλ����ԡ�

//JPA �������ṩ���򲻻����עΪ @Transient �����Ի��ֶγ־ñ��棨�򴴽����ݿ�ģʽ����

//����ע������ @Entity�� @MappedSuperclass �� @Embeddable һ��ʹ�á�

//����עû�����ԡ��йظ�����ϸ��Ϣ������� API��

//@UniqueConstraint
//Ĭ������£�JPA �������ṩ������������о����԰����ظ�ֵ��

//ʹ�� @UniqueConstraint ��עָ������Ϊ������������ɵ� DDL �а���һ��ΨһԼ�������ߣ����������м���ָ��ΨһԼ��������� @Column����

//@Version
//Ĭ������£�JPA �������ṩ�������Ӧ�ó���������һ���ԡ�

//ʹ�� @Version ��עͨ��ָ���������ֹ�����ֵ��ʵ����İ汾�ֶλ����������� JPA ������ֹ��������Ƽ���������

//ѡ��汾�ֶλ�����ʱ��ȷ����

//ÿ��ʵ��ֻ��һ���汾�ֶλ�����

//ѡ��һ���־ñ��浽��������Ի��ֶΣ������ @Table��

//����Ӧ�ó����޸İ汾���Ի��ֶ�

//����עû������ ���йظ�����ϸ��Ϣ������� API��

