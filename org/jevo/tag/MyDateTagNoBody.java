package org.jevo.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Huang Jianhua 版权所有 自2012,并保留所有权利。
 * User: utstar
 * Date: 2013-4-1
 * Time: 22:35:57
 * To change this template use File | Settings | File Templates.
 */

public class MyDateTagNoBody extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        HttpServletRequest request;

        // 是TagSupport类中定义的一个属性，它是javax.servlet.jsp.PageContext的对象

        request = (HttpServletRequest) pageContext.getRequest();

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String date = formater.format(new Date());

        JspWriter out = pageContext.getOut();

        try {

            out.print(date);

        } catch (IOException e) {

            e.printStackTrace();

        }

        // doStartTag() 方法返回 SKIP_BODY 。当然其原因是我们的简单日期标记没有正文。

        return Tag.SKIP_BODY;

    }

}


