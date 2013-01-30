package jp.dogrun.ileaflet.dao;

import java.util.List;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

import jp.dogrun.ileaflet.meta.ContentMeta;
import jp.dogrun.ileaflet.model.Content;

public class ContentDao extends DaoBase<Content>{

    public List<Content> findByIdentity(String identity) {
        ContentMeta meta = ContentMeta.get();
        return Datastore.query(Content.class).filter(
            meta.identity.equal(identity)
            ).sort(meta.createAt.desc).asList();
    }

    public Content findById(String id) {
        Key key = Datastore.createKey(Content.class, Long.valueOf(id));
        ContentMeta meta = ContentMeta.get();
        return Datastore.query(Content.class).filter(
            meta.key.equal(key)
            ).asSingle();
    }

}
