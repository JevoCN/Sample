package org.jevo.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Huang Jianhua ��Ȩ���� ��2012,����������Ȩ����
 * User: utstar
 * Date: 2013-4-1
 * Time: 22:35:57
 * To change this template use File | Settings | File Templates.
 */

public class MyDateTagNoBody extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        HttpServletRequest request;

        // ��TagSupport���ж����һ�����ԣ�����javax.servlet.jsp.PageContext�Ķ���

        request = (HttpServletRequest) pageContext.getRequest();

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String date = formater.format(new Date());

        JspWriter out = pageContext.getOut();

        try {

            out.print(date);

        } catch (IOException e) {

            e.printStackTrace();

        }

        // doStartTag() �������� SKIP_BODY ����Ȼ��ԭ�������ǵļ����ڱ��û�����ġ�

        return Tag.SKIP_BODY;

    }

}


