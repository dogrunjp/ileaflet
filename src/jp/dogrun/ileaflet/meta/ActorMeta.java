package jp.dogrun.ileaflet.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-01-21 19:19:00")
/** */
public final class ActorMeta extends org.slim3.datastore.ModelMeta<jp.dogrun.ileaflet.model.Actor> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.util.Date> createAt = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.util.Date>(this, "createAt", "createAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.util.Date> editAt = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.util.Date>(this, "editAt", "editAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor> email = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor>(this, "email", "email");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor> identity = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor>(this, "identity", "identity");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor> keyword = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor>(this, "keyword", "keyword");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor> name = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor>(this, "name", "name");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor> password = new org.slim3.datastore.StringAttributeMeta<jp.dogrun.ileaflet.model.Actor>(this, "password", "password");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.lang.Integer> purchase = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.lang.Integer>(this, "purchase", "purchase", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<jp.dogrun.ileaflet.model.Actor, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final org.slim3.datastore.CreationDate slim3_createAtAttributeListener = new org.slim3.datastore.CreationDate();

    private static final org.slim3.datastore.ModificationDate slim3_editAtAttributeListener = new org.slim3.datastore.ModificationDate();

    private static final ActorMeta slim3_singleton = new ActorMeta();

    /**
     * @return the singleton
     */
    public static ActorMeta get() {
       return slim3_singleton;
    }

    /** */
    public ActorMeta() {
        super("Actor", jp.dogrun.ileaflet.model.Actor.class);
    }

    @Override
    public jp.dogrun.ileaflet.model.Actor entityToModel(com.google.appengine.api.datastore.Entity entity) {
        jp.dogrun.ileaflet.model.Actor model = new jp.dogrun.ileaflet.model.Actor();
        model.setCreateAt((java.util.Date) entity.getProperty("createAt"));
        model.setEditAt((java.util.Date) entity.getProperty("editAt"));
        model.setEmail((java.lang.String) entity.getProperty("email"));
        model.setIdentity((java.lang.String) entity.getProperty("identity"));
        model.setKey(entity.getKey());
        model.setKeyword((java.lang.String) entity.getProperty("keyword"));
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setPassword((java.lang.String) entity.getProperty("password"));
        model.setPurchase(longToInteger((java.lang.Long) entity.getProperty("purchase")));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createAt", m.getCreateAt());
        entity.setProperty("editAt", m.getEditAt());
        entity.setProperty("email", m.getEmail());
        entity.setProperty("identity", m.getIdentity());
        entity.setProperty("keyword", m.getKeyword());
        entity.setProperty("name", m.getName());
        entity.setProperty("password", m.getPassword());
        entity.setProperty("purchase", m.getPurchase());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
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
        jp.dogrun.ileaflet.model.Actor m = (jp.dogrun.ileaflet.model.Actor) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCreateAt() != null){
            writer.setNextPropertyName("createAt");
            encoder0.encode(writer, m.getCreateAt());
        }
        if(m.getEditAt() != null){
            writer.setNextPropertyName("editAt");
            encoder0.encode(writer, m.getEditAt());
        }
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getIdentity() != null){
            writer.setNextPropertyName("identity");
            encoder0.encode(writer, m.getIdentity());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getKeyword() != null){
            writer.setNextPropertyName("keyword");
            encoder0.encode(writer, m.getKeyword());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getPassword() != null){
            writer.setNextPropertyName("password");
            encoder0.encode(writer, m.getPassword());
        }
        if(m.getPurchase() != null){
            writer.setNextPropertyName("purchase");
            encoder0.encode(writer, m.getPurchase());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected jp.dogrun.ileaflet.model.Actor jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        jp.dogrun.ileaflet.model.Actor m = new jp.dogrun.ileaflet.model.Actor();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("createAt");
        m.setCreateAt(decoder0.decode(reader, m.getCreateAt()));
        reader = rootReader.newObjectReader("editAt");
        m.setEditAt(decoder0.decode(reader, m.getEditAt()));
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("identity");
        m.setIdentity(decoder0.decode(reader, m.getIdentity()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("keyword");
        m.setKeyword(decoder0.decode(reader, m.getKeyword()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("password");
        m.setPassword(decoder0.decode(reader, m.getPassword()));
        reader = rootReader.newObjectReader("purchase");
        m.setPurchase(decoder0.decode(reader, m.getPurchase()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}