package org.jevo.hibernate.sample.jpa;

import javax.persistence.Embeddable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:55:12
 * To change this template use File | Settings | File Templates.
 */
//@Embeddable
//默认情况下，JPA 持续性提供程序假设每个实体均持久保存到它自己的数据库表。
//使用 @Embeddable 批注指定一个类，该类的实例存储为拥有实体的固有部分并共享该实体的身份。嵌入对象的每个持久属性或字段都将映射到实体的数据库表。
//此批注没有属性 。

//示例 1-16 显示了如何使用此批注指定：类 EmploymentPeriod 在用作批注为 @Embedded 的持久字段的类型时可以嵌套到实体中（请参阅 示例 1-17）

//示例 1-16 @Embeddable


@Embeddable
public class EmploymentPeriod {
java.util.Date startDate;
java.util.Date endDate;

}


