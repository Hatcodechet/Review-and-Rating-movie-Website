package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.Movie;
import com.repository.MovieRepository;

@Service

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository mvRepo) {
        this.movieRepository = mvRepo;
    }

    public Movie handleCreateMovie(Movie newMovie) {
        if (this.movieRepository.findByTitle(newMovie.getTitle()) != null) {
            return null;
        }
        return this.movieRepository.save(newMovie);
    }

    public void handleDeleteMovie(long id) {
        this.movieRepository.deleteById(id);
    }

    public Movie handleGetMovie(long id) {
        Optional<Movie> targetMovie = this.movieRepository.findById(id);
        if (targetMovie.isPresent()) {
            return targetMovie.get();
        }
        return null;
    }

    public List<Movie> handleGetAllMovie() {
        return this.movieRepository.findAll();
    }

    public Movie handleUpdateMovie(Movie targetMovie) {
        Movie movie = this.handleGetMovie(targetMovie.getId());
        if (movie != null) {
            movie.setTitle(targetMovie.getTitle());
            this.movieRepository.save(movie);
        }
        return movie;
    }

    public String handleDeleteAll() {
        movieRepository.deleteAll();
        return "All the movies has been deleted";
    }

}
