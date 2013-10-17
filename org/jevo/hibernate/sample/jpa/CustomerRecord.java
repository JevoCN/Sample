package org.jevo.hibernate.sample.jpa;

import javax.persistence.OneToOne;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:56:20
 * To change this template use File | Settings | File Templates.
 */
//示例 1-64 @OneToOne - CustomerRecord 类
@Entity
public class CustomerRecord implements Serializable {
    Customer customer;

    @OneToOne(optional = false, mappedBy = "customerRecord")
    public Customer getCustomer() {
        return customer;
    }

}

