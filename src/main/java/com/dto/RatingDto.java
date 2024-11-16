
package com.dto;

import java.time.LocalDateTime;

public class RatingDto {

    private Long userId;
    private Long movieId;
    private int rating;
    private LocalDateTime ratingDate;

    // Constructors
    public RatingDto() {
    }

    public RatingDto(Long userId, Long movieId, int rating, LocalDateTime ratingDate) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.ratingDate = ratingDate;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatingDate(){
        return ratingDate;
    }
    public void setRatingDate(LocalDateTime ratingDate){
        this.ratingDate = ratingDate;
    }
}
