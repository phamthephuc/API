package com.dto;

import lombok.Data;

@Data

public class ReviewDTO {
    private Long userId;
    private Long locationId;
    private Long numberRating;
    private String commentContent;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getNumberRating() {
        return numberRating;
    }

    public void setNumberRating(Long numberRating) {
        this.numberRating = numberRating;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
