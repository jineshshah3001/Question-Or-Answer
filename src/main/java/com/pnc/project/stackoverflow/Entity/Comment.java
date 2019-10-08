package com.pnc.project.stackoverflow.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.math.BigInteger;

public class Comment {

    @Transient
    public static final String SEQUENCE_NAME = "comment_sequence";

    @Id
    private Long id;
    private String body;


    public Comment(){

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
