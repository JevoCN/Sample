package org.jevo.hibernate.sample.jpa;

import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.mail.Address;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:27:20
 * To change this template use File | Settings | File Templates.
 */

//@JoinColumns
//默认情况下，在实体关联中，JPA 持续性提供程序假设使用一个连接列。

//如果要指定两个或更多连接列（即复合主键），请使用 @JoinColumns 批注。

//示例 1-44 显示了如何使用此批注指定两个连接列的名称： Employee 表中的 ADDR_ID（其中包含 Address 表列 ID 中的外键值）以及 Employee 表中的 ADDR_ZIP（其中包含 Address 表列 ZIP 中的外键值）。

//示例 1-44 @JoinColumns

@Entity
public class Employee44 implements Serializable {
    protected Address address;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ADDR_ID", referencedColumnName = "ID"),
        @JoinColumn(name = "ADDR_ZIP", referencedColumnName = "ZIP")
            })
    public Address getAddress() {
        return address;
    }

}

//@JoinColumn
//默认情况下，在实体关联中，JPA 持续性提供程序使用一个基于现有名称（如字段或属性名称）的数据库模式，以便它可以自动确定要使用的单个连接列（包含外键的列）。

//在以下条件下使用 @JoinColumn 批注：

//默认连接列名称难于处理、是一个保留字、与预先存在的数据模型不兼容或作为数据库中的列名无效

//您需要使用外部表中的列（非主键列）进行连接

//您想要使用两个或更多连接列（请参阅 @JoinColumns）

//您想要使用一个连接表（请参阅 @JoinTable）
/*
示例 1-43 显示了如何使用此批注使 JPA 将数据库表 Employee 列 ADDR_ID 用作连接列。

示例 1-43 @JoinColumn

@Entity
public class Employee implements Serializable {
    ...
@ManyToOne
@JoinColumn(name="ADDR_ID")
public Address getAddress() {
return address;
    }
}
*/