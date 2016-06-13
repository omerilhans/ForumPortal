package com.forum.repository;

import com.forum.database.BaseRepository;
import com.forum.entity.Yorum;
import java.util.List;
import javax.persistence.Query;

public class YorumRepository extends BaseRepository<Yorum> {

    public YorumRepository() {
        super(Yorum.class);
    }

    public List<Yorum> listYorumByUserId(int userId) {
        String jpq = "select yorum from Yorum as yorum where yorum.user.userId = :userId";
        Query query = entityManager.createQuery(jpq);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Yorum> listYorumByKonuId(int konuId) {
        String jpq = "select yorum from Yorum as yorum where yorum.konu.konuId = :konuId";
        Query query = entityManager.createQuery(jpq);
        query.setParameter("konuId", konuId);
        return query.getResultList();
    }

    public List<Yorum> listYorumByUserAndKonuId(int userId, int konuId) {
        String jpq = "select yorum from Yorum as yorum where yorum.konu.konuId = :konuId and yorum.user.userId = :userId";
        Query query = entityManager.createQuery(jpq);
        query.setParameter("konuId", konuId);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
