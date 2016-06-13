package com.forum.faces;

import com.forum.entity.Users;
import com.forum.repository.UsersRepository;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.enterprise.context.RequestScoped;

@ManagedBean(name = "userShowBean")
@RequestScoped
public class UserShowBean {

    private Users user;
    private boolean degreeAdmin;
    private boolean degreeMod;

    public boolean isDegreeMod() {
        return degreeMod;
    }

    public void setDegreeMod(boolean degreeMod) {
        this.degreeMod = degreeMod;
    }

    public boolean isDegreeAdmin() {
        return degreeAdmin;
    }

    public void setDegreeAdmin(boolean degreeAdmin) {
        this.degreeAdmin = degreeAdmin;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UserShowBean() {
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int userId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.parseInt(request.getParameter("userId"));
        }
        UsersRepository usersRepository = new UsersRepository();
        if(userId != -1 && userId != 0)
        {
            user = usersRepository.find(userId);
        } else {
            
        }
        if (user.getDegree().getDegreeName().equals("Administrator")) {
            degreeMod = true;
        } else if (user.getDegree().getDegreeName().equals("Moderator")) {
            degreeAdmin = true;
        }
        usersRepository.close();
    }

}
