package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:40:34
 * To change this template use File | Settings | File Templates.
 */
//@FieldResult
//ִ�� @NamedNativeQuery ʱ�������Է���ʵ�壨������ͬ���͵�ʵ�壩������ֵ��ʵ��ͱ���ֵ����ϡ�

//Ĭ������£�JPA �������ṩ���������ʹ�� @EntityResult ����ʵ��ʱ�� SELECT ��佫�����뷵�ص�ʵ��������ֶλ��������Ӧ�������У��� SELECT ����е�������Ӧ���ֶλ���������δʹ�� AS ��䣩��

//��� SELECT ���ֻ����ĳЩ�뷵�ص�ʵ����ֶλ��������Ӧ���У��� SELECT ����е�����������Ӧ���ֶλ���������ʹ���� AS ��䣩������ʹ�� @EntityResult ����ʵ��ʱ����ʹ�� @FieldResult ��ע�� SELECT ����е���ӳ�䵽�ֶλ����ԡ�

//�й���ϸ��Ϣ��������� @ColumnResult �� @SqlResultSetMapping��

//ʾ�� 1-32 ��ʾ�����ʹ�ô���ע�� Order �� Item������� ʾ�� 1-33��ʵ������ڽ���б������ ʾ�� 1-34���С��ڸ�ʾ���У�����б�Ϊ Object ����� List���磺 {[Order, Item], [Order, Item], ...}��

//ʾ�� 1-32 ʹ�� @EntityResult �� @FieldResult �� Order ʵ��



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
        //ʾ�� 1-34 ���ʹ�� @SqlResultSetMapping �� @EntityResult ��ԭ����ѯ
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

