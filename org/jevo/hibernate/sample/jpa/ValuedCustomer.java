package org.jevo.hibernate.sample.jpa;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 10:51:13
 * To change this template use File | Settings | File Templates.
 */

// 示例 1-13 中的子类将它自己的 @DiscriminatorValue 指定为 VIP。在 Customer 和 ValuedCustomer 中， @DiscriminatorValue 的值必须可以转换为由 @DiscriminatorColumn 属性 discriminatorType 指定的类型，并且必须符合 @DiscriminatorColumn 属性 length。


//示例 1-13 @DiscriminatorValue — 子类

@Entity
@DiscriminatorValue(value="VIP")
public class ValuedCustomer extends Customer {
 
}


