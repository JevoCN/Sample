package org.jevo.hibernate.sample.jpa;

import javax.persistence.NamedNativeQuery;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-24
 * Time: 13:43:59
 * To change this template use File | Settings | File Templates.
 */
//@NamedNativeQueries
//如果需要指定多个 @NamedNativeQuery，则必须使用一个 @NamedNativeQueries 批注指定所有命名查询。
//示例 1-6 显示了如何使用此批注指定两个命名原生查询。


@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "findAllPartTimeEmployees",
            query = "SELECT * FROM EMPLOYEE WHERE PRT_TIME=1"
    ),
    @NamedNativeQuery(
            name = "findAllSeasonalEmployees",
            query = "SELECT * FROM EMPLOYEE WHERE SEASON=1"
    )
        })
public class PartTimeEmployee55 extends Employee53 {

}

