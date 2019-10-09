package com.pnc.project.stackoverflow.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Question")
public class Question {

    @Id
    private String id;
    private String title;
    private String body;
    private List<String> tag;
    private List<Comment> comments;
    private List<Answer> answers;
    private long numberOfAnswers;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateAsked;

    @Transient
    public static final String SEQUENCE_NAME = "sequence";


    public Date getDateAsked() {
        return dateAsked;
    }

    public void setDateAsked(Date dateAsked) {
        this.dateAsked = dateAsked;
    }

    public long getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(long numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
