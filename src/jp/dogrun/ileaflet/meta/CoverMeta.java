package jp.dogrun.ileaflet.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-03-03 13:07:14")
/** */
public final class CoverMeta extends org.slim3.datastore.ModelMeta<jp.dogrun.ileaflet.model.Cover> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Cover> contentId = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Cover>(this, "contentId", "contentId");

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<jp.dogrun.ileaflet.model.Cover, byte[]> data = new org.slim3.datastore.CoreUnindexedAttributeMeta<jp.dogrun.ileaflet.model.Cover, byte[]>(this, "data", "data", byte[].class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Cover> fileName = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Cover>(this, "fileName", "fileName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Cover, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Cover, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Cover, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Cover, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final CoverMeta slim3_singleton = new CoverMeta();

    /**
     * @return the singleton
     */
    public static CoverMeta get() {
       return slim3_singleton;
    }

    /** */
    public CoverMeta() {
        super("Cover", jp.dogrun.ileaflet.model.Cover.class);
    }

    @Override
    public jp.dogrun.ileaflet.model.Cover entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.dogrun.ileaflet.model.Cover model = new jp.dogrun.ileaflet.model.Cover();
        model.setContentId((java.lang.String) entity.getProperty("contentId"));
        model.setData(blobToBytes((com.google.appengine.api.datastore.Blob) entity.getProperty("data")));
        model.setFileName((java.lang.String) entity.getProperty("fileName"));
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("contentId", m.getContentId());
        entity.setUnindexedProperty("data", bytesToBlob(m.getData()));
        entity.setProperty("fileName", m.getFileName());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        jp.dogrun.ileaflet.model.Cover m = (jp.dogrun.ileaflet.model.Cover) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getContentId() != null){
            writer.setNextPropertyName("contentId");
            encoder0.encode(writer, m.getContentId());
        }
        if(m.getData() != null){
            writer.setNextPropertyName("data");
            encoder0.encode(writer, new com.google.appengine.api.datastore.ShortBlob(m.getData()));
        }
        if(m.getFileName() != null){
            writer.setNextPropertyName("fileName");
            encoder0.encode(writer, m.getFileName());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.dogrun.ileaflet.model.Cover jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.dogrun.ileaflet.model.Cover m = new jp.dogrun.ileaflet.model.Cover();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("contentId");
        m.setContentId(decoder0.decode(reader, m.getContentId()));
        reader = rootReader.newObjectReader("data");
        if(m.getData() != null){
            m.setData(decoder0.decode(reader, new com.google.appengine.api.datastore.ShortBlob(m.getData())).getBytes());
        } else{
            com.google.appengine.api.datastore.ShortBlob v = decoder0.decode(reader, (com.google.appengine.api.datastore.ShortBlob)null);
            if(v != null){
                m.setData(v.getBytes());
            } else{
                m.setData(null);
            }
        }
        reader = rootReader.newObjectReader("fileName");
        m.setFileName(decoder0.decode(reader, m.getFileName()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}