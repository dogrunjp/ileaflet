package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class RegistrationController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        String code = requestScope("registerCode");
        ActorDao dao = new ActorDao();
        Actor actor = dao.findByKeyword(code);
        if ( actor == null ) {
            //TODO 存在しないメッセージ
            return forward("index.jsp");
        }
        if ( actor.getPurchase() != null ) {
            //TODO 既に登録済メッセージ
            return forward("index.jsp");
        }

        actor.setPurchase(0);
        Datastore.put(actor);

        //セッションに設定する
        sessionScope(Actor.class.getName(),actor);
        return redirect("/dashboard/");
    }
}
