package jp.dogrun.ileaflet.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-03-04 15:50:58")
/** */
public final class ContentMeta extends org.slim3.datastore.ModelMeta<jp.dogrun.ileaflet.model.Content> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Long> capacity = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Long>(this, "capacity", "capacity", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.util.Date> createAt = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.util.Date>(this, "createAt", "createAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content> detail = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content>(this, "detail", "detail");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.util.Date> editAt = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.util.Date>(this, "editAt", "editAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content> identity = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content>(this, "identity", "identity");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Boolean> publish = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Boolean>(this, "publish", "publish", java.lang.Boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Boolean> purchase = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Boolean>(this, "purchase", "purchase", java.lang.Boolean.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Integer> targetRevision = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Integer>(this, "targetRevision", "targetRevision", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content> title = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Content>(this, "title", "title");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Content, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final org.slim3.datastore.CreationDate slim3_createAtAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationDate slim3_editAtAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final ContentMeta slim3_singleton = new ContentMeta();

    /**
     * @return the singleton
     */
    public static ContentMeta get() {
       return slim3_singleton;
    }

    /** */
    public ContentMeta() {
        super("Content", jp.dogrun.ileaflet.model.Content.class);
    }

    @Override
    public jp.dogrun.ileaflet.model.Content entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.dogrun.ileaflet.model.Content model = new jp.dogrun.ileaflet.model.Content();
        model.setCapacity((java.lang.Long) entity.getProperty("capacity"));
        model.setCreateAt((java.util.Date) entity.getProperty("createAt"));
        model.setDetail((java.lang.String) entity.getProperty("detail"));
        model.setEditAt((java.util.Date) entity.getProperty("editAt"));
        model.setIdentity((java.lang.String) entity.getProperty("identity"));
        model.setKey(entity.getKey());
        model.setPublish((java.lang.Boolean) entity.getProperty("publish"));
        model.setPurchase((java.lang.Boolean) entity.getProperty("purchase"));
        model.setTargetRevision(longToInteger((java.lang.Long) entity.getProperty("targetRevision")));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("capacity", m.getCapacity());
        entity.setProperty("createAt", m.getCreateAt());
        entity.setProperty("detail", m.getDetail());
        entity.setProperty("editAt", m.getEditAt());
        entity.setProperty("identity", m.getIdentity());
        entity.setProperty("publish", m.getPublish());
        entity.setProperty("purchase", m.getPurchase());
        entity.setProperty("targetRevision", m.getTargetRevision());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        m.setCreateAt(slim3_createAtAttributeListener.prePut(m.getCreateAt()));
        m.setEditAt(slim3_editAtAttributeListener.prePut(m.getEditAt()));
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
        jp.dogrun.ileaflet.model.Content m = (jp.dogrun.ileaflet.model.Content) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCapacity() != null){
            writer.setNextPropertyName("capacity");
            encoder0.encode(writer, m.getCapacity());
        }
        if(m.getCreateAt() != null){
            writer.setNextPropertyName("createAt");
            encoder0.encode(writer, m.getCreateAt());
        }
        if(m.getDetail() != null){
            writer.setNextPropertyName("detail");
            encoder0.encode(writer, m.getDetail());
        }
        if(m.getEditAt() != null){
            writer.setNextPropertyName("editAt");
            encoder0.encode(writer, m.getEditAt());
        }
        if(m.getIdentity() != null){
            writer.setNextPropertyName("identity");
            encoder0.encode(writer, m.getIdentity());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getPublish() != null){
            writer.setNextPropertyName("publish");
            encoder0.encode(writer, m.getPublish());
        }
        if(m.getPurchase() != null){
            writer.setNextPropertyName("purchase");
            encoder0.encode(writer, m.getPurchase());
        }
        if(m.getTargetRevision() != null){
            writer.setNextPropertyName("targetRevision");
            encoder0.encode(writer, m.getTargetRevision());
        }
        if(m.getTitle() != null){
            writer.setNextPropertyName("title");
            encoder0.encode(writer, m.getTitle());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.dogrun.ileaflet.model.Content jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.dogrun.ileaflet.model.Content m = new jp.dogrun.ileaflet.model.Content();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("capacity");
        m.setCapacity(decoder0.decode(reader, m.getCapacity()));
        reader = rootReader.newObjectReader("createAt");
        m.setCreateAt(decoder0.decode(reader, m.getCreateAt()));
        reader = rootReader.newObjectReader("detail");
        m.setDetail(decoder0.decode(reader, m.getDetail()));
        reader = rootReader.newObjectReader("editAt");
        m.setEditAt(decoder0.decode(reader, m.getEditAt()));
        reader = rootReader.newObjectReader("identity");
        m.setIdentity(decoder0.decode(reader, m.getIdentity()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("publish");
        m.setPublish(decoder0.decode(reader, m.getPublish()));
        reader = rootReader.newObjectReader("purchase");
        m.setPurchase(decoder0.decode(reader, m.getPurchase()));
        reader = rootReader.newObjectReader("targetRevision");
        m.setTargetRevision(decoder0.decode(reader, m.getTargetRevision()));
        reader = rootReader.newObjectReader("title");
        m.setTitle(decoder0.decode(reader, m.getTitle()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}