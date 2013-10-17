package org.jevo.hibernate.sample.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceProperty;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 14:13:35
 * To change this template use File | Settings | File Templates.
 */
//@PersistenceProperty
//默认情况下，JPA 持续性提供程序假设您使用 @PersistenceContext 获取的实体管理器将使用默认属性。

//使用 @PersistenceProperty 批注指定属性（包括供应商特定的属性），以便容器或持续性提供程序：

//自定义实体管理器行为

//利用供应商的 JPA 持续性提供程序实现中的特定特性

//创建实体管理器时将属性传递给持续性提供程序。无法识别的属性被简单地忽略。

//示例 1-70 显示了如何使用 @PersistenceProperty 批注自定义查询以利用由 TopLink Essentials 提供的供应商 JPA 扩展：在该示例中，该属性确保在此持续性上下文中使用一个完整的 TopLink 缓存。有关详细信息，请参阅 “TopLink JPA Persistence.xml 文件扩展”。

//例 1-70 @PersistenceProperty



@Stateless
public class OrderEntryBean70 implements OrderEntry {
    @PersistenceContext(
            properties = {@PersistenceProperty={name = "toplink.cache.type.default", value = "CacheType.Full"}})
    EntityManager em;

    public void enterOrder(int custID, Order newOrder) {
        Customer cust = em.find(Customer.class, custID);
        cust.getOrders().add(newOrder);
        newOrder.setCustomer(cust);
    }
}

