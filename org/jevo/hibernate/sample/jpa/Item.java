package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:40:35
 * To change this template use File | Settings | File Templates.
 */
//ʾ�� 1-10 Item ʵ��


@Entity
public class Item {
    @Id
    protected int id;
    protected String name;

}


