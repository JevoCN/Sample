package org.jevo.enusample;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-3
 * Time: 23:17:58
 * To change this template use File | Settings | File Templates.
 */


public class EnumClassTest {
    enum OrderStatusEnum {
        CREATE, PENDING, PICK, PACK, SHIPPING, COMPLETED,
    };

    public static void main(String[] args) {
//        for (OrderStatusEnum s1 : OrderStatusEnum.values()) {
//            System.err.println("s1 == " + s1.name()+", value = "+ s1.ordinal());
//        }

//        for (OrderStatusEnum s : OrderStatusEnum.values()) {
//            System.err.println(s + " ordinal: " + s.ordinal());
//            System.err.println(s.compareTo(OrderStatusEnum.CREATE) + " ");
//            System.err.println(s.equals(OrderStatusEnum.CREATE) + " ");
//            System.err.println(s == OrderStatusEnum.CREATE);
//            System.err.println(s.getDeclaringClass());
//            System.err.println(s.name());
//            OrderStatusEnum[] enus = s.getClass().getEnumConstants();
//            for (OrderStatusEnum s1 : enus) {
//               System.err.println("enus == " + s1.name()+"");
//            }
//            System.err.println("----------------------");
//        }
        // Produce an enum value from a string name:
        for (String s : "CREATE ORDER".split(" ")) {
            System.err.println("String is " + s);
            if(s.compareTo(OrderStatusEnum.CREATE.toString()) == 0) {
                OrderStatusEnum status = Enum.valueOf(OrderStatusEnum.class, s);
                System.err.println("s1 == " + status.name()+", value = "+ status.ordinal() + ", " + status.toString());
                System.err.println("Order is " + status);
            }
        }
    }
}
