package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogonController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //入力値をMD5化（identityとパスワード）
        String identity = requestScope("identity");
        String password = requestScope("password");

        //パスワードとユーザから検索
        ActorDao dao = new ActorDao();
        Actor actor = dao.findById(identity);

        if ( actor == null ) {
            //TODO メッセージを付与
            return forward("./index.jsp");
        }

        String target = ApplicationUtil.changeMD5(password);
        if ( !target.equals(actor.getPassword()) ) {
            //TODO メッセージを付与
            return forward("./index.jsp");
        }

        Integer purchase = actor.getPurchase();
        if ( purchase == null ) {
            //TODO メッセージを付与
            return forward("./index.jsp");
        }

        System.out.println("来てるよ？");

        sessionScope(Actor.class.getName(),actor);
        //TODO 指定ページへリダイレクト
        return redirect("/dashboard/");
    }
}
