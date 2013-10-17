package org.jevo.hibernate.sample.jpa;

import javax.persistence.PostRemove;
import javax.persistence.PreRemove;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:19:36
 * To change this template use File | Settings | File Templates.
 */

//示例 1-23 EmployeeRemoveListener

public class EmployeeRemoveListener {
    @PreRemove
    @PostRemove
    public void employeePreRemove(Object employee) {

    }

}

