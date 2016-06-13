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
@Table(name = "Users")
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userSurname")
    private String userSurname;
    @Column(name = "userNickname")
    private String userNickname;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userSex")
    private char userSex;
    @Column(name = "userHomeland")
    private String userHomeland;

    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degree;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Kategori> kategoriList;

    public Users() {
    }

    public Users(int userId, String userName, String userSurname, String userNickname, String userPassword, String userEmail, char userSex, String userHomeland) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userSex = userSex;
        this.userHomeland = userHomeland;
    }

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public char getUserSex() {
        return userSex;
    }

    public void setUserSex(char userSex) {
        this.userSex = userSex;
    }

    public String getUserHomeland() {
        return userHomeland;
    }

    public void setUserHomeland(String userHomeland) {
        this.userHomeland = userHomeland;
    }

}
