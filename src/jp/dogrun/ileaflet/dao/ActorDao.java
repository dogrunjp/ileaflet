package jp.dogrun.ileaflet.dao;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import jp.dogrun.ileaflet.meta.ActorMeta;
import jp.dogrun.ileaflet.model.Actor;

public class ActorDao extends DaoBase<Actor>{

    public Actor findById(String identity) {
        ActorMeta meta = ActorMeta.get();
        return Datastore.query(Actor.class).
                filter(
                    meta.identity.equal(identity)
                ).asSingle();
    }
    
    public Actor findByMail(String email) {
        ActorMeta meta = ActorMeta.get();
        return Datastore.query(Actor.class).
                filter(
                    meta.email.equal(email)
                ).asSingle();
    }

    public Actor findByKeyword(String keyword) {
        ActorMeta meta = ActorMeta.get();
        return Datastore.query(Actor.class).
                filter(
                    meta.keyword.equal(keyword)
                ).asSingle();
    }
            

}
