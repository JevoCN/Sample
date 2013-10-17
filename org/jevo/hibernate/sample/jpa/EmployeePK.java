package org.jevo.hibernate.sample.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 11:00:38
 * To change this template use File | Settings | File Templates.
 */
//@EmbeddedId

//使用 @EmbeddedId 批注指定一个由实体拥有的可嵌入复合主键类（通常由两个或更多基元类型或 JDK 对象类型组成）。从原有数据库映射时（此时数据库键由多列组成），通常将出现复合主键。

//复合主键类具有下列特征：

//它是一个普通的旧式 Java 对象 (POJO) 类。

//它必须为 public，并且必须有一个 public 无参数构造函数。

//如果使用基于属性的访问，则主键类的属性必须为 public 或 protected。

//它必须是可序列化的。

//它必须定义 equals 和 hashCode 方法。

//这些方法的值相等性的语义必须与键映射到的数据库类型的数据库相等性一致。

//或者，您可以使复合主键类成为非嵌入类（请参阅 @IdClass）。

//此批注没有属性 。有关更多详细信息，请参阅 API。

//示例 1-18 显示了一个批注为 @Embeddable 的典型复合主键类。 示例1-19 显示了如何使用可嵌入的复合主键类（使用 @EmbeddedId 批注）配置一个实体。

//示例 1-18 可嵌入复合主键类



@Embeddable
public class EmployeePK implements Serializable {
    private String name;
    private long id;

    public EmployeePK() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int hashCode() {
        return (int) (name.hashCode() + id);
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof EmployeePK)) return false;
        if (obj == null) return false;
        EmployeePK pk = (EmployeePK) obj;
        return pk.id == id && pk.name.equals(name);
    }
}

