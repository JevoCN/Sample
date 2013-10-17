package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:26:37
 * To change this template use File | Settings | File Templates.
 */

//@EntityResult
//执行 @NamedNativeQuery 时，它可以返回实体（包括不同类型的实体）、标量值或实体和标量值的组合。

//使用 @EntityResult 批注返回实体。

//有关详细信息，另请参阅 @ColumnResult、 @FieldResult 和 @SqlResultSetMapping。



@SqlResultSetMapping(name = "OrderResults",
        entities = {@EntityResult(entityClass = Order.class,
                fields = {@FieldResult(name = "id", column = "order_id"),
                    @FieldResult(name = "quantity", column = "order_quantity"),
                    @FieldResult(name = "item", column = "order_item")
                        }),
            @EntityResult(entityClass = Item.class,
                    fields = {@FieldResult(name = "id", column = "item_id"),
                        @FieldResult(name = "name", column = "item_name")
                            })
                }
)
@Entity
public class Order24 {
    @Id
    protected int id;
    protected long quantity;
    protected Item item;

     public Object getObjects() {
        //示例 1-26 结合使用 @SqlResultSetMapping 与 @EntityResult 的原生查询
        EntityManagerFactory factory = null;// = new EntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
         Query q = entityManager.createNativeQuery("SELECT o.id AS order_id, " +
                 "o.quantity AS order_quantity, " + "o.item AS order_item, " + "i.id AS item_id, " +
                 "i.name AS item_name, " + "FROM Order o, Item i " + "WHERE (order_quantity > 25) AND (order_item = i.id)",
                 "OrderResults"
         );

         List resultList = q.getResultList();
        // List of Object arrays:{[Order, Item], [Order, Item], ...}
        return resultList;
    }
}

