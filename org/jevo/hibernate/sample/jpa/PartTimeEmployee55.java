package org.jevo.hibernate.sample.jpa;

import javax.persistence.NamedNativeQuery;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:43:59
 * To change this template use File | Settings | File Templates.
 */
//@NamedNativeQueries
//�����Ҫָ����� @NamedNativeQuery�������ʹ��һ�� @NamedNativeQueries ��עָ������������ѯ��
//ʾ�� 1-6 ��ʾ�����ʹ�ô���עָ����������ԭ����ѯ��


@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "findAllPartTimeEmployees",
            query = "SELECT * FROM EMPLOYEE WHERE PRT_TIME=1"
    ),
    @NamedNativeQuery(
            name = "findAllSeasonalEmployees",
            query = "SELECT * FROM EMPLOYEE WHERE SEASON=1"
    )
        })
public class PartTimeEmployee55 extends Employee53 {

}

