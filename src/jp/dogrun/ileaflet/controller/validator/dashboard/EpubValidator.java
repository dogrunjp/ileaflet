package jp.dogrun.ileaflet.controller.validator.dashboard;

import java.util.Map;

import jp.dogrun.ileaflet.logic.EpubLogic;

import org.slim3.controller.upload.FileItem;
import org.slim3.controller.validator.AbstractValidator;
import org.slim3.util.ApplicationMessage;

public class EpubValidator extends AbstractValidator {

    public static final EpubValidator INSTANCE = new EpubValidator();
    public EpubValidator() {
    }

    public EpubValidator(String message) {
        super(message);
    }

    public String validate(Map<String, Object> parameters, String name) {
        
        FileItem fileItem = (FileItem)parameters.get(name);
        if ( fileItem == null ) return null;
        
        EpubLogic logic = new EpubLogic();
        byte[] epubData = fileItem.getData();
        if ( !logic.isCheck(epubData) ) {
            if (message != null) {
                return message;
            }
            return ApplicationMessage.get(getMessageKey(), getLabel(name));
        }

        return null;
    }

    @Override
    protected String getMessageKey() {
        return "validator.epubCheck";
    }

}
