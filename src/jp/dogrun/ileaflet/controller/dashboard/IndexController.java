package jp.dogrun.ileaflet.controller.dashboard;

import java.util.List;
import java.util.UUID;

import jp.dogrun.ileaflet.dao.ContentDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.model.Content;
import jp.dogrun.ileaflet.util.GCSUtil;

import org.slim3.controller.Navigation;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class IndexController extends DashboardController {

    @Override
    public Navigation process() throws Exception {

        Actor actor = getActor();
        ContentDao dao = new ContentDao();
        List<Content> contentList =dao.findByIdentity(actor.getIdentity());

        requestScope("contentList",contentList);

        String secret = "dRP8RSKoFI8BBRZiNSzvsQCN";
        String bucket = "secondarykey_test";
        String successActionRedirect = getBaseUrl() + "dashboard/";
        String acl = "bucket-owner-read";
        String key = UUID.randomUUID().toString();
 
        String policy = GCSUtil.createPolicyDocument();
        String signature = GCSUtil.signPolicyDocument(policy, secret);
        
        requestScope("uploadUrl","https://" + bucket + ".storage.googleapis.com/");
        requestScope("key",key);
        requestScope("bucket",bucket);
        requestScope("contentType","image/jpeg");
        requestScope("acl",acl);
        requestScope("accessId","674155994016@developer.gserviceaccount.com");
        requestScope("policy",policy);
        requestScope("success_action_redirect",successActionRedirect);
        requestScope("signature",signature);
        
        return forward("index.jsp");
    }
    
    

    private String getBaseUrl() {
        String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        return base;
	}
}
