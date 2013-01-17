package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;

public class SamePasswordValidator extends AbstractValidator {

    public static SamePasswordValidator INSTANCE = new SamePasswordValidator();
    public SamePasswordValidator() {
        super();
    }

    public SamePasswordValidator(String message) {
        super(message);
    }

    public String validate(Map<String, Object> parameters, String name) {
        Object value1 = parameters.get("password");
        Object value2 = parameters.get("password2");
        if ( value1 != null &&
                !value1.equals(value2) ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get(getMessageKey(), getLabel(name));
        }
        return null;
    }

    @Override
    protected String getMessageKey() {
        return "validator.samePassword";
    }

}
