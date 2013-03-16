package jp.dogrun.ileaflet.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;

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

        while (-1 != (size = input.read(buffer))) {
          output.write(buffer, 0, size);
        }
        input.close();
        output.close();
    }
    public static String signPolicyDocument(String policyDocument, String secret) {
        try {

            // String policyBase64 = Base64.encodeBase64String(policyDocument.getBytes("UTF8"));

            KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            return signData(privateKey,policyDocument);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
}

    private static String signData(PrivateKey key, String data)
            throws Exception {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(key);
        signer.update(data.getBytes("UTF-8"));
        byte[] rawSignature = signer.sign();
        String encodedSignature =
            new String(Base64.encodeBase64(rawSignature, false), "UTF-8");
        return encodedSignature;
    }

}
