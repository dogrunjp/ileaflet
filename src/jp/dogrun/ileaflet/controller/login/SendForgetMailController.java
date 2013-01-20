package jp.dogrun.ileaflet.controller.login;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class SendForgetMailController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        //メールアドレスから該当ユーザを検索
        ActorDao dao = new ActorDao();
        Actor actor = dao.findByMail((String)requestScope("mail"));
        if ( actor == null ) {
            return forward("forget.jsp");
        }

        String keyword = null;
        while( true ) {
            //時刻＋identityからMD5化
        	keyword = (new Date()) + actor.getIdentity();
        	keyword = ApplicationUtil.changeMD5(keyword);
        	//キーワードが重複しないかチェック
        	Actor diffActor = dao.findByKeyword(keyword);
        	if ( diffActor == null ) {
        	    actor.setKeyword(keyword);
        	    break;
        	}
        }

        Transaction tx = Datastore.beginTransaction();
        try {
            String serverUrl = ApplicationUtil.getHost(request);
            String registUrl = serverUrl + "/login/password?registerCode=" + actor.getKeyword();
            //メールを送信する
            sendForgetMail(actor.getName(),actor.getEmail(),registUrl);
            Datastore.put(actor);
            tx.commit();
        } catch ( Exception ex ) {
            tx.rollback();
            throw ex;
        }

        return forward("forget.jsp");
    }

    public void sendForgetMail(String name,String email,String url) throws MessagingException, UnsupportedEncodingException {

        InternetAddress ToAddress = new InternetAddress(email,name, "ISO-2022-JP");
        InternetAddress FromAddress = new InternetAddress("secondarykey@gmail.com", "iLeaflet管理者", "ISO-2022-JP");

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(FromAddress);
        message.addRecipient(Message.RecipientType.TO, ToAddress);
        message.setSubject("iLeafletパスワード再設定のお知らせ", "ISO-2022-JP");

        message.setText(url);
        System.out.println(url);
        Transport.send(message); 
    }
}
