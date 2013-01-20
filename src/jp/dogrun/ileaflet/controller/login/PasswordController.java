package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class PasswordController extends Controller {

    @Override
    public Navigation run() throws Exception {
       
        //登録コードの確認
        String code = requestScope("registerCode");
        ActorDao dao = new ActorDao();
        Actor actor = dao.findByKeyword(code);
        if ( actor == null ) {
            //TODO メッセージの設定
            return forward("password.jsp");
        }
      
        return forward("password.jsp");
    }
}
