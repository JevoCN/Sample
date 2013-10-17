package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:22:21
 * To change this template use File | Settings | File Templates.
 */
//@PrimaryKeyJoinColumns
//默认情况下，JPA 持续性提供程序假设每个实体有一个单列主键。

//如果要指定一个由两个或更多列组成的主键，请使用 @PrimaryKeyJoinColumns 批注。

//示例 1-76 显示了如何使用此批注指定一个由列 CUST_ID 和 CUST_TYPE 组成的复合主键。

//示例 1-76 @PrimaryKeyJoinColumns


@Entity
@Table(name="VCUST")
@DiscriminatorValue("VCUST")
@PrimaryKeyJoinColumns({
@PrimaryKeyJoinColumn(name="CUST_ID",referencedColumnName="ID"),
@PrimaryKeyJoinColumn(name="CUST_TYPE",referencedColumnName="TYPE")
})
public class ValuedCustomer76 extends Customer74 { 

}


