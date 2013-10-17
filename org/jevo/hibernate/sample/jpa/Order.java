package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:37:23
 * To change this template use File | Settings | File Templates.
 */
//@ColumnResult
//执行 @NamedNativeQuery 时，它可以返回实体（包括不同类型的实体）、标量值或实体和标量值的组合。

//使用 @ColumnResult 批注返回标量值。标量类型由您在 @ColumnResult 中标识的列类型确定。

//有关详细信息，另请参阅 @EntityResult、 @FieldResult 和 @SqlResultSetMapping。

//示例 1-9 显示了如何使用此批注将 Item（请参阅 示例 1-10）标量 name 包含在结果列表（请参阅 示例 1-11）中。在该示例中，结果列表将为 Object 数组的 List，如： {[Order, "Shoes"], [Order, "Socks"], ...}。
//示例 1-9 使用 @ColumnResult 的 Order 实体

@SqlResultSetMapping(name = "OrderResults",
        entities = {@EntityResult(entityClass = Order.class,
                fields = {@FieldResult(name = "id", column = "order_id"),
                    @FieldResult(name = "quantity", column = "order_quantity"),
                    @FieldResult(name = "item", column = "order_item")
                        })
                },
        columns = {@ColumnResult(name = "item_name")}
)
@Entity
public class Order {
    @Id
    protected int id;
    protected long quantity;
    protected Item item;


    public Object getObjects() {
        //示例 1-11 结合使用 @SqlResultSetMapping 与 @ColumnResult 的原生查询
        EntityManagerFactory factory = null;// = new EntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        Query q = entityManager.createNativeQuery("SELECT o.id AS order_id, " +
                "o.quantity AS order_quantity, " + "o.item AS order_item, " + "i.name     AS item_name, " +
                "FROM Order o, Item i " + "WHERE (order_quantity > 25) AND (order_item = i.id)", "OrderResults");

        List resultList = q.getResultList();
// List of Object arrays:{[Order, "Shoes"], [Order, "Socks"], ...}
        return resultList;
    }


    //示例 1-62 @ManyToOne - 使用一般参数的 Order 类
    Customer customer;
    @ManyToOne
    @JoinColumn(name = "CUST_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}

//@SqlResultSetMapping
//执行 @NamedNativeQuery 时，它可以返回实体（包括不同类型的实体）、标量值或实体和标量值的组合。

//默认情况下（如 示例 1-81 所示），JPA 持续性提供程序假设原生 SQL 查询中的 SELECT 语句：

//返回一个实体类型

//包含与返回的实体的所有字段或属性相对应的所有列

//使用与字段或属性名（未使用 AS 语句）相对应的列名

//示例 1-81 简单的原生 SQL 查询

//Query q = entityManager.createNativeQuery(
//"SELECT o.id, o.quantity, o.item " +
//"FROM Order o, Item i " +
//"WHERE (o.item = i.id) AND (i.name = "widget")",
//Order.class
//);
//List resultList = q.getResultList();
// List of Order entity objects:{Order, Order, ...}

//如果原生 SQL 查询满足以下条件，请使用 @SqlResultSetMapping 批注控制 JPA 持续性提供程序如何将 JDBC 结果集映射到实体字段或属性以及标量：

//返回多个类型的实体

//只返回标量值或实体和标量值的组合

//使用列别名（ AS 语句）

//如果有多个 @SqlResultSetMapping，则必须使用 @SqlResultSetMappings。

/*
示例 1-82 显示了如何使用此批注将 Order 和 Item（请参阅 示例 1-83）实体和标量 name 包含在结果列表（请参阅 示例 1-84）中。在该示例中，结果列表将为 Object 数组的 List，如： {[Order, Item, "Shoes"], [Order, Item, "Socks"], ...}。

示例 1-82 使用 @SqlResultSetMapping 的 Order 实体

@SqlResultSetMapping(
name="OrderResults",
entities={
@EntityResult(
entityClass=Order.class,
fields={
@FieldResult(name="id",       column="order_id"),
@FieldResult(name="quantity", column="order_quantity"),
@FieldResult(name="item",     column="order_item")
            }
        ),
@EntityResult(
entityClass=Item.class,
fields={
@FieldResult(name="id",       column="item_id"),
@FieldResult(name="name",     column="item_name"),
            }
        )
    }
columns={
@ColumnResult(
name="item_name"
        )
    }
)
@Entity
public class Order {
@Id
protected int id;
protected long quantity;
protected Item item;
    ...
}
示例 1-83 Item 实体

@Entity
public class Item {
@Id
protected int id;
protected String name;
    ...
}
示例 1-84 将 @SqlResultSetMapping 与 @EntityResult 一起使用的原生查询

Query q = entityManager.createNativeQuery(
"SELECT o.id       AS order_id, " +
"o.quantity AS order_quantity, " +
"o.item     AS order_item, " +
"i.id       AS item_id, " +
"i.name     AS item_name, " +
"FROM Order o, Item i " +
"WHERE (order_quantity > 25) AND (order_item = i.id)",
"OrderResults"
);

List resultList = q.getResultList();
// List of Object arrays:{[Order, Item, "Shoes"], [Order, Item, "Socks"], ...}
*/



//@SqlResultSetMappings
//如果需要指定多个 @SqlResultSetMapping，则必须使用一个 @SqlResultSetMappings 批注指定所有 SQL 结果集映射。

/*
示例 1-85 显示了如何使用此批注指定两个 @SqlResultSetMapping 实例。

示例 1-85 @SqlResultSetMappings

SqlResultSetMappings({
@SqlResultSetMapping(
name="OrderItemItemNameResults",
entities={
@EntityResult(entityClass=Order.class),
@EntityResult(entityClass=Item.class)
        }
columns={
@ColumnResult(name="item_name")
        }
    ),
@SqlResultSetMapping(
name="OrderItemResults",
entities={
@EntityResult(entityClass=Order.class),
@EntityResult(entityClass=Item.class)
        }
    )
})
@Entity
public class Order {
@Id
protected int id;
protected long quantity;
protected Item item;
    ...
}

 */
