package com.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "login_details")
public class Login {

    @Id
    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column (name = "isLogin", nullable = false, length = 50)
    private boolean isLogin;

    @Column (name = "login_time", nullable = false, length = 50)
    private String loginTime;

    @Column (name = "logout_time", nullable = true, length = 50)
    private String logoutTime;

    public Login(String userName, boolean isLogin, String loginTime, String logoutTime) {
        this.userName = userName;
        this.isLogin = isLogin;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }
}
