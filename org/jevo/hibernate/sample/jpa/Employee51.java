package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:38:56
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Employee51 {
    @EmbeddedId
    public EmployeePK getEmpPK() {
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "proj_id")
    public Project getProject() {
        return null;
    }
}

