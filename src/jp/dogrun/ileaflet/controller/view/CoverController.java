package jp.dogrun.ileaflet.controller.view;

import jp.dogrun.ileaflet.dao.CoverDao;
import jp.dogrun.ileaflet.model.Cover;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CoverController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String id = requestScope("id");
        CoverDao dao = new CoverDao();
        Cover cover = dao.findByContentId(id);
        show(cover.getFileName(),cover.getData());
        return null;
    }
}
