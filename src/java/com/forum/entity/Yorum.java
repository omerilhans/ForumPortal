package com.forum.entity;

import com.forum.database.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Yorum")
public class Yorum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yorumId")
    private int yorumId;
    @Column(name = "yorumContent")
    private String yorumContent;

    @ManyToOne
    @JoinColumn(name="userId")
    private Users user;
    
    @ManyToOne 
    @JoinColumn(name="konuId")
    private Konu konu;

    public Konu getKonu() {
        return konu;
    }

    public void setKonu(Konu konu) {
        this.konu = konu;
    }
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getYorumId() {
        return yorumId;
    }

    public void setYorumId(int yorumId) {
        this.yorumId = yorumId;
    }

    public String getYorumContent() {
        return yorumContent;
    }

    public void setYorumContent(String yorumContent) {
        this.yorumContent = yorumContent;
    }

}
