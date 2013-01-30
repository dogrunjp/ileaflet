package jp.dogrun.ileaflet.controller.download;

import java.io.InputStream;
import java.nio.channels.Channels;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        String id = requestScope("id");
        ContentDao dao = new ContentDao();
        Content content = dao.findById(id);

        FileService fileService = FileServiceFactory.getFileService();
        String filename = "/gs/leaflet/" + 
                content.getIdentity() + "/" + content.getKey().getId() + "/" + content.getTargetRevision() + ".epub";
        AppEngineFile readableFile = new AppEngineFile(filename);
        FileReadChannel readChannel =
            fileService.openReadChannel(readableFile, false);
        
        InputStream inputStream = Channels.newInputStream(readChannel);

        this.download(content.getTitle() + ".epub", inputStream);
        
        return null;
    }
}
