package jp.dogrun.ileaflet.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import jp.dogrun.ileaflet.model.Actor;

public class LoginTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
    
        Actor actor = null;
        HttpSession session = pageContext.getSession();
        if ( session != null ) {
            actor = (Actor)session.getAttribute(Actor.class.getName());
        }

        try {
            JspWriter out = pageContext.getOut();
            if ( actor == null ) {
                //TODO リダイレクト用のパスを設定
                String path = "";
                out.print("<a href='/login/?redirect=" + path +"'>ログイン</a>");
            } else {
                out.print("<a href='/dashboard/'>" + actor.getName() + "</a>");
        	}
        } catch (IOException e) {
            throw new RuntimeException("Bodyの書き込み失敗",e);
        }
        
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
    
    
}
