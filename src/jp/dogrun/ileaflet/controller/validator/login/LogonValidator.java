package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;
import org.slim3.util.StringUtil;

public class LogonValidator extends AbstractValidator {

    public static LogonValidator INSTANCE = new LogonValidator();
    public String validate(Map<String, Object> parameters, String name) {
        
        //入力値をMD5化（identityとパスワード）
        String identity = (String)parameters.get("identity");
        if ( StringUtil.isEmpty(identity) ) {
            return null;
        }
        String password = (String)parameters.get("password");
        if ( StringUtil.isEmpty(password) ) {
            return null;
        }

        //パスワードとユーザから検索
        ActorDao dao = new ActorDao();
        Actor actor = dao.findById(identity);
        if ( actor == null ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get("validator.logon", getLabel("identity"),getLabel("password"));
        }

        String target = ApplicationUtil.changeMD5(password);
        if ( !target.equals(actor.getPassword()) ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get("validator.logon", getLabel("identity"),getLabel("password"));
        }

        Integer purchase = actor.getPurchase();
        if ( purchase == null ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get("validator.logonRegister");
        }

        return null;
    }

    @Override
    protected String getMessageKey() {
        return "validator.logon";
    }

}
