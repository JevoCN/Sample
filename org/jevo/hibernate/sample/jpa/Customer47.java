package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:32:54
 * To change this template use File | Settings | File Templates.
 */
//@ManyToMany
//Ĭ������£�JPA Ϊ���ж�Զ�����Ե�Ϊ��ֵ�����Զ�����һ�� @ManyToMany ӳ�䡣

//ʹ�� @ManyToMany ��ע��

//����ȡ��������Ϊ LAZY

//�����ֵ���ʺ���Ӧ�ó�����ӳ������Ϊ��ֹ��ֵ����Էǻ�Ԫ���ͣ�

//������ʹ�õ� Collection ����ʹ��һ���������ģ�������ù�����Ŀ��ʵ��

//���ñ�����������Ŀ��Ĳ��������磬���ɾ����ӵ��ʵ�壬��ȷ����ɾ��������Ŀ��

//�����ɳ������ṩ����ʹ�õ����ӱ����ϸ��Ϣ������� @JoinTable��

//ʾ�� 1-47 �� ʾ�� 1-48 ��ʾ�����ʹ�ô���ע��ʹ��һ������� Customer �� PhoneNumber ֮������һ����Զ�ӳ�䡣

//ʾ�� 1-47 @ManyToMany �� ʹ��һ������� Customer ��


@Entity
public class Customer47 implements Serializable {
    Set<PhoneNumber> phones;

    @ManyToMany
    @JoinTable(
            name = "CUST_PHONE",
            joinColumns =
            @JoinColumn(name = "CUST_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "PHONE_ID", referencedColumnName = "ID")
    )
    public Set<PhoneNumber> getPhones() {
        return phones;
    }

}

