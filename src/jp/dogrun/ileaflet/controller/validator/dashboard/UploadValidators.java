package jp.dogrun.ileaflet.controller.validator.dashboard;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slim3.controller.validator.Validator;
import org.slim3.controller.validator.Validators;

public class UploadValidators extends Validators {

    public UploadValidators(HttpServletRequest request) {
        super(request);
    }

    public UploadValidators(Map<String, Object> parameters)
            throws NullPointerException {
        super(parameters);
    }

    public Validator check() {
        return EpubValidator.INSTANCE;
    }

}
