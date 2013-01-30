package jp.dogrun.ileaflet.controller.dashboard;

import java.util.List;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        Actor actor = sessionScope(Actor.class.getName());
        //ログイン状態を取得
        if ( actor == null ) {
            return redirect("../login/");
        }
       
        ContentDao dao = new ContentDao();
        List<Content> contentList =dao.findByIdentity(actor.getIdentity());

        requestScope("contentList",contentList);

        return forward("index.jsp");
    }
}
