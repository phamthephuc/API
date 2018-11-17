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

}
