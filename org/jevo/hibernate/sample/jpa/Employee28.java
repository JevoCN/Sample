package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:34:02
 * To change this template use File | Settings | File Templates.
 */

//@Enumerated
//默认情况下，JPA 持续性提供程序持久保存枚举常量的序数值。

//使用 @Enumerated 批注指定在 String 值适合应用程序要求或与现有数据库模式匹配的情况下，JPA 持续性提供程序是否应持久保存枚举常量的序数值或 String 值。

//该批注可以与 @Basic 一起使用。

//根据 示例 1-27 中的枚举常量， 示例 1-28 显示了如何使用此批注指定在持久保存 Employee 时应将 SalaryRate 的 String 值写入数据库。默认情况下，会将 EmployeeStatus 的序数值写入数据库。

//示例 1-27 枚举常量

enum EmployeeStatus {FULL_TIME, PART_TIME, CONTRACT}
enum SalaryRate {JUNIOR, SENIOR, MANAGER, EXECUTIVE}

//示例 1-28 @Enumerated

@Entity
public class Employee28 {
    public EmployeeStatus getStatus() {
        return EmployeeStatus.FULL_TIME;
    }

    @Enumerated(EnumType.STRING)
    public SalaryRate getPayScale() {
        return SalaryRate.JUNIOR;
    }
    
}


