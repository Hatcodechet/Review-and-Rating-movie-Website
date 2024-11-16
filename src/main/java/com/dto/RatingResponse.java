package com.dto;

import java.time.LocalDateTime;

import com.model.Movie;
import com.model.User;

public class RatingResponse {

    private Long id;
    private User user;
    private Movie movie;
    private int rating;
    private LocalDateTime ratingDate;
    // Constructors
    public RatingResponse(Long id, User user, Movie movie, int rating, LocalDateTime ratingDate) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.ratingDate = ratingDate;  // Cập nhật constructor
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDateTime ratingDate) {
        this.ratingDate = ratingDate;
    }
}