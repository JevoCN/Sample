package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:19:21
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="CUST")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorValue("CUST")
public class Customer74 {

}

