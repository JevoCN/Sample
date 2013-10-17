package org.jevo.hibernate.sample.jpa;

import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:39:05
 * To change this template use File | Settings | File Templates.
 */

//ʾ�� 1-31 ���༶��� @ExcludeSuperclassListeners

@Entity
@ExcludeSuperclassListeners
@EntityListeners(value={EmployeePersistListener.class, EmployeeRemoveListener.class})
public class PartTimeEmployee31 extends Employee30 {

}

