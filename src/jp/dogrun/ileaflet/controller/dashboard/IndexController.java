package jp.dogrun.ileaflet.controller.dashboard;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;

import org.apache.commons.codec.binary.Base64;
import org.slim3.controller.Navigation;


public class IndexController extends DashboardController {

    @Override
    public Navigation process() throws Exception {

        Actor actor = getActor();
        ContentDao dao = new ContentDao();
        List<Content> contentList =dao.findByIdentity(actor.getIdentity());

        requestScope("contentList",contentList);

        String bucket = "leaflet";
        String accessId = "674155994016-c7u194ld56qq7bhf5kj5ev9465n8gvbe@developer.gserviceaccount.com";
        //String accessId = "ileafletapp@appspot.gserviceaccount.com";
        String successActionRedirect = getBaseUrl() + "dashboard/";
        String acl = "bucket-owner-read";
        String key = UUID.randomUUID().toString();
        String type = "image/jpeg";

        String policy = createPolicyDocument(acl,bucket,successActionRedirect,type);

        requestScope("uploadUrl","https://" + bucket + ".storage.googleapis.com/" + bucket + "/");
        requestScope("key",key);
        requestScope("bucket",bucket);
        requestScope("contentType",type);
        requestScope("acl",acl);
        requestScope("googleAccessId",accessId);
        requestScope("policy",policy);
        requestScope("success_action_redirect",successActionRedirect);
        requestScope("signature","YRWeSUBGy/LVYPURLyyJo3RKWF16AwdiAu/hPNPlatv4Hew1Q8GV6cD7e9DDDR91MHxf0SAuOdhB37pBk5PJfy8xlDGtLXBK3qVrgTus2AJItqYqp07MOXEPRuuVWRQaRXOakcAvQcfnOy9zQvMLf8QJY+cWT5XgXs4YoYN2HGA=");
        
        return forward("index.jsp");
    }

    private String getBaseUrl() {
        String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        return base;
	}
    
    public static String createPolicyDocument(String acl,String bucket,String redirect,String type) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MINUTE, 20);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String expiration = df.format(gc.getTime());

        StringBuilder buf = new StringBuilder();
        buf.append("{\"expiration\": \"" + expiration + "\",");
        buf.append("\"conditions\": [");
        buf.append("[\"starts-with\", \"$key\", \"\"],");
        buf.append("{\"acl\", \"" + acl + "\"},");
        buf.append("{\"bucket\", \"" + bucket + "\"},");
        buf.append("{\"success_action_redirect\", \"" + redirect + "\"},");
        buf.append("[\"eq\", \"$Content-Type\", \"" + type + "\"]");
        buf.append("]");
        buf.append("}");
  
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

        String data = buf.toString().replaceAll("\n", "");
        try {
            return Base64.encodeBase64String(data.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
