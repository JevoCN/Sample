package org.jevo.hibernate.sample.annotation;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-23
 * Time: 22:40:16
 * To change this template use File | Settings | File Templates.
 */


import java.util.HashSet;
import java.util.Set;

// 标准注解
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//增加的注解

import org.hibernate.annotations.GenericGenerator;

//当前的类是一个持久化类，是Category这个类。他映射了一个表category。所对应的 数据库是users

//这句：@Table(name = "category", catalog = "users")  可以省略
@Entity
@Table(name = "category", catalog = "users")

public class Category implements java.io.Serializable {

    private static final long serialVersionUID = 3240281547213597385L;
    private Integer id;
    private String name;
    private String description;
    private Set<Product> products = new HashSet<Product>(0);


    public Category() {
    }

    public Category(String name, String description, Set<Product> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    // 主键 ：@Id    主键生成方式：strategy = "increment"
    //映射表中id这个字段，不能为空，并且是唯一的
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //映射表中name这个字段 ，长度是500
    @Column(name = "name", length = 500)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //映射表中description这个字段 ，长度是500
    @Column(name = "description", length = 500)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //级联操作：cascade = CascadeType.ALL
    //延迟加载：fetch = FetchType.LAZY
    //映射：mappedBy = "category"
    //一对多方式
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}


