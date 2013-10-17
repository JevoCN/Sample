package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:37:23
 * To change this template use File | Settings | File Templates.
 */
//@ColumnResult
//ִ�� @NamedNativeQuery ʱ�������Է���ʵ�壨������ͬ���͵�ʵ�壩������ֵ��ʵ��ͱ���ֵ����ϡ�

//ʹ�� @ColumnResult ��ע���ر���ֵ���������������� @ColumnResult �б�ʶ��������ȷ����

//�й���ϸ��Ϣ��������� @EntityResult�� @FieldResult �� @SqlResultSetMapping��

//ʾ�� 1-9 ��ʾ�����ʹ�ô���ע�� Item������� ʾ�� 1-10������ name �����ڽ���б������ ʾ�� 1-11���С��ڸ�ʾ���У�����б�Ϊ Object ����� List���磺 {[Order, "Shoes"], [Order, "Socks"], ...}��
//ʾ�� 1-9 ʹ�� @ColumnResult �� Order ʵ��

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
        //ʾ�� 1-11 ���ʹ�� @SqlResultSetMapping �� @ColumnResult ��ԭ����ѯ
        EntityManagerFactory factory = null;// = new EntityManagerFactory();
        EntityManager entityManager = factory.createEntityManager();
        Query q = entityManager.createNativeQuery("SELECT o.id AS order_id, " +
                "o.quantity AS order_quantity, " + "o.item AS order_item, " + "i.name     AS item_name, " +
                "FROM Order o, Item i " + "WHERE (order_quantity > 25) AND (order_item = i.id)", "OrderResults");

        List resultList = q.getResultList();
// List of Object arrays:{[Order, "Shoes"], [Order, "Socks"], ...}
        return resultList;
    }


    //ʾ�� 1-62 @ManyToOne - ʹ��һ������� Order ��
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
//ִ�� @NamedNativeQuery ʱ�������Է���ʵ�壨������ͬ���͵�ʵ�壩������ֵ��ʵ��ͱ���ֵ����ϡ�

//Ĭ������£��� ʾ�� 1-81 ��ʾ����JPA �������ṩ�������ԭ�� SQL ��ѯ�е� SELECT ��䣺

//����һ��ʵ������

//�����뷵�ص�ʵ��������ֶλ��������Ӧ��������

//ʹ�����ֶλ���������δʹ�� AS ��䣩���Ӧ������

//ʾ�� 1-81 �򵥵�ԭ�� SQL ��ѯ

//Query q = entityManager.createNativeQuery(
//"SELECT o.id, o.quantity, o.item " +
//"FROM Order o, Item i " +
//"WHERE (o.item = i.id) AND (i.name = "widget")",
//Order.class
//);
//List resultList = q.getResultList();
// List of Order entity objects:{Order, Order, ...}

//���ԭ�� SQL ��ѯ����������������ʹ�� @SqlResultSetMapping ��ע���� JPA �������ṩ������ν� JDBC �����ӳ�䵽ʵ���ֶλ������Լ�������

//���ض�����͵�ʵ��

//ֻ���ر���ֵ��ʵ��ͱ���ֵ�����

//ʹ���б����� AS ��䣩

//����ж�� @SqlResultSetMapping�������ʹ�� @SqlResultSetMappings��

/*
ʾ�� 1-82 ��ʾ�����ʹ�ô���ע�� Order �� Item������� ʾ�� 1-83��ʵ��ͱ��� name �����ڽ���б������ ʾ�� 1-84���С��ڸ�ʾ���У�����б�Ϊ Object ����� List���磺 {[Order, Item, "Shoes"], [Order, Item, "Socks"], ...}��

ʾ�� 1-82 ʹ�� @SqlResultSetMapping �� Order ʵ��

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
ʾ�� 1-83 Item ʵ��

@Entity
public class Item {
@Id
protected int id;
protected String name;
    ...
}
ʾ�� 1-84 �� @SqlResultSetMapping �� @EntityResult һ��ʹ�õ�ԭ����ѯ

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
//�����Ҫָ����� @SqlResultSetMapping�������ʹ��һ�� @SqlResultSetMappings ��עָ������ SQL �����ӳ�䡣

/*
ʾ�� 1-85 ��ʾ�����ʹ�ô���עָ������ @SqlResultSetMapping ʵ����

ʾ�� 1-85 @SqlResultSetMappings

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
