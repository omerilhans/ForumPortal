package com.forum.faces;

import com.forum.entity.Kategori;
import com.forum.entity.Konu;
import com.forum.repository.KonuRepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "konuListBean")
@RequestScoped
public class KonuListBean {

    private List<Konu> konuList;
    private Kategori kategori;
    private int selectedKategoriId;

    public KonuListBean() {
        konuList = new ArrayList<>();
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int kategoriId = 0;
        if (request.getParameter("kategoriId") != null) {
            kategoriId = Integer.parseInt(request.getParameter("kategoriId"));
        }
   
        KonuRepository konuRepository = new KonuRepository();
        if (kategoriId != 0) {
            konuList = konuRepository.listKonuByKategoriId(kategoriId);
        } else {
            konuList = konuRepository.list();
        }
        konuRepository.close();
    }

    //<editor-fold defaultstate="collapsed" desc="Setter-Getter">

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public int getSelectedKategoriId() {
        return selectedKategoriId;
    }

    public void setSelectedKategoriId(int selectedKategoriId) {
        this.selectedKategoriId = selectedKategoriId;
    }

    public List<Konu> getKonuList() {
        return konuList;
    }

    public void setKonuList(List<Konu> konuList) {
        this.konuList = konuList;
    }
//</editor-fold>

    public void remove() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int konuId = 0;
        if (request.getParameter("konuId") != null) {
            konuId = Integer.parseInt(request.getParameter("konuId"));
        }
        KonuRepository konuRepository = new KonuRepository();
        if (konuId != 0) {
            konuRepository.remove(konuId);
        }
        konuList = konuRepository.list();
        konuRepository.close();
    }

    public void selectKonuList() {
        KonuRepository konuRepo = new KonuRepository();
        if (selectedKategoriId != 0) {
            konuList = konuRepo.listKonuByKategoriId(selectedKategoriId);
        } else {
            konuList = konuRepo.list();
        }
        konuRepo.close();
    }

    public void filtre() {
        selectKonuList();
    }
}
