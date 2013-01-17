package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.controller.validator.login.RegisterValidators;
import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

public class RegisterController extends Controller {

    @Override
    public Navigation run() throws Exception {

        if ( !validate() ) {
            return forward("./create.jsp");
        }

        Actor actor = createActor();
        Datastore.put(actor);
        sessionScope(Actor.class.getName(),actor);
        return redirect("/dashboard/");
    }
    
    private boolean validate() {
        RegisterValidators v = new RegisterValidators(request);
        v.add("identity",v.required());
        v.add("name",v.required());
        v.add("mail",v.required());
        v.add("password",v.required(),v.samePassword());
        v.add("actor",v.sameUser());
        return v.validate();
    }
  
    private Actor createActor() {

        //入力チェック
        String identity = requestScope("identity");
        String name     = requestScope("name");
        String mail     = requestScope("mail");
        String pass1    = requestScope("password");

        //ユーザを作成
        Actor actor = new Actor();
        actor.setIdentity(identity);
        actor.setName(name);
        actor.setEmail(mail);

        String enPass = ApplicationUtil.changeMD5(identity + "-" + pass1);
        actor.setPassword(enPass);

        return actor;
    }

}
