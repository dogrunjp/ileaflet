package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.controller.validator.login.RegisterValidators;
import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

public class SetPasswordController extends Controller {

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
        if ( !validate() ) {
            return forward("password.jsp");
        }

        String password = requestScope("password");
        password = ApplicationUtil.changeMD5(password);

        actor.setPassword(password);
        Datastore.put(actor);

        return forward("index.jsp");
    }

    private boolean validate() {
        RegisterValidators v = new RegisterValidators(request);
        v.add("password",v.required(),v.samePassword());
        return v.validate();
    }
  
}
