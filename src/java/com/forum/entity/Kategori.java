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
@Table(name = "Kategori")
public class Kategori extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kategoriId")
    private int kategoriId;
    @Column(name = "kategoriTitle")
    private String kategoriTitle;

    @OneToMany(mappedBy = "kategori", cascade = CascadeType.ALL)
    private List<Konu> konuList;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    public List<Konu> getKonuList() {
        return konuList;
    }

    public void setKonuList(List<Konu> konuList) {
        this.konuList = konuList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(int kategoriId) {
        this.kategoriId = kategoriId;
    }

    public String getKategoriTitle() {
        return kategoriTitle;
    }

    public void setKategoriTitle(String kategoriTitle) {
        this.kategoriTitle = kategoriTitle;
    }

}
