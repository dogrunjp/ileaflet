package jp.dogrun.ileaflet.controller.validator.login;

import java.util.Map;

import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;
import org.slim3.util.StringUtil;

public class KeywordValidator extends AbstractValidator {

    public static KeywordValidator INSTANCE = new KeywordValidator();
    public KeywordValidator() {
        super();
    }

    public KeywordValidator(String message) {
        super(message);
    }

    public String validate(Map<String, Object> parameters, String name) {
        String value = (String)parameters.get(name);
        if ( isKeyword(value) ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get(getMessageKey(), getLabel(name));
        }
        return null;
    }

    private boolean isKeyword(String value) {
        if ( StringUtil.isEmpty(value)) return false;
        if(
                value.equals("dashboard") ||
                value.equals("dashboard") ||
                value.equals("dashboard")
                ) {
           return true;
        }

        return false;
    }

    @Override
    protected String getMessageKey() {
        return "validator.keyword";
    }

}
