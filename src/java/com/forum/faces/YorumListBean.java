package com.forum.faces;

import com.forum.entity.Konu;
import com.forum.entity.Users;
import com.forum.entity.Yorum;
import com.forum.repository.KonuRepository;
import com.forum.repository.UsersRepository;
import com.forum.repository.YorumRepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "yorumListBean")
@RequestScoped
public class YorumListBean {

    private KonuRepository konuRepo;
    private YorumRepository yorumRepo;
    private UsersRepository userRepo;

    private List<Yorum> yorumList;
    private Konu konu;
    private Users user;
    private boolean publictenYorumIstek;

    public YorumListBean() {
        yorumList = new ArrayList<>();
        konu = new Konu();
        user = new Users();

        yorumRepo = new YorumRepository();
        konuRepo = new KonuRepository();
        userRepo = new UsersRepository();

        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int konuId = 0, userId = 0;
        boolean tumYorumlarIstek = false;
        if (request.getParameter("konuId") != null) {
            konuId = Integer.parseInt(request.getParameter("konuId"));
        }
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        if (request.getParameter("tumYorumlarIstek") != null) {
            tumYorumlarIstek = Boolean.parseBoolean(request.getParameter("tumYorumlarIstek"));
        }
        if (request.getParameter("publictenYorumIstek") != null) {
            publictenYorumIstek = Boolean.parseBoolean(request.getParameter("publictenYorumIstek"));
        }

        // Istek HomePage'den yapılıyorsa,
        if (publictenYorumIstek) {
            if (konuId == 0) {
                yorumList = yorumRepo.list();
            } else {
                yorumList = yorumRepo.listYorumByKonuId(konuId);
            }
        } else // Istek Login'den yapılıyorsa,
        {
            if (konuId == 0) {
                yorumList = yorumRepo.listYorumByUserId(userId);
            } else if (konuId != 0) {
                yorumList = yorumRepo.listYorumByUserAndKonuId(userId, konuId);
            }
            if (tumYorumlarIstek) {
                yorumList = yorumRepo.list();
            }
        }

        // ----------------------
        konuRepo.close();
        yorumRepo.close();
    }

    //<editor-fold defaultstate="collapsed" desc="Setter-Getter">
    public void setKonuRepo(KonuRepository konuRepo) {
        this.konuRepo = konuRepo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public boolean isPublictenYorumIstek() {
        return publictenYorumIstek;
    }

    public void setPublictenYorumIstek(boolean publictenYorumIstek) {
        this.publictenYorumIstek = publictenYorumIstek;
    }

    public void setKonu(Konu konu) {
        this.konu = konu;
    }

    public Konu getKonu() {
        return konu;
    }

    public List<Yorum> getYorumList() {
        return yorumList;
    }

    public void setYorumList(List<Yorum> yorumList) {
        this.yorumList = yorumList;
    }
//</editor-fold>

    public void remove() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int yorumId = 0;
        if (request.getParameter("yorumId") != null) {
            yorumId = Integer.parseInt(request.getParameter("yorumId"));
        }
        if (yorumId != 0) {
            yorumRepo.remove(yorumId);
        }
        yorumList = yorumRepo.list();
        yorumRepo.close();
    }

}
