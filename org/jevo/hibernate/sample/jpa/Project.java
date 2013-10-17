package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.Map;
import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:37:23
 * To change this template use File | Settings | File Templates.
 */
//@MapKey
//Ĭ������£�JPA �������ṩ����������ʵ�������Ϊ java.util.Map ���͵Ĺ����� Map ����

//�����������עΪ @Id �ķǸ�������������ֶλ����Ե�����ʵ�������� Map ����

//�����������עΪ @IdClass �ĸ������������������ʵ�������� Map ����

//ʹ�� @MapKey ��ע��

//��ĳ�������ֶλ�����ָ��Ϊ Map �����������ʵ����������ʺ���Ӧ�ó���

//ָ��һ��Ƕ��ĸ��������ࣨ����� @EmbeddedId��

//ָ�����ֶλ����Ա������ΨһԼ��������� @UniqueConstraint����

//�� ʾ�� 1-52 �У�Project ����Ϊ Map �� Employee ʵ��ӵ��һ�Զ��ϵ�� ʾ�� 1-52 ��ʾ�����ʹ�� @MapKey ��עָ���� Map �ļ�Ϊ Employee �ֶ� empPK������һ������Ϊ EmployeePK������� ʾ�� 1-52����Ƕ��ʽ��������������� ʾ�� 1-51����

//ʾ�� 1-50 ʹ�� @MapKey �� Project ʵ��




@Entity
public class Project {
    @OneToMany(mappedBy = "project")
    @MapKey(name = "empPK")
    public Map<EmployeePK, Employee> getEmployees() {
        return null;
    }

//@OrderBy
//Ĭ������£�JPA �������ṩ���򰴹���ʵ�������������˳����� Collection �����ĳ�Ա��
//�� @OrderBy ��ע�� @OneToMany �� @ManyToMany һ��ʹ���Ա㣺
//ָ��һ��������Ϊ�������ݵ������ֶλ�����
//Ϊÿ���������ֶλ�������ָ����ͬ�������������
//    ʾ�� 1-65 ��ʾ�����ʹ�� @OrderBy ��עָ�� Project ���� getEmployees Ӧ�� Employee �ֶ� lastname ������˳�򲢰� Employee �ֶ� seniority �Խ���˳�򷵻� Employee �� List�� ʾ�� 1-66 ��ʾ��Ĭ������£� Employee ���� getProjects �� Employee ���� empId ������˳�򷵻� List��
//    ʾ�� 1-65 Project ʵ��  
    @ManyToMany
    @OrderBy("lastname ASC", "seniority DESC")
    public List<Employee2> getEmployeeList() {
        return null;
    }

}

