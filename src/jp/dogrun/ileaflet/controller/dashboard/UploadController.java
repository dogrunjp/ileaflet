package jp.dogrun.ileaflet.controller.dashboard;

import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

import jp.dogrun.ileaflet.logic.EpubLogic;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;
import com.google.appengine.api.files.GSFileOptions.GSFileOptionsBuilder;

public class UploadController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        FileItem fileItem = requestScope("epubFile");

        //TODO タスクキューに入れるかな？
        EpubLogic logic = new EpubLogic();
        
        logic.isCheck(fileItem.getData());
      
        //容量等の情報を設定
       
        //状態を仮の状態にする

        //Client ID:  
            //674155994016.apps.googleusercontent.com
            //Client secret:  dRP8RSKoFI8BBRZiNSzvsQCN
            //Email address:  
            //674155994016@developer.gserviceaccount.com
            //Redirect URIs:  https://ileafletapp.appspot.com/oauth2callback
            //JavaScript origins: 
            //https://ileafletapp.appspot.com
       
       
        String BUCKETNAME = "leaflet";
        String FILENAME = "/gs/" + BUCKETNAME + "/" + fileItem.getFileName();
        
       
        System.out.println(FILENAME);

        FileService fileService = FileServiceFactory.getFileService();
        GSFileOptionsBuilder optionsBuilder = new GSFileOptionsBuilder()
                .setBucket(BUCKETNAME)
                .setKey(FILENAME)
                .setMimeType(fileItem.getContentType())
                .setAcl("private");
        AppEngineFile file = fileService
                .createNewGSFile(optionsBuilder.build());
        FileWriteChannel writeChannel = fileService.openWriteChannel(file, true);
        ByteBuffer buffer = ByteBuffer.wrap(fileItem.getData());
        writeChannel.write(buffer);
        writeChannel.closeFinally();
        

        return forward("index.jsp");
    }
}
