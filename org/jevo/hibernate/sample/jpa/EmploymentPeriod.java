package org.jevo.hibernate.sample.jpa;

import javax.persistence.Embeddable;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:55:12
 * To change this template use File | Settings | File Templates.
 */
//@Embeddable
//Ĭ������£�JPA �������ṩ�������ÿ��ʵ����־ñ��浽���Լ������ݿ��
//ʹ�� @Embeddable ��עָ��һ���࣬�����ʵ���洢Ϊӵ��ʵ��Ĺ��в��ֲ������ʵ�����ݡ�Ƕ������ÿ���־����Ի��ֶζ���ӳ�䵽ʵ������ݿ��
//����עû������ ��

//ʾ�� 1-16 ��ʾ�����ʹ�ô���עָ������ EmploymentPeriod ��������עΪ @Embedded �ĳ־��ֶε�����ʱ����Ƕ�׵�ʵ���У������ ʾ�� 1-17��

//ʾ�� 1-16 @Embeddable


@Embeddable
public class EmploymentPeriod {
java.util.Date startDate;
java.util.Date endDate;

}


