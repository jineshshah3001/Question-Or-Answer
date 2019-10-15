package com.pnc.project.stackoverflow.Entity;

import org.springframework.data.annotation.Id;

public class Comment {

    @Id
    private Long id;
    private String body;
    private User user;


    public Comment(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
