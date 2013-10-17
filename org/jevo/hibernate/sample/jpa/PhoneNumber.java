package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:33:33
 * To change this template use File | Settings | File Templates.
 */
//示例 1-48 @ManyToMany — 使用一般参数的 PhoneNumber 类


@Entity
public class PhoneNumber implements Serializable {
    Set<Customer47> customers;

    @ManyToMany(mappedBy = "phones")
    public Set<Customer47> getCustomers() {
        return customers;
    }

}

