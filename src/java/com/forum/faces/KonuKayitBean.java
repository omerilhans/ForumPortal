package com.forum.faces;

import com.forum.entity.Kategori;
import com.forum.entity.Konu;
import com.forum.repository.KategoriRepository;
import com.forum.repository.KonuRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "konuKayitBean")
@RequestScoped
public class KonuKayitBean {

    private Konu konu;
    private List<Kategori> kategoriList;
    private int selectedKategoriId;

    // CONS.
    public KonuKayitBean() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int konuId = 0;
        // Kayıt işlemleri için.
        if (request.getParameter("konuId") != null) {
            konuId = Integer.parseInt(request.getParameter("konuId"));
        }

        // Yeni kayıt ise,
        if (konuId == 0) {
            konu = new Konu();
            konu.setKonuId(0);
            konu.setKonuTitle("Isimsiz Konu");
        } else { // Var olan bir kayıt ise,
            KonuRepository konusRepository = new KonuRepository();
            konu = konusRepository.find(konuId);
            konusRepository.close();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter-Setter">

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }

    public int getSelectedKategoriId() {
        return selectedKategoriId;
    }

    public void setSelectedKategoriId(int selectedKategoriId) {
        this.selectedKategoriId = selectedKategoriId;
    }

    public Konu getKonu() {
        return konu;
    }

    public void setKonu(Konu konu) {
        this.konu = konu;
    }
//</editor-fold>

    // SAVE
    public void save() {
        KategoriRepository katRep = new KategoriRepository();
        Kategori konununKategorisi = katRep.find(selectedKategoriId);
        konu.setKategori(konununKategorisi);
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int konuId = 0;
        if (request.getParameter("konuId") != null) {
            konuId = Integer.parseInt(request.getParameter("konuId"));
        }
        KonuRepository konuRepository = new KonuRepository();
        if (konuId == 0) {
            konuRepository.persist(konu);
        } else {
            konuRepository.merge(konu);
        }
        katRep.close();
        konuRepository.close();
    }

}
