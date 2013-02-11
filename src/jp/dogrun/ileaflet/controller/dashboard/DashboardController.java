package jp.dogrun.ileaflet.controller.dashboard;

import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public abstract class DashboardController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        Actor actor = getActor();
        //ログイン状態を取得
        if ( actor == null ) {
            return redirect("../login/");
        }
        return process();
    }

    protected abstract Navigation process() throws Exception;

    protected Actor getActor() {
        return sessionScope(Actor.class.getName());
    }

}
