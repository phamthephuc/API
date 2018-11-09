package com.dto;

import java.util.Date;

public class EvaluationDTO {
    private String nameUser;
    private Date dateReview;
    private Long score;
    private String content;

    public EvaluationDTO(String nameUser, String content) {
        this.nameUser = nameUser;
        this.content = content;
    }

    public EvaluationDTO() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public Long getScore() {
        return score;
    }
}

