package jp.dogrun.ileaflet.controller.login;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jp.dogrun.ileaflet.controller.validator.login.RegisterValidators;
import jp.dogrun.ileaflet.dao.ActorDao;
import jp.dogrun.ileaflet.model.Actor;
import jp.dogrun.ileaflet.util.ApplicationUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class RegisterController extends Controller {

    @Override
    public Navigation run() throws Exception {

        if ( !validate() ) {
            return forward("./create.jsp");
        }

        Actor actor = createActor();

        Transaction tx = Datastore.beginTransaction();
        try {
            String serverUrl = ApplicationUtil.getHost(request);
            String registUrl = serverUrl + "/login/registration?registerCode=" + actor.getKeyword();
            //メールを送信する
            sendRegistMail(actor.getName(),actor.getEmail(),registUrl);
            Datastore.put(actor);
            tx.commit();
        } catch ( Exception ex ) {
            tx.rollback();
            throw ex;
        }
        //TODO メールを送信しましたメッセージ
        return forward("../index.jsp");
    }
    
    private boolean validate() {
        RegisterValidators v = new RegisterValidators(request);
        v.add("identity",v.required(),v.keyword());
        v.add("name",v.required());
        v.add("mail",v.required(),v.regexp("[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+"));
        v.add("password",v.required(),v.samePassword());
        v.add("actor",v.sameUser());
        return v.validate();
    }
  
    private Actor createActor() {
      
        String keyword = null;
        while (true) {
            keyword = createKeyword();
        	ActorDao dao = new ActorDao();
        	Actor actor = dao.findByKeyword(keyword);
        	if ( actor == null ) break;
        }

        //入力チェック
        String identity = requestScope("identity");
        String name     = requestScope("name");
        String mail     = requestScope("mail");
        String pass1    = requestScope("password");

        //ユーザを作成
        Actor actor = new Actor();
        actor.setIdentity(identity);
        actor.setName(name);
        actor.setEmail(mail);

        String enPass = ApplicationUtil.changeMD5(pass1);
        actor.setPassword(enPass);
        actor.setPurchase(null);
        actor.setKeyword(keyword);

        return actor;
    }

    private String createKeyword() {
        Random r = new Random();
        r.setSeed((new Date()).getTime());
        double xxx = r.nextDouble();
        return ApplicationUtil.changeMD5(String.valueOf(xxx));
    }
    
    public void sendRegistMail(String name,String email,String url) throws MessagingException, UnsupportedEncodingException {

        InternetAddress ToAddress = new InternetAddress(email,name, "ISO-2022-JP");
        InternetAddress FromAddress = new InternetAddress("secondarykey@gmail.com", "iLeaflet管理者", "ISO-2022-JP");

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(FromAddress);
        message.addRecipient(Message.RecipientType.TO, ToAddress);
        message.setSubject("iLeaflet仮登録のお知らせ", "ISO-2022-JP");

        message.setText(url);
        Transport.send(message); 
    }

}
