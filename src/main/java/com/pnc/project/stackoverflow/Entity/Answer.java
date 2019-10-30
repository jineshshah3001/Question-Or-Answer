package com.pnc.project.stackoverflow.Entity;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class Answer {


    @Id
    private Long id;
    private String body;
    private List<Comment> comments;
    private Date dateCreated;
    private String answeredBy;
    private String editedBy;



    public Answer(){

    }

    public String getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(String answeredBy) {
        this.answeredBy = answeredBy;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "body='" + body + '\'' +
                ", comments=" + comments +
                '}';
    }
}
