package jp.dogrun.ileaflet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.router.RouterImpl;

public class AppRouter extends RouterImpl {


    public AppRouter() { 
        //addRouting("/_ah/{address}", "/_ah/{address}"); 
        addRouting(
            "/download/{contentId}", 
            "/download/?id={contentId}"); 
        addRouting(
            "/view/{contentId}", 
            "/view/?id={contentId}"); 
    }

    @Override
    public boolean isStatic(String path) throws NullPointerException {
        if ( isFilter(path,"/_ah/") ) return true;
        return super.isStatic(path);
    }

    @Override
    public String route(HttpServletRequest request, String path) {
        System.out.println("きとるよー");

        HttpSession session = request.getSession();
        if ( session != null ) {
            //Actor actor = (Actor)session.getAttribute(Actor.class.getName());
            //if ( actor != null ) {
                //request.setAttribute("userName", actor.getName());
            //}
        }
        
        if ( isFilter(path,"/dashboard") ) return path;
        if ( isFilter(path,"/info/") ) return path;
        if ( isFilter(path,"/login") ) return path;
        if ( isFilter(path,"/admin") ) return path;
        return super.route(request, path);
    } 
    
    private boolean isFilter(String path,String prefix) {
        int idx = path.indexOf(prefix);
        if ( idx == 0 ) return true;
        return false;
    }
    
}
