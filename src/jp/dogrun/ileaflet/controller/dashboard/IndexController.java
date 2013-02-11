package jp.dogrun.ileaflet.controller.dashboard;

import java.util.List;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Navigation;

public class IndexController extends DashboardController {

    @Override
    public Navigation process() throws Exception {

        Actor actor = getActor();
        ContentDao dao = new ContentDao();
        List<Content> contentList =dao.findByIdentity(actor.getIdentity());

        requestScope("contentList",contentList);
        return forward("index.jsp");
    }
}
