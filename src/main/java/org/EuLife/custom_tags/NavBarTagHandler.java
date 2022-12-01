package org.EuLife.custom_tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class NavBarTagHandler extends TagSupport {


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.println("<ul>");
            out.println("<li>Java</li>");
            out.println("<li>SQL</li>");
            out.println("<li>Hibernate</li>");
            out.println("<li>Spring</li>");
            out.println("<li>Інформація</li>");
            out.println("</ul>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }
}
