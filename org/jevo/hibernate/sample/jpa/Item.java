package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:40:35
 * To change this template use File | Settings | File Templates.
 */
//示例 1-10 Item 实体


@Entity
public class Item {
    @Id
    protected int id;
    protected String name;

}


