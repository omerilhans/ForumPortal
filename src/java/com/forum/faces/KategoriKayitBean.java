package com.forum.faces;

import com.forum.entity.Kategori;
import com.forum.entity.Users;
import com.forum.repository.KategoriRepository;
import com.forum.repository.UsersRepository;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "kategoriKayitBean")
@RequestScoped
public class KategoriKayitBean {

    private Kategori kategori;
    private boolean isMod = false, isUser;
    private boolean publicPage;

    public KategoriKayitBean() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int kategoriId = 0, userId = 0;
        // Kayıt işlemleri için.
        if (request.getParameter("kategoriId") != null) {
            kategoriId = Integer.parseInt(request.getParameter("kategoriId"));
        }
        // Kayıt yetki işlemleri için.
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        UsersRepository userRepo = new UsersRepository();
        //  ------------------------------------------------- (Yetki)
        //              Gelen userId ile, Giriş yapılmadan mı veya yapılarak mı gelinmiş?
        //      Bir Admin/Mod veya User sayfasından mı gelinmiş ; 

        // Boolean değerlerle ayarlamasını yaparız.
        if (userId == -1 || userId == 0) {
            isMod = false;
            publicPage = true;
        } else {
            Users user = userRepo.find(userId);
            if (user.getDegree().getDegreeName().equals("Administrator")
                    || user.getDegree().getDegreeName().equals("Moderator")) {
                isMod = true;
            } else {
                isMod = false;
            }
            publicPage = false;
            if (!publicPage && !isMod) {
                isUser = true;
            }
        }
        // -------------------

        // Yeni kayıt olucaksa
        if (kategoriId == 0) {
            kategori = new Kategori();
            kategori.setKategoriId(0);
            kategori.setKategoriTitle("Isimsiz Kategori");
        } else { // Var olan bir kayıt ise,
            KategoriRepository kategorisRepository = new KategoriRepository();
            kategori = kategorisRepository.find(kategoriId);
            kategorisRepository.close();
        }
    }

    //Get ve Setler
    //<editor-fold defaultstate="collapsed" desc="Getter And Setter">
    public boolean isIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }

    public boolean isPublicPage() {
        return publicPage;
    }

    public void setPublicPage(boolean publicPage) {
        this.publicPage = publicPage;
    }

    public void setIsMod(boolean isMod) {
        this.isMod = isMod;
    }

    public boolean isIsMod() {
        return isMod;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
//</editor-fold>

    public void save() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int kategoriId = 0;
        if (request.getParameter("kategoriId") != null) {
            kategoriId = Integer.parseInt(request.getParameter("kategoriId"));
        }
        KategoriRepository kategorisRepository = new KategoriRepository();
        if (kategoriId == 0) {
            kategorisRepository.persist(kategori);
        } else {
            kategorisRepository.merge(kategori);
        }
        kategorisRepository.close();
    }
}
