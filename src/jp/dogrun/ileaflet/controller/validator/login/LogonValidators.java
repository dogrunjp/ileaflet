package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slim3.controller.validator.Validator;
import org.slim3.controller.validator.Validators;

public class LogonValidators extends Validators {

    public LogonValidators(HttpServletRequest request) {
        super(request);
    }

    public LogonValidators(Map<String, Object> parameters)
            throws NullPointerException {
        super(parameters);
    }

    public Validator logon() {
        return LogonValidator.INSTANCE;
    }
}
