package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Actor actor = (Actor)request.getSession().getAttribute(Actor.class.getName());
        //ログイン状態を取得
        if ( actor != null ) {
            return forward("../dashboard/");
        }
        //ログインページに移動
        return forward("index.jsp");
    }
}
