package com.forum.faces;

import com.forum.entity.Konu;
import com.forum.entity.Yorum;
import com.forum.repository.KonuRepository;
import com.forum.repository.YorumRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

// Yorum Kayıt-List Eksik Kaldı.
@ManagedBean(name = "yorumKayitBean")
@RequestScoped
public class YorumKayitBean {

    private Yorum yorum;
    private List<Konu> konuList;
    private int selectedKonuId;
    private boolean publictenYorumKayitIstek;

    // CONS.
    public YorumKayitBean() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int yorumId = 0;
        // Kayıt işlemleri için.
        if (request.getParameter("yorumId") != null) {
            yorumId = Integer.parseInt(request.getParameter("yorumId"));
        }
        
        // Yeni kayıt'zahn
        if (yorumId == -1) {
            publictenYorumKayitIstek = true;

        } else if (yorumId == 0) {
            yorum = new Yorum();
            yorum.setYorumId(0);
            yorum.setYorumContent("Isimsiz Yorum");
        } else { // Var olan bir kayıt'siaohn,
            YorumRepository yorumRepository = new YorumRepository();
            yorum = yorumRepository.find(yorumId);
            yorumRepository.close();
        }
    }

    // Get-Set.
    //<editor-fold defaultstate="collapsed" desc="Getter-Setter">

    public Yorum getYorum() {
        return yorum;
    }

    public void setYorum(Yorum yorum) {
        this.yorum = yorum;
    }

    public List<Konu> getKonuList() {
        return konuList;
    }

    public void setKonuList(List<Konu> konuList) {
        this.konuList = konuList;
    }

    public int getSelectedKonuId() {
        return selectedKonuId;
    }

    public void setSelectedKonuId(int selectedKonuId) {
        this.selectedKonuId = selectedKonuId;
    }

    public boolean isPublictenYorumKayitIstek() {
        return publictenYorumKayitIstek;
    }

    public void setPublictenYorumKayitIstek(boolean publictenYorumKayitIstek) {
        this.publictenYorumKayitIstek = publictenYorumKayitIstek;
    }

//</editor-fold>

    /*          SAVE          */
    public void save() {
        KonuRepository konuRepo = new KonuRepository();
        Konu yorumunKonusu = konuRepo.find(selectedKonuId);
        yorum.setKonu(yorumunKonusu);
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int yorumId = 0;
        if (request.getParameter("yorumId") != null) {
            yorumId = Integer.parseInt(request.getParameter("yorumId"));
        }
        YorumRepository yorumRepository = new YorumRepository();
        if (yorumId == 0) {
            yorumRepository.persist(yorum);
        } else {
            yorumRepository.merge(yorum);
        }
        konuRepo.close();
        yorumRepository.close();
    }

}
