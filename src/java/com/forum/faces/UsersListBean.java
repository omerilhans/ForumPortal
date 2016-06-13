package com.forum.faces;

import com.forum.entity.Degree;
import com.forum.entity.Users;
import com.forum.repository.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "usersListBean")
@RequestScoped
public class UsersListBean {

    private List<Users> userList;
    private int selectedDegreeId;
    private Degree degree;

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getSelectedDegreeId() {
        return selectedDegreeId;
    }

    public void setSelectedDegreeId(int selectedDegreeId) {
        this.selectedDegreeId = selectedDegreeId;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

    public UsersListBean() {
        userList = new ArrayList<>();
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        selectUserList();
    }

    public void remove() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        int userId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        UsersRepository usersRepository = new UsersRepository();
        if (userId != 0) {
            usersRepository.remove(userId);
        }
        userList = usersRepository.list();
        usersRepository.close();
    }

    public void selectUserList() {
        UsersRepository userRepo = new UsersRepository();
        if (selectedDegreeId != 0) {
            userList = userRepo.listUsersByDegreeId(selectedDegreeId);
        } else {
            userList = userRepo.list();
        }
        userRepo.close();
    }

    public void filtre() {
        selectUserList();
    }
}
