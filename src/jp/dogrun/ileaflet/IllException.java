package jp.dogrun.ileaflet;

public class IllException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IllException(String string, Exception e) {
        super(string,e);
    }

}
