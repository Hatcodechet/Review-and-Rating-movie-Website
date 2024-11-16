package com.controller;

import com.dto.RatingDto;
import com.dto.RatingResponse;
import com.model.Movie;
import com.model.Rating;
import com.model.User;
import com.service.MovieService;
import com.service.RatingService;
import com.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final UserService userService;
    private final MovieService movieService;

    public RatingController(RatingService ratingService, UserService userService, MovieService movieService) {
        this.ratingService = ratingService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<RatingResponse> createRating(@RequestBody RatingDto ratingDto) {
        User user = userService.fetchUserById(ratingDto.getUserId());
        Movie movie = movieService.handleGetMovie(ratingDto.getMovieId());

        Rating newRating = ratingService.createRating(new Rating(user, movie, ratingDto.getRating(), LocalDateTime.now()));

        RatingResponse response = new RatingResponse(newRating.getId(), user, movie, newRating.getRating(), newRating.getRatingDate());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRating(@PathVariable Long id) {
        Rating rating = ratingService.getRatingById(id);

        User user = rating.getUser();
        Movie movie = rating.getMovie();

        RatingResponse response = new RatingResponse(rating.getId(), user, movie, rating.getRating(), rating.getRatingDate());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
}