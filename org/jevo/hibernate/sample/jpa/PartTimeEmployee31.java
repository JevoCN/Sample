package org.jevo.hibernate.sample.jpa;

import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:39:05
 * To change this template use File | Settings | File Templates.
 */

//示例 1-31 子类级别的 @ExcludeSuperclassListeners

@Entity
@ExcludeSuperclassListeners
@EntityListeners(value={EmployeePersistListener.class, EmployeeRemoveListener.class})
public class PartTimeEmployee31 extends Employee30 {

}

