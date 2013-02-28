package jp.dogrun.ileaflet.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.LockException;

import jp.dogrun.ileaflet.model.Content;

public class GCSUtil {

    public static byte[] get(Content content) throws FileNotFoundException, LockException, IOException {

        FileService fileService = FileServiceFactory.getFileService();
        String filename = "/gs/leaflet/" + 
                content.getIdentity() + "/" + content.getKey().getId() + "/" + content.getTargetRevision() + ".epub";
        AppEngineFile readableFile = new AppEngineFile(filename);

        FileReadChannel readChannel =
            fileService.openReadChannel(readableFile, false);
        InputStream is = Channels.newInputStream(readChannel);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       
        copy(is,outStream);
        
        return outStream.toByteArray();
    }
    

    private static void copy(InputStream input,OutputStream output) throws IOException {
        int DEFAULT_BUFFER_SIZE = 1024 * 50;
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int size = -1;

        int cnt = 1;
        while (-1 != (size = input.read(buffer))) {
            System.out.println("Loop:" + cnt);
          output.write(buffer, 0, size);
          cnt++;
        }
        input.close();
        output.close();
    }
    
}
