package jp.dogrun.ileaflet.controller.view;

import java.io.InputStream;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.logic.EpubLogic;
import jp.dogrun.ileaflet.model.Content;
import jp.dogrun.ileaflet.util.GCSUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;


public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
      
        String id = requestScope("id");
        ContentDao dao = new ContentDao();
        Content content = dao.findById(id);
        if ( content == null ) {
            //TODO 存在しないエラー
        }

        InputStream is = GCSUtil.getInputStream(content);
        EpubLogic logic = new EpubLogic();
        if ( !logic.isCheck(is) ) {
           System.out.println("EPUB じゃない"); 
        }

        return forward("index.jsp");
    }
}
