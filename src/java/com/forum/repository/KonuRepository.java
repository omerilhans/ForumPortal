package com.forum.repository;

import com.forum.database.BaseRepository;
import com.forum.entity.Konu;
import com.forum.entity.Users;
import java.util.List;
import javax.persistence.Query;

public class KonuRepository extends BaseRepository<Konu> {

    public KonuRepository() {
        super(Konu.class);
    }

    public List<Konu> listKonuByKategoriId(int kategoriId) {
        String jpq = "select konu from Konu as konu where konu.kategori.kategoriId = :kategoriId";
        Query query = entityManager.createQuery(jpq);
        query.setParameter("kategoriId", kategoriId);
        return query.getResultList();
    }
}
