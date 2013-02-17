package jp.dogrun.ileaflet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

public class ApplicationUtil {
   
    public static final String BACKET_NAME = "leaflet";
    
    public static String getHost(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        int idx = url.indexOf(uri);
        String server = url.substring(0,idx);
        return server;
    }

    public static String changeMD5(String src) {
        return digest("MD5",src);
    }
    
    private static String digest(String digest,String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("文字列がNull、または空です。");
        }

        // MD5で暗号化したByte型配列を取得する
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("変換する暗号の文字列が違う：" + digest,e);
        }
        md5.update(str.getBytes());
        byte[] enclyptedHash = md5.digest();
        // 暗号化されたByte型配列を、16進数表記文字列に変換する
        StringBuilder hexStrBuilder = new StringBuilder();
        for (int i = 0; i < enclyptedHash.length; i++) {
            // 16進数表記で1桁数値だった場合、2桁目を0で埋める
            if ((enclyptedHash[i] & 0xff) < 0x10) {
                hexStrBuilder.append("0");
            }
            hexStrBuilder.append(Integer.toHexString(0xff & enclyptedHash[i]));
        }
        return hexStrBuilder.toString();
    }
}
