package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:56:18
 * To change this template use File | Settings | File Templates.
 */
//@Embedded

//Ĭ������£�JPA �������ṩ�������ÿ��ʵ����־ñ��浽���Լ������ݿ��

//ʹ�� @Embedded ��עָ��һ���־��ֶΣ����ֶε� @Embeddable ���Ϳ��Դ洢Ϊӵ��ʵ��Ĺ��в��֣��������ʵ�����ݡ�Ƕ������ÿ���־����Ի��ֶξ�ӳ�䵽ӵ��ʵ������ݿ��

//���Խ��ʹ�� @Embedded �� @Embeddable �Խ����ϸ�����Ȩ��ϵ��ģ�ͣ��Ա���ɾ����ӵ�ж��������»���ɾ����ӵ�еĶ���

//Ƕ��Ķ���Ӧӳ�䵽�����

//Ĭ������£� @Embeddable ����ָ�����ж��壨����� @Column�������� @Embedded �ࡣ���Ҫ������Щ�ж��壬��ʹ�� @AttributeOverride��

//����עû�����ԡ�

//ʾ�� 1-17 ��ʾ�����ʹ�ø���עָ���� @Embeddable �� EmploymentPeriod������� ʾ�� 1-16������ʹ��ָ�������Ը��ǣ������ @AttributeOverride��Ƕ�뵽ʵ�����С��������Ҫ���Ը��ǣ��������ȫ���� @Embedded ��ע��JPA �������ṩ�����ƶϳ� EmploymentPeriod �Ǵ����� @Embeddable ��ע����Ƕ�ס�


//ʾ�� 1-17 @Embedded

@Entity
public class Employee17 implements Serializable {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "startDate", column = @Column(name = "EMP_START")),
        @AttributeOverride(name = "endDate", column = @Column(name = "EMP_END"))
            })
    public EmploymentPeriod getEmploymentPeriod() {
        return new EmploymentPeriod();
    }

}

