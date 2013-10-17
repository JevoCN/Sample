package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:40:34
 * To change this template use File | Settings | File Templates.
 */
//@FieldResult
//执行 @NamedNativeQuery 时，它可以返回实体（包括不同类型的实体）、标量值或实体和标量值的组合。

//默认情况下，JPA 持续性提供程序假设在使用 @EntityResult 返回实体时， SELECT 语句将包含与返回的实体的所有字段或属性相对应的所有列，且 SELECT 语句中的列名对应于字段或属性名（未使用 AS 语句）。

//如果 SELECT 语句只包含某些与返回的实体的字段或属性相对应的列，或 SELECT 语句中的列名并不对应于字段或属性名（使用了 AS 语句），则在使用 @EntityResult 返回实体时，请使用 @FieldResult 批注将 SELECT 语句中的列映射到字段或属性。

//有关详细信息，另请参阅 @ColumnResult 和 @SqlResultSetMapping。

//示例 1-32 显示了如何使用此批注将 Order 和 Item（请参阅 示例 1-33）实体包含在结果列表（请参阅 示例 1-34）中。在该示例中，结果列表将为 Object 数组的 List，如： {[Order, Item], [Order, Item], ...}。

//示例 1-32 使用 @EntityResult 和 @FieldResult 的 Order 实体



@SqlResultSetMapping(
        name = "OrderResults",
        entities = {
            @EntityResult(
                    entityClass = Order.class,
                    fields = {
                        @FieldResult(name = "id", column = "order_id"),
                        @FieldResult(name = "quantity", column = "order_quantity"),
                        @FieldResult(name = "item", column = "order_item")
                            }
            ),
            @EntityResult(
                    entityClass = Item.class,
                    fields = {
                        @FieldResult(name = "id", column = "item_id"),
                        @FieldResult(name = "name", column = "item_name")
                            }
            )
                }
)
@Entity
public class Order32 {
    @Id
    protected int id;
    protected long quantity;
    protected Item item;


    public Object getObjects() {
        //示例 1-34 结合使用 @SqlResultSetMapping 与 @EntityResult 的原生查询
        EntityManagerFactory factory = null;// = new EntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        Query q = entityManager.createNativeQuery("SELECT o.id AS order_id, " + "o.quantity AS order_quantity, " +
            "o.item AS order_item, " + "i.id AS item_id, " + "i.name AS item_name, " +
            "FROM Order o, Item i " + "WHERE (order_quantity > 25) AND (order_item = i.id)", "OrderResults"
        );

        List resultList = q.getResultList();
        // List of Object arrays:{[Order, Item], [Order, Item], ...}
        return resultList;
    }
}

