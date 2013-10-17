package org.jevo.hotswap.sample.exam3;

/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-17
 * Time: 11:31:53
 * To change this template use File | Settings | File Templates.
 */
//start方法用于初始化，close用于清楚此服务。doBusiness用来模拟处理业务
public interface IDyService
{
   public void start();
   public void close();
   public void doBusiness();
}


