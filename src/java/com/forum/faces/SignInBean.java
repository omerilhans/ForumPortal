package com.forum.faces;

import com.forum.entity.Users;
import com.forum.repository.UsersRepository;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "signInBean")
@SessionScoped
public class SignInBean {

    private UsersRepository usersRepository;

    private boolean signedIn;
    private Users user;
    private String nickName;
    private String password;

    public SignInBean() {
        usersRepository = new UsersRepository();
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void signOut() {
        signedIn = false;
        user = new Users();
        nickName = "";
        password = "";
    }

    public void signIn() {
        Users enteredUser = usersRepository.findByUserNickname(nickName);
        if (enteredUser == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Böyle Bir Kullanıcı Yok !"));
            signedIn = false;
        } else if (!enteredUser.getUserPassword().equals(password) || !enteredUser.getUserNickname().equals(nickName)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı Yok veya Parola Yanlış. Tekrar Deneyin"));
            signedIn = false;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Giriş Başarılı !"));
            signedIn = true;
            user = enteredUser;
        }
    }

}
