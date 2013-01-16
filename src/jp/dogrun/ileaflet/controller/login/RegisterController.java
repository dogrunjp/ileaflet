package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

public class RegisterController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        Actor actor = createActor();
        if ( actor == null ) {
            return forward("./create.jsp");
        }
 
        Datastore.put(actor);
        sessionScope(Actor.class.getName(),actor);
        return redirect("/dashboard/");
    }

    private Actor createActor() {
        //入力チェック
        String identity = requestScope("identity");
        String name     = requestScope("name");
        String mail     = requestScope("mail");
        String pass1    = requestScope("password");
        String pass2    = requestScope("password2");
        
        if ( 
             StringUtil.isEmpty(identity) ||
             StringUtil.isEmpty(name) ||
             StringUtil.isEmpty(mail) ||
             StringUtil.isEmpty(pass1)
        ) {
            return null;
        }
        if ( !pass1.equals(pass2) ) {
            return null;
        }
        
        ActorDao dao = new ActorDao();
        Actor actor = dao.findById(identity);
        if ( actor == null ) {
            actor = dao.findByMail(mail);
        }

        if ( actor != null ) {
            return null;
        }
        
        //ユーザを作成
        actor = new Actor();
        actor.setIdentity(identity);
        actor.setName(name);
        actor.setEmail(mail);

        String enPass = ApplicationUtil.changeMD5(identity + "-" + pass1);
        actor.setPassword(enPass);
        
        return actor;
    }

}
