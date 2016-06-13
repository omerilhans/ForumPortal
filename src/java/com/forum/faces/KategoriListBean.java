package com.forum.faces;

import com.forum.entity.Kategori;
import com.forum.repository.KategoriRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "kategoriListBean")
@RequestScoped
public class KategoriListBean {

    private List<Kategori> kategoriList;
    private int selectedUserId;

    public KategoriListBean() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int userId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        if (userId != 0) {
            selectedUserId = userId;
        }

        KategoriRepository kategoriRepo = new KategoriRepository();
        kategoriList = kategoriRepo.list();
        kategoriRepo.close();
    }

    // Get-Set
    //<editor-fold defaultstate="collapsed" desc="Get-Set">
    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public int getSelectedUserId() {
        return selectedUserId;
    }

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }
//</editor-fold>

    public void remove() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int kategoriId = 0;
        if (request.getParameter("kategoriId") != null) {
            kategoriId = Integer.parseInt(request.getParameter("kategoriId"));
        }
        KategoriRepository kategorisRepository = new KategoriRepository();
        if (kategoriId != 0) {
            kategorisRepository.remove(kategoriId);
        }
        kategoriList = kategorisRepository.list();
        kategorisRepository.close();
    }

}
