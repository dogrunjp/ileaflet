package jp.dogrun.ileaflet.logic;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class EpubLogicTest {

    @Test
    public void test() {
        
        EpubLogic logic = new EpubLogic();
        
        File file = new File("./test/israelsailing-20130127.epub");
        InputStream inStream;
        try {
            inStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            fail("例外が起きないはず");
            return;
        }
       
        byte[] data = new byte[(int)file.length()];
        
        try {
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            fail("例外が起きないはず");
        }
        
        assertTrue(logic.isCheck(data));

        
        //META-INF/container.xml
        
    }

}
