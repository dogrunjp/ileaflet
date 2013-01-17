package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;

public class SameActorValidator extends AbstractValidator {

    public static SameActorValidator INSTANCE = new SameActorValidator();
    public SameActorValidator() {
    }

    public SameActorValidator(String message) {
        super(message);
    }

    public String validate(Map<String, Object> parameters, String name) {
        String identity = (String)parameters.get("identity");
        String mail = (String)parameters.get("mail");
        
        ActorDao dao = new ActorDao();
        Actor actor = dao.findById(identity);
        if ( actor != null ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get("validator.sameIdentity", getLabel("identity"));
        }

        actor = dao.findByMail(mail);
        if ( actor != null ){
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get("validator.sameMail", getLabel("mail"));
        }    
        return null;
    }

    @Override
    protected String getMessageKey() {
        return "validator.sameActor";
    }

}
