package jp.dogrun.ileaflet.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EpubDto {

    private String title;
    private String creator;
    private String langage;
    private Map<String,String> resourceMap = new HashMap<String,String>();
    private List<String> pageList = new ArrayList<String>();

    private byte[] cover;
    private String coverName;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getLangage() {
        return langage;
    }
    public void setLangage(String langage) {
        this.langage = langage;
    }
    public byte[] getCover() {
        return cover;
    }
    public void setCover(byte[] cover) {
        this.cover = cover;
    }
    public List<String> getPageList() {
        return pageList;
    }
    public void setPageList(List<String> pageList) {
        this.pageList = pageList;
    }
    public Map<String,String> getResourceMap() {
        return resourceMap;
    }
    public void setResourceMap(Map<String,String> resourceMap) {
        this.resourceMap = resourceMap;
    }
    public void putResource(String id, String href) {
        resourceMap.put(id, href);
    }
    public void addPage(String id) {
        pageList.add(id);
    }
    public String getCoverName() {
        return coverName;
    }
    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }
}
