package jp.dogrun.ileaflet.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

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
    
    
    public static String createPolicyDocument() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MINUTE, 20);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String expiration = df.format(gc.getTime());

        StringBuilder buf = new StringBuilder();
        buf.append("{\"expiration\": \"");
        buf.append(expiration);
        buf.append("\"");
        buf.append(",\"conditions\": [");
        buf.append("[\"starts-with\", \"$key\", \"\"]");
        buf.append("]}");
  
        /*
        {"expiration": "2010-06-16T11:11:11Z",
            "conditions": [
             ["starts-with", "$key", "" ],
             {"acl": "bucket-owner-read" },
             {"bucket": "travel-maps"},
             {"success_action_redirect": "http://www.example.com/success_notification.html" },
             ["eq", "$Content-Type", "image/jpeg" ],
             ["content-length-range", 0, 1000000]
             ]
           }
           */

        return Base64.encodeBase64String(buf.toString().replaceAll("\n", "").getBytes());
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
    
    private static String signData(PrivateKey key, String data) throws Exception {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(key);
        signer.update(data.getBytes("UTF-8"));
        byte[] rawSignature = signer.sign();
        String encodedSignature = new String(Base64.encodeBase64(rawSignature, false), "UTF-8");
        return encodedSignature;
      }
}
