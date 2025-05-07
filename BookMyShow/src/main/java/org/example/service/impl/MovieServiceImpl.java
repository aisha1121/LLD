package org.example.service.impl;

import org.example.City;
import org.example.Movie;
import org.example.service.MovieService;

import java.util.*;

public class MovieServiceImpl implements MovieService {
    private Map<City, List<Movie>> cityListMap = new HashMap<>();

    @Override
    public void addMovieInCity(Movie movie, City city) {
        List<Movie> existingMovie = findMovieByCity(city);
        if (existingMovie.isEmpty()) existingMovie = new ArrayList<>();
        existingMovie.add(movie);
    }

    @Override
    public List<Movie> findMovieByCity(City city) {
        return List.of();
    }
}
