package com.forum.faces;

import com.forum.entity.Degree;
import com.forum.entity.Kategori;
import com.forum.entity.Konu;
import com.forum.entity.Users;
import com.forum.repository.DegreeRepository;
import com.forum.repository.KategoriRepository;
import com.forum.repository.KonuRepository;
import com.forum.repository.UsersRepository;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "humanAppBean")
@ApplicationScoped
public class HumanAppBean {

    public List<Degree> getDegreeList() {
        DegreeRepository degRepo = new DegreeRepository();
        List<Degree> degreeList = degRepo.list();
        degRepo.close();
        return degreeList;
    }

    public List<Kategori> getKategoriList() {
        KategoriRepository degRepo = new KategoriRepository();
        List<Kategori> kategoriList = degRepo.list();
        degRepo.close();
        return kategoriList;
    }

    public List<Users> getUserList() {
        UsersRepository degRepo = new UsersRepository();
        List<Users> userList = degRepo.list();
        degRepo.close();
        return userList;
    }

    public List<Konu> getKonuList() {
        KonuRepository konuRepo = new KonuRepository();
        List<Konu> konuList = konuRepo.list();
        konuRepo.close();
        return konuList;
    }
}
