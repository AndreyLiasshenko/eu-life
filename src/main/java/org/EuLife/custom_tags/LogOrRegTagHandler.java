package org.EuLife.custom_tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;


public class LogOrRegTagHandler extends TagSupport {
    private boolean isLogined=false;
    private String username;
    private String imagepath;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            if (isLogined) {
                out.println("<ul id=\"ul1\">");
                    out.println("<li><a class=\"invisible_link\" href=\"/\"><img src=\"img/logo.png\" width=\"90\" height=\"90\" alt=\"img\"></a></li>");
                    out.println("<li><div><a class=\"button newPostButton\" href=\"/new-question\">Задати запитання</a></div></li>");
                out.println("</ul>");

                out.println("<ul id=\"ul2\">");

                    out.println("<div>");
                        out.println("<h4 style=\"color: #dee8e8\">");
                            out.println("<h4 id=\"user_name\">");
                                out.println("<a href=\"/user\">"+ username +"</a>");
                            out.println("</h4>");
                        out.println("</h4>");
                        out.println("<img src=\""+ imagepath +"\" width=\"40\" height=\"40\" alt=\"user\" id=\"user_img\">");
                    out.println("</div>");

                    out.println("<li style=\"margin-top: 5px\">");
                    out.println("<a class=\"button\" style=\"padding: 3px;\" href=\"/log-out\">Вихід</a>");
                    out.println("</li>");

                out.println("</ul>");


            } else {
                out.println("<ul id=\"ul1\">");
                out.println("<li><a class=\"invisible_link\" href=\"/\"><img src=\"img/logo.png\" width=\"90\" height=\"90\" alt=\"img\"></a></li>");
                out.println("<li><div><a class=\"button newPostButton\" href=\"/new-question\">Задати запитання</a></div></li>");
                out.println("</ul>");

                out.println("<ul id=\"ul2\">");
                out.println("<li><a class=\"button logBtn\" href=\"/authorization\">Вхід</a></li>");
                out.println("<li><a class=\"button logBtn\" href=\"/registration\">Реєстрація</a></li>");
                out.println("</ul>");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }
}
