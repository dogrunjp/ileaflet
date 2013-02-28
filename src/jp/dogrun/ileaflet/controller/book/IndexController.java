package jp.dogrun.ileaflet.controller.book;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String id = requestScope("id");
        ContentDao dao = new ContentDao();
        Content content = dao.findById(id);
        if ( content == null ) {
            //TODO 回避
            redirect("/");
        }
        requestScope("content",content);
        return forward("index.jsp");
    }
}
