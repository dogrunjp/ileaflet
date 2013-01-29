package jp.dogrun.ileaflet.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EpubLogic {

    public EpubLogic() {
    }

    public boolean isCheck(byte[] data) {
        
        InputStream inStream = new ByteArrayInputStream(data);
        ZipInputStream epubStream = new ZipInputStream(inStream);

        ZipEntry entry;
        try {
            entry = epubStream.getNextEntry();
        } catch (IOException e) {
            return false;
        }

        if ( entry == null ) return false;
        if ( !entry.getName().equals("mimetype") ) return false;
        
        byte[] mimeData = read(epubStream,entry);
        String mimeBuf = new String(mimeData);
        if ( !mimeBuf.equals("application/epub+zip") ) return false;

        Map<String,Boolean> epubMap = new HashMap<String,Boolean>();
        epubMap.put("mimetype", true);

        byte[] containerData = null;
        String opfName = null;
        byte[] opfData = null;
        try {
            while ( (entry = epubStream.getNextEntry()) != null ) {
                String name = entry.getName();
                epubMap.put(name, true);

                if ( name.equals(opfName) ) {
                    opfData = read(epubStream,entry);
                } else if ( name.equals("META-INF/container.xml") ) {
                    containerData = read(epubStream,entry);
                    opfName = getOpfName(containerData);
                    if ( opfName == null ) return false;
                }
        	}
            
        } catch (IOException e) {
            return false;
        }
        
        try {
            epubStream.close();
        } catch (IOException e) {
            return false;
        }

        if ( containerData == null ) return false;
        if ( opfName == null ) return false;

        //optファイルの取得
        if ( opfData == null ) {
            //OPFファイルの存在確認
            if ( !epubMap.get(opfName) ) return false;
            try {
                opfData = retry(opfName,data);
            } catch (IOException e) {
                return false;
            }
            if ( opfData == null ) {
                return false;
            }
        }

        return checkOpf(opfData,epubMap);
    }
  
    private boolean checkOpf(byte[] opfData, Map<String, Boolean> epubMap) {
        //全てのファイルが存在しているか？
        //item のhrefが全部あるか？
        //reference のhrefが全部あるか？
        Document doc = null;
        try {
            doc = loadXml(opfData);
        } catch (ParserConfigurationException e) {
            return false;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        NodeList itemTagList = doc.getElementsByTagName("item");
        if ( itemTagList != null ) {
            for ( int idx = 0; idx < itemTagList.getLength(); ++idx ) {
                Element item = (Element)itemTagList.item(idx);
                String path = item.getAttribute("href");
                if ( !epubMap.get(path) ) {
                    return false;
                }
            }
        }

        NodeList referenceTagList = doc.getElementsByTagName("reference");
        if ( referenceTagList != null ) {
            for ( int idx = 0; idx < referenceTagList.getLength(); ++idx ) {
                Element item = (Element)referenceTagList.item(idx);
                String path = item.getAttribute("href");
                if ( !epubMap.get(path) ) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 圧縮の関係でOPFデータが取得できなかった場合の再処理
     * @param data
     * @return
     * @throws IOException 
     */
    private byte[] retry(String opfName,byte[] data) throws IOException {
        InputStream inStream = new ByteArrayInputStream(data);
        ZipInputStream epubStream = new ZipInputStream(inStream);
        ZipEntry entry = null;
        byte[] opfData = null;
        while ( (entry = epubStream.getNextEntry()) != null ) {
            String name = entry.getName();
            if ( name.equals(opfName) ) {
                opfData = read(epubStream,entry);
                epubStream.close();
                return opfData;
            }
        }
        epubStream.close();
        
        return opfData;
    }
    
    private Document loadXml(byte[] data) throws ParserConfigurationException,SAXException,IOException {
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fact.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(data));
        return doc;
    }

    /**
     * OPFFileの取得
     * @param containerData
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private String getOpfName(byte[] containerData)  {
        Document doc;
        try {
            doc = loadXml(containerData);
        } catch (ParserConfigurationException e) {
            return null;
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        NodeList rootFileTagList = doc.getElementsByTagName("rootfile");
        if ( rootFileTagList == null || rootFileTagList.getLength() != 1 ) {
            return null;
        }

        Element rootFileTag = (Element)rootFileTagList.item(0);
        String opfName = rootFileTag.getAttribute("full-path");
   
        return opfName;
    }

    private byte[] read(ZipInputStream stream,ZipEntry entry) {
        byte[] data = new byte[(int)entry.getSize()];
        try {
            stream.read(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

}
