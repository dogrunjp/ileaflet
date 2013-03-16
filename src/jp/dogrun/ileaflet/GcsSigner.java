package jp.dogrun.ileaflet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;

import org.apache.commons.codec.binary.Base64;

public class GcsSigner {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
          System.err.println("Usage: java GcsSigner path/to/key \"key password\" \"String to sign\"");
          System.exit(-1);
        }

        String keyFile = args[0];
        String keyPassword = args[1];
        String signFile = args[2];
        
        File file = new File(signFile);
        String stringToSign = fileToString(file);

        PrivateKey key = loadKeyFromPkcs12(keyFile, keyPassword.toCharArray());
        String signature = signData(key, stringToSign);
        System.out.println(signature);
      }

      private static PrivateKey loadKeyFromPkcs12(String filename, char[] password) throws Exception {
        FileInputStream fis = new FileInputStream(filename);
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try {
          ks.load(fis, password);
        } catch (IOException e) {
          if (e.getCause() != null && e.getCause() instanceof UnrecoverableKeyException) {
            System.err.println("Incorrect password");
          }
          throw e;
        }
        return (PrivateKey)ks.getKey("privatekey", password);
      }


      private static String signData(PrivateKey key, String data) throws Exception {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(key);
        signer.update(data.getBytes("UTF-8"));
        byte[] rawSignature = signer.sign();
        String encodedSignature = new String(Base64.encodeBase64(rawSignature, false), "UTF-8");
        return encodedSignature;
      }
      
      // ファイル内容をを文字列化するメソッドです。
      private static String fileToString(File file) throws IOException {
        BufferedReader br = null;
        try {
          // ファイルを読み込むバッファドリーダを作成します。
          br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
          // 読み込んだ文字列を保持するストリングバッファを用意します。
          StringBuffer sb = new StringBuffer();
          // ファイルから読み込んだ一文字を保存する変数です。
          int c;
          // ファイルから１文字ずつ読み込み、バッファへ追加します。
          while ((c = br.read()) != -1) {
            sb.append((char) c);
          }
          // バッファの内容を文字列化して返します。
          return sb.toString();
        } finally {
          // リーダを閉じます。
          br.close();
        }
      }
}
