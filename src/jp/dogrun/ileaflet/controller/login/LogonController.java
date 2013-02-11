package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.controller.validator.login.LogonValidators;
import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogonController extends Controller {

    @Override
    public Navigation run() throws Exception {

        if ( !validate() ) {
            return forward("./index.jsp");
        }

        String identity = requestScope("identity");
        //パスワードとユーザから検索
        ActorDao dao = new ActorDao();
        Actor actor = dao.findById(identity);
        sessionScope(Actor.class.getName(),actor);
        return redirect("/dashboard/");
    }

    private boolean validate() {
        LogonValidators v = new LogonValidators(request);
        v.add("identity", v.required());
        v.add("password", v.required());
        v.add("logon", v.logon());
        return v.validate();
    }
}
