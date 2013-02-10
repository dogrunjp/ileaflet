package jp.dogrun.ileaflet.controller.download;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ThrowableUtil;

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
        if ( content == null ) {
            //TODO 回避
        }

        FileService fileService = FileServiceFactory.getFileService();
        String filename = "/gs/leaflet/" + 
                content.getIdentity() + "/" + content.getKey().getId() + "/" + content.getTargetRevision() + ".epub";
        AppEngineFile readableFile = new AppEngineFile(filename);

        FileReadChannel readChannel =
            fileService.openReadChannel(readableFile, false);
        
        Integer capacity = (new BigDecimal(content.getCapacity())).intValue();
        ByteBuffer buffer = ByteBuffer.allocate(capacity);
        readChannel.read(buffer);

        this.download(content.getTitle() + ".epub", buffer.array());

        return null;
    }
 
    protected void download(String fileName, byte[] data)
            throws NullPointerException {
        if (fileName == null) {
            throw new NullPointerException(
                "The fileName parameter must not be null.");
        }
        if (data == null) {
            throw new NullPointerException(
                "The data parameter must not be null.");
        }
        try {
            response.setContentType("application/epub+zip");
            response.setHeader("Content-disposition", "attachment; "
                + encodeFileName(fileName));
            OutputStream out = response.getOutputStream();
            try {
                out.write(data);
            } finally {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            ThrowableUtil.wrapAndThrow(e);
        }
    }
}
