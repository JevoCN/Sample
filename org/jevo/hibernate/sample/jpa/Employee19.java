package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:02:12
 * To change this template use File | Settings | File Templates.
 */

//示例 1-19 @EmbeddedId

@Entity
public class Employee19 implements Serializable {
    EmployeePK primaryKey;

    public Employee19() {
    }

    @EmbeddedId
    public EmployeePK getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(EmployeePK pk) {
        primaryKey = pk;
    }

}


