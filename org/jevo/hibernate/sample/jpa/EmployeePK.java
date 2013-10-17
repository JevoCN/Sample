package org.jevo.hibernate.sample.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:00:38
 * To change this template use File | Settings | File Templates.
 */
//@EmbeddedId

//ʹ�� @EmbeddedId ��עָ��һ����ʵ��ӵ�еĿ�Ƕ�븴�������ࣨͨ��������������Ԫ���ͻ� JDK ����������ɣ�����ԭ�����ݿ�ӳ��ʱ����ʱ���ݿ���ɶ�����ɣ���ͨ�������ָ���������

//�����������������������

//����һ����ͨ�ľ�ʽ Java ���� (POJO) �ࡣ

//������Ϊ public�����ұ�����һ�� public �޲������캯����

//���ʹ�û������Եķ��ʣ�������������Ա���Ϊ public �� protected��

//�������ǿ����л��ġ�

//�����붨�� equals �� hashCode ������

//��Щ������ֵ����Ե�����������ӳ�䵽�����ݿ����͵����ݿ������һ�¡�

//���ߣ�������ʹ�����������Ϊ��Ƕ���ࣨ����� @IdClass����

//����עû������ ���йظ�����ϸ��Ϣ������� API��

//ʾ�� 1-18 ��ʾ��һ����עΪ @Embeddable �ĵ��͸��������ࡣ ʾ��1-19 ��ʾ�����ʹ�ÿ�Ƕ��ĸ��������ࣨʹ�� @EmbeddedId ��ע������һ��ʵ�塣

//ʾ�� 1-18 ��Ƕ�븴��������



@Embeddable
public class EmployeePK implements Serializable {
    private String name;
    private long id;

    public EmployeePK() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int hashCode() {
        return (int) (name.hashCode() + id);
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EmployeePK)) return false;
        if (obj == null) return false;
        EmployeePK pk = (EmployeePK) obj;
        return pk.id == id && pk.name.equals(name);
    }
}

