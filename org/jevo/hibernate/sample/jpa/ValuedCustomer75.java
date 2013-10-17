package org.jevo.hibernate.sample.jpa;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:20:32
 * To change this template use File | Settings | File Templates.
 */
//@PrimaryKeyJoinColumn
//默认情况下，当一个实体使用 InheritanceType.JOINED（请参阅 @Inheritance）扩展另一个实体时，JPA 持续性提供程序假设子类的外键列与超类主表的主键列同名。

//使用 @PrimaryKeyJoinColumn 批注：

//如果子类的外键列与该情形中超类的主表的主键列不同名

//使用 @SecondaryTable 批注将辅助表连接到主表

//在 @OneToOne 映射中，引用实体的主键用作被引用实体的外键。

//使用复合外键（请参阅 @PrimaryKeyJoinColumns）

//示例 1-74 显示了一个实体基类 Customer， 示例 1-75 显示了如何使用 @PrimaryKeyJoinColumn 在 ValuedCustomer（ Customer 的一个子类）的主表中指定主键连接列 CUST_ID。

//示例 1-74 @PrimaryKeyJoinColumn - InheritanceType.JOINED 超类


//示例 1-75 @PrimaryKeyJoinColumn - InheritanceType.JOINED 子类

@Entity
@Table(name="VCUST")
@DiscriminatorValue("VCUST")
@PrimaryKeyJoinColumn(name="CUST_ID")
public class ValuedCustomer75 extends Customer74 {
     
}


