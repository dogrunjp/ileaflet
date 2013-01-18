package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slim3.controller.validator.Validators;

public class RegisterValidators extends Validators {

    public RegisterValidators(HttpServletRequest request) {
        super(request);
    }

    public RegisterValidators(Map<String, Object> parameters)
            throws NullPointerException {
        super(parameters);
    }

    public SamePasswordValidator samePassword() {
        return SamePasswordValidator.INSTANCE;
    }

    public SameActorValidator sameUser() {
        return SameActorValidator.INSTANCE;
    }

    public KeywordValidator keyword() {
        return KeywordValidator.INSTANCE;
    }
   
}
