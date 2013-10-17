package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:29:04
 * To change this template use File | Settings | File Templates.
 */
//@JoinTable
//默认情况下，JPA 持续性提供程序在映射多对多关联（或在单向的一对多关联中）的拥有方上的实体关联时使用一个连接表。连接表名称及其列名均在默认情况下指定，且 JPA 持续性提供程序假设：在关系的拥有方上的实体主表中，每个主键列有一个连接列。

//如果您需要执行以下操作，请使用 @JoinTable 批注：

//由于默认名称难于处理、是一个保留字、与预先存在的数据模型不兼容或作为数据库中的表名无效而更改连接表的名称

//由于默认名称难于处理、是一个保留字、与预先存在的数据模型不兼容或作为数据库中的列名无效而更改连接表的列名称

//使用特定目录或模式配置连接表

//使用唯一约束配置一个或多个连接表列

//每个实体使用多个连接列

//示例 1-45 显示了如何使用此批注为 Employee 与 Project 之间实体的多对多关系指定一个名为 EMP_PROJ_EMP 的连接表。连接表中有两列： EMP_ID 和 PROJ_ID。 EMP_ID 列包含其主键列（被引用列）名为 ID 的 Employee 表中的主键值。 PROJ_ID 列包含其主键列（被引用列）也名为 ID 的 Project 表中的主键值。

//示例 1-45 @JoinTable



//示例 1-45 @JoinTable

@Entity
public class Employee45 implements Serializable {

    Collection projects;
    
    @ManyToMany
    @JoinTable(
            name = "EJB_PROJ_EMP",
            joinColumns = @JoinColumn(name = "EMP_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJ_ID", referencedColumnName = "ID")
    )
    public Collection getProjects() {
        return projects;
    }

}

