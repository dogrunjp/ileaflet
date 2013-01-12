package jp.dogrun.ileaflet.controller.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ForgetController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("forget.jsp");
    }
}
