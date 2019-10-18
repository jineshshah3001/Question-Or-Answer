package com.pnc.project.stackoverflow.Entity;

import java.io.Serializable;

public class Login implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String email;
    private String password;

    public Login(){

    }

    public Login(String email , String password){
        this.setEmail(email);
        this.setPassword(password);
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
