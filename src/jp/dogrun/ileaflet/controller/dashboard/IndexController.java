package jp.dogrun.ileaflet.controller.dashboard;

import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        Actor actor = sessionScope(Actor.class.getName());
        //ログイン状態を取得
        if ( actor == null ) {
            return forward("../login/");
        }
        return forward("index.jsp");
    }
}
