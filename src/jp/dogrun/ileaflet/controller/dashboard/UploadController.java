package jp.dogrun.ileaflet.controller.dashboard;

import java.nio.ByteBuffer;

import jp.dogrun.ileaflet.logic.EpubLogic;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

public class UploadController extends Controller {

    @Override
    public Navigation run() throws Exception {

        FileItem fileItem = requestScope("epubFile");
        
        EpubLogic logic = new EpubLogic();
        byte[] epubData = fileItem.getData();
        
        if ( !logic.isCheck(epubData) ) {
            //TODO メッセージ
            return forward("index.jsp");
        }
        Actor actor = sessionScope(Actor.class.getName());

        //コンテンツデータを設定
        Content content = new Content();
        content.setIdentity(actor.getIdentity());
        content.setTitle(fileItem.getFileName());
        content.setTargetRevision(0);
        content.setPurchase(false);
        content.setPublish(false);
        content.setCapacity(Long.valueOf(epubData.length));

        Transaction tx = Datastore.beginTransaction();
        try {
            Datastore.put(tx,content);
            Key key = content.getKey();
            String BUCKETNAME = "leaflet";
        	String FILENAME = actor.getIdentity() + "/"+ key.getId() + "/" + content.getTargetRevision() + ".epub";

        	FileService fileService = FileServiceFactory.getFileService();
        	GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
                	.setBucket(BUCKETNAME)
                	.setKey(FILENAME)
                	.setMimeType(fileItem.getContentType());
        	AppEngineFile file = fileService
                	.createNewGSFile(optionsBuilder.build());
	
        	FileWriteChannel writeChannel = fileService.openWriteChannel(file, true);
        	ByteBuffer buffer = ByteBuffer.wrap(fileItem.getData());
        	writeChannel.write(buffer);
        	writeChannel.closeFinally();
        	tx.commit();
        } catch ( Exception ex ) {
            tx.rollback();
            throw ex;
        }
        
        //TODO アップロードしました。
        return forward("index.jsp");
    }
}
