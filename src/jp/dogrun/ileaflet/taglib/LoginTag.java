package jp.dogrun.ileaflet.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slim3.util.RequestLocator;

import jp.dogrun.ileaflet.model.Actor;

public class LoginTag extends TagSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
    
        Actor actor = null;
        
        HttpServletRequest request = RequestLocator.get();
        actor = (Actor)request.getSession().getAttribute(Actor.class.getName());

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
