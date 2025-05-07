package org.example.service;

import org.example.City;
import org.example.Movie;

import java.util.List;

public interface MovieService {
    void addMovieInCity(Movie movie, City city);
    List<Movie> findMovieByCity(City city);

}
