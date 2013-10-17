package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:26:37
 * To change this template use File | Settings | File Templates.
 */

//@EntityResult
//ִ�� @NamedNativeQuery ʱ�������Է���ʵ�壨������ͬ���͵�ʵ�壩������ֵ��ʵ��ͱ���ֵ����ϡ�

//ʹ�� @EntityResult ��ע����ʵ�塣

//�й���ϸ��Ϣ��������� @ColumnResult�� @FieldResult �� @SqlResultSetMapping��



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
        //ʾ�� 1-26 ���ʹ�� @SqlResultSetMapping �� @EntityResult ��ԭ����ѯ
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

