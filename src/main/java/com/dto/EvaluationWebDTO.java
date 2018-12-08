package com.dto;

import lombok.Data;

import java.util.Date;
@Data

public class EvaluationWebDTO {

    private Long id;
    private String nameUser;
    private Date dateReview;
    private Long score;
    private String content;

    public EvaluationWebDTO(String nameUser, String content) {
        this.nameUser = nameUser;
        this.content = content;
    }

    public EvaluationWebDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
