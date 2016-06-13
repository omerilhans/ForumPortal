package com.forum.entity;

import com.forum.database.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Konu")
public class Konu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "konuId")
    private int konuId;
    @Column(name = "konuTitle")
    private String konuTitle;

    @ManyToOne
    @JoinColumn(name = "kategoriId")
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @OneToMany(mappedBy = "konu", cascade = CascadeType.ALL)
    private List<Yorum> yorumList;

    public List<Yorum> getYorumList() {
        return yorumList;
    }

    public void setYorumList(List<Yorum> yorumList) {
        this.yorumList = yorumList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public int getKonuId() {
        return konuId;
    }

    public void setKonuId(int konuId) {
        this.konuId = konuId;
    }

    public String getKonuTitle() {
        return konuTitle;
    }

    public void setKonuTitle(String konuTitle) {
        this.konuTitle = konuTitle;
    }

}
