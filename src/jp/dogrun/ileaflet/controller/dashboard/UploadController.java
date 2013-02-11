package jp.dogrun.ileaflet.controller.dashboard;

import java.nio.ByteBuffer;

import jp.dogrun.ileaflet.controller.validator.dashboard.UploadValidators;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;

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

public class UploadController extends DashboardController {

    @Override
    public Navigation process() throws Exception {
        
        if ( !validate() ) {
            return forward("index.jsp");
        }

        FileItem fileItem = requestScope("epubFile");
        Actor actor = getActor();

        //コンテンツデータを設定
        Content content = new Content();
        content.setIdentity(actor.getIdentity());
        content.setTitle(fileItem.getFileName());
        content.setTargetRevision(0);
        content.setPurchase(false);
        content.setPublish(false);
        content.setCapacity(Long.valueOf(fileItem.getData().length));

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
        return redirect("/dashboard/");
    }

    private boolean validate() {
        UploadValidators v = new UploadValidators(request);
        v.add("epubFile", v.required(),v.check());
        return v.validate();
    }
}
