package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:29:04
 * To change this template use File | Settings | File Templates.
 */
//@JoinTable
//Ĭ������£�JPA �������ṩ������ӳ���Զ���������ڵ����һ�Զ�����У���ӵ�з��ϵ�ʵ�����ʱʹ��һ�����ӱ����ӱ����Ƽ�����������Ĭ�������ָ������ JPA �������ṩ������裺�ڹ�ϵ��ӵ�з��ϵ�ʵ�������У�ÿ����������һ�������С�

//�������Ҫִ�����²�������ʹ�� @JoinTable ��ע��

//����Ĭ���������ڴ�����һ�������֡���Ԥ�ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��еı�����Ч���������ӱ������

//����Ĭ���������ڴ�����һ�������֡���Ԥ�ȴ��ڵ�����ģ�Ͳ����ݻ���Ϊ���ݿ��е�������Ч���������ӱ��������

//ʹ���ض�Ŀ¼��ģʽ�������ӱ�

//ʹ��ΨһԼ������һ���������ӱ���

//ÿ��ʵ��ʹ�ö��������

//ʾ�� 1-45 ��ʾ�����ʹ�ô���עΪ Employee �� Project ֮��ʵ��Ķ�Զ��ϵָ��һ����Ϊ EMP_PROJ_EMP �����ӱ����ӱ��������У� EMP_ID �� PROJ_ID�� EMP_ID �а����������У��������У���Ϊ ID �� Employee ���е�����ֵ�� PROJ_ID �а����������У��������У�Ҳ��Ϊ ID �� Project ���е�����ֵ��

//ʾ�� 1-45 @JoinTable



//ʾ�� 1-45 @JoinTable

@Entity
public class Employee45 implements Serializable {

    Collection projects;
    
    @ManyToMany
    @JoinTable(
            name = "EJB_PROJ_EMP",
            joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "ID")
    )
    public Collection getProjects() {
        return projects;
    }

}

