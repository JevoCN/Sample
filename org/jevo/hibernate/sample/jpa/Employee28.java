package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:34:02
 * To change this template use File | Settings | File Templates.
 */

//@Enumerated
//Ĭ������£�JPA �������ṩ����־ñ���ö�ٳ���������ֵ��

//ʹ�� @Enumerated ��עָ���� String ֵ�ʺ�Ӧ�ó���Ҫ������������ݿ�ģʽƥ�������£�JPA �������ṩ�����Ƿ�Ӧ�־ñ���ö�ٳ���������ֵ�� String ֵ��

//����ע������ @Basic һ��ʹ�á�

//���� ʾ�� 1-27 �е�ö�ٳ����� ʾ�� 1-28 ��ʾ�����ʹ�ô���עָ���ڳ־ñ��� Employee ʱӦ�� SalaryRate �� String ֵд�����ݿ⡣Ĭ������£��Ὣ EmployeeStatus ������ֵд�����ݿ⡣

//ʾ�� 1-27 ö�ٳ���

enum EmployeeStatus {FULL_TIME, PART_TIME, CONTRACT}
enum SalaryRate {JUNIOR, SENIOR, MANAGER, EXECUTIVE}

//ʾ�� 1-28 @Enumerated

@Entity
public class Employee28 {
    public EmployeeStatus getStatus() {
        return EmployeeStatus.FULL_TIME;
    }

    @Enumerated(EnumType.STRING)
    public SalaryRate getPayScale() {
        return SalaryRate.JUNIOR;
    }
    
}


