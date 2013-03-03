package jp.dogrun.ileaflet.logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jp.dogrun.ileaflet.IllException;
import jp.dogrun.ileaflet.dto.EpubDto;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EpubLogic {

    public EpubLogic() {
    }

    /**
     * Epubのデータを抽出
     * @param data
     * @return
     */
    public EpubDto createEpubDao(byte[] data) {
        
        byte[] containerData;
        try {
            containerData = getData("META-INF/container.xml",data);
        } catch (IOException e) {
            throw new IllException("META-INF/container.xmlの取得に失敗",e);
        }
        String opfName = getOpfName(containerData);
        String path = opfName.substring(0,opfName.indexOf("/") + 1);

        byte[] opfData;
        try {
            opfData = getData(opfName,data);
        } catch (IOException e) {
            throw new IllException(opfName + "の取得に失敗",e);
        }

        Document doc;
        try {
            doc = loadXml(opfData);
        } catch (ParserConfigurationException e) {
            throw new IllException(opfName + "のパースに失敗",e);
        } catch (SAXException e) {
            throw new IllException(opfName + "のパースに失敗",e);
        } catch (IOException e) {
            throw new IllException(opfName + "のパースに失敗",e);
        }

        EpubDto dto = new EpubDto();

        //<dc:title>PHOTOHOKU_1212</dc:title>
        dto.setTitle(getTagText(doc,"dc:title"));
        //<dc:creator id="creator">PHOTOHOKU</dc:creator>
        dto.setCreator(getTagText(doc,"dc:creator"));
        //<dc:language>ja</dc:language>
        dto.setLangage(getTagText(doc,"dc:language"));

        NodeList itemList = doc.getElementsByTagName("item");
        for ( int idx = 0; idx < itemList.getLength(); ++idx ) {
            Element elm = (Element)itemList.item(idx);
            String id = elm.getAttribute("id");
            String href = elm.getAttribute("href");
            dto.putResource(id,href);
        }

        NodeList pageList = doc.getElementsByTagName("itemref");
        for ( int idx = 0; idx < pageList.getLength(); ++idx ) {
            Element elm = (Element)pageList.item(idx);
            String id = elm.getAttribute("idref");
            dto.addPage(id);
        }

        NodeList metaList = doc.getElementsByTagName("meta");
        //<meta name="cover" content="cover_5266"/>
        String coverName = null;
        for ( int idx = 0; idx < metaList.getLength(); ++idx ) {
            Element elm = (Element)metaList.item(idx);
            String name = elm.getAttribute("name");
            if ( "cover".equals(name) ) {
                coverName = elm.getAttribute("content");
                break;
            }
        }

        if ( coverName != null ) {
            String dataName  = dto.getResourceMap().get(coverName);
            try {
                byte[] coverData = getData(path + dataName,data);
                dto.setCover(coverData);
                dto.setCoverName(dataName);
            } catch (IOException e) {
                throw new IllException("カバーデータのの取得に失敗",e);
            }
        }
       
        /*
        <dc:identifier id="pub-id">urn:uuid:2e0b7b3e-0dbe-45ef-a1eb-a472dacb544b</dc:identifier>
        <meta property="dcterms:identifier" id="uuid">urn:uuid:2e0b7b3e-0dbe-45ef-a1eb-a472dacb544b</meta>
        <meta property="dcterms:modified">2012-12-12T00:00:00Z</meta>
        <dc:type>Fixedlayout EPUB</dc:type>
        <dc:date>2012-12-12T00:00:00Z</dc:date>
        <meta property="rendition:layout">pre-paginated</meta>
        <meta property="rendition:spread">auto</meta>
        <meta property="rendition:orientation">auto</meta>
        <meta property="layout:orientation">auto</meta>
        <meta property="layout:page-spread">auto</meta>
        <meta property="layout:fixed-layout">true</meta>
        <meta property="layout:overflow-scroll">true</meta>
        */
        return dto;
    }

    private String getTagText(Document doc,String tagName) {
        NodeList nodeList = doc.getElementsByTagName(tagName);
        if ( nodeList == null || nodeList.getLength() <= 0 ) return null;
        Element elm = (Element)nodeList.item(0);
        return elm.getTextContent();
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
        
        byte[] mimeData;
        try {
            mimeData = read(epubStream,entry);
        } catch (IOException e1) {
            return false;
        }
        String mimeBuf = new String(mimeData);
        if ( !mimeBuf.equals("application/epub+zip") ) return false;

        Map<String,Boolean> epubMap = new HashMap<String,Boolean>();
        epubMap.put("mimetype", true);

        byte[] containerData = null;
        String opfName = null;
        try {
            while ( (entry = epubStream.getNextEntry()) != null ) {
                String name = entry.getName();
                epubMap.put(name, true);
                if ( name.equals("META-INF/container.xml") ) {
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
        if ( !epubMap.get(opfName) ) return false;
        
        return true;
    }

    /**
     * 圧縮の関係でOPFデータが取得できなかった場合の再処理
     * @param data
     * @return
     * @throws IOException 
     */
    private byte[] getData(String dataName,byte[] data) throws IOException {
        InputStream inStream = new ByteArrayInputStream(data);
        ZipInputStream epubStream = new ZipInputStream(inStream);
        ZipEntry entry = null;
        byte[] opfData = null;
        while ( (entry = epubStream.getNextEntry()) != null ) {
            String name = entry.getName();
            if ( name.equals(dataName) ) {
                opfData = read(epubStream,entry);
                epubStream.close();
                return opfData;
            }
        }
        epubStream.close();
        return opfData;
    }
   
    /**
     * XMLファイルの取得
     * @param data
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
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

    /**
     * データの抜き出し
     * @param stream
     * @param entry
     * @return
     * @throws IOException 
     */
    private byte[] read(ZipInputStream stream,ZipEntry entry) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte [] buffer = new byte[1024];
        while ( true ) {
            int len = stream.read(buffer);
        	if(len < 0) {
        	    break;
        	}
        	bout.write(buffer, 0, len);
        }
        return bout.toByteArray();
    }

}
