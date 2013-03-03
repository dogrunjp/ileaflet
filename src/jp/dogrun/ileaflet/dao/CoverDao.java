package jp.dogrun.ileaflet.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import jp.dogrun.ileaflet.meta.CoverMeta;
import jp.dogrun.ileaflet.model.Cover;

public class CoverDao extends DaoBase<Cover>{

    public Cover findByContentId(String id) {
        CoverMeta meta = CoverMeta.get();
        return Datastore.query(Cover.class).filter(
            meta.contentId.equal(id)
            ).asSingle();
    }
}
