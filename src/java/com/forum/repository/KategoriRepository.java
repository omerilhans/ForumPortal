package com.forum.repository;

import com.forum.database.BaseRepository;
import com.forum.entity.Kategori;

public class KategoriRepository extends BaseRepository<Kategori> {

    public KategoriRepository() {
        super(Kategori.class);
    }

}
