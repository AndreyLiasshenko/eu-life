package org.EuLife.custom_tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.util.Date;

public class CommentTagHandler extends TagSupport {
    private boolean islogined;

    Long questionId;
    Long answerId;
    Long commentId;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<h4 class=\"add_comment\" >Додати коментар</h4>");
            out.write("<hr class=\"answer_hr\">");
            out.write("<div class=\"new_comment\">");
            out.write("<h4 class=\"new_comment_title\">Ваш коментар</h4>");
            out.write("<form action=\"/new-comment?question_id=" +questionId+ "&answer_id=" +answerId+ "&comment_id=" +commentId+ "\" method=\"post\">");
            out.write("<label> <textarea  class=\"text_reply\" cols=\"30\" rows=\"5\" maxlength=\"2000\" name=\"text\" ></textarea> </label>");
            if (islogined) {
                out.write("<input style=\"display: block\" type=\"submit\" class=\"button\" value=\"Додати\">");
            } else {
                out.write("<input style=\"display: block\" type=\"submit\" class=\"disButton\" value=\"Додати\" disabled>");
            }
            out.write("</form>");
            out.write("</div>");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setIslogined(boolean islogined) {
        this.islogined = islogined;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
