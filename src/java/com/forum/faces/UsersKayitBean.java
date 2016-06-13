package com.forum.faces;

import com.forum.entity.Degree;
import com.forum.entity.Users;
import com.forum.repository.DegreeRepository;
import com.forum.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "usersKayitBean")
@RequestScoped
public class UsersKayitBean {

    private Users user;
    private List<Degree> degreeList;
    private int selectedDegreeId;
    private boolean userAdminDegree;

    public boolean isUserAdminDegree() {
        return userAdminDegree;
    }

    public void setUserAdminDegree(boolean userAdminDegree) {
        this.userAdminDegree = userAdminDegree;
    }

    public int getSelectedDegreeId() {
        return selectedDegreeId;
    }

    public void setSelectedDegreeId(int selectedDegreeId) {
        this.selectedDegreeId = selectedDegreeId;
    }

    public List<Degree> getDegreeList() {
        return degreeList;
    }

    public void setDegreeList(List<Degree> degreeList) {
        this.degreeList = degreeList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UsersKayitBean() {
        // degreeList'i instance'ni oluştur.
        degreeList = new ArrayList<>();

        UsersRepository usersRepository = new UsersRepository();
        DegreeRepository degRepo = new DegreeRepository();

        // Geçici Degree Listesi Oluştur.
        List<Degree> tmpDegList = degRepo.list();

        // ------
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int userId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        // ------

        boolean yetkiliMi = false;
        if (userId != -1 && userId != 0) {
            Users userTmp = usersRepository.find(userId);
            yetkiliMi = userTmp.getDegree().getDegreeName().equals("Administrator")
                    || userTmp.getDegree().getDegreeName().equals("Moderator");
        }
        if (userId == -1 || yetkiliMi) {
            // Eğer userId=0 ise, Default değerler göster.
            user = new Users();
            user.setUserId(0);
            user.setUserName("Adınız ?");
            user.setUserSurname("Soyadınız ?");
            user.setUserNickname("Bir Nickname girin");
            user.setUserPassword("Password girin");
            user.setUserEmail("E-Mail Girin");
            user.setUserSex('E');
            user.setUserHomeland("Nerelisiniz ?");
            if (yetkiliMi) {
                userAdminDegree = true;
                System.out.println("userAdminDegree : " + userAdminDegree);
            }
        }
        usersRepository.close();
    }

    public void save() {
        DegreeRepository degRepo = new DegreeRepository();
        Degree deg = degRepo.find(selectedDegreeId);
        user.setDegree(deg);
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int userId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        UsersRepository usersRepository = new UsersRepository();
        if (userId != 0) {
            usersRepository.merge(user);
        } else {
            usersRepository.persist(user);
        }
        degRepo.close();
        usersRepository.close();
    }

}
