package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
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

