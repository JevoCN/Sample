package org.jevo.hibernate.sample.jpa;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:30:55
 * To change this template use File | Settings | File Templates.
 */
//@Lob
//默认情况下，JPA 持续性提供程序假设所有持久数据均可以表示为典型的数据库数据类型。

//结合使用 @Lob 批注与 @Basic 映射，以指定持久属性或字段应作为大型对象持久保存到数据库支持的大型对象类型。

//Lob 可以是二进制类型或字符类型。持续性提供程序从持久字段或属性的类型推断出 Lob 类型。

//对于基于字符串和字符的类型，默认值为 Clob。在所有其他情况下，默认值为 Blob。

//还可以使用 @Column 属性 columnDefinition 进一步改进 Lob 类型。

//此批注没有属性 。有关更多详细信息，请参阅 API。

//示例 1-46 显示了如何使用此批注指定持久字段 pic 应作为 Blob 进行持久保存。

//示例 1-46 @Lob

@Entity
public class Employee46 implements Serializable {
    @Lob
//    @Basic(fetch = FetchType.LAZY)
    @Basic(optional=false)
    @Column(name = "EMP_PIC", columnDefinition = "BLOB NOT NULL")
    protected byte[] pic;

}

