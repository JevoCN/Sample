package org.jevo.hibernate.sample.jpa;

import javax.persistence.PrePersist;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:18:25
 * To change this template use File | Settings | File Templates.
 */

//示例 1-22 EmployeePersistListener

public class EmployeePersistListener {
    @PrePersist
    public void employeePrePersist(Object employee) {

    }

}

