package com.dto;

import lombok.Data;

@Data

public class ReviewDTO {
    private Long userId;
    private Long locationId;
    private Long numberRating;
    private String commentContent;

}
