package org.example.service.impl;

import org.example.City;
import org.example.Movie;
import org.example.Show;
import org.example.Theatre;
import org.example.service.TheatreService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TheatreServiceImpl implements TheatreService {
    @Override
    public void setUpTheatres(City city, Movie movie, List<Theatre> theatres) {
        HashMap<Movie, List<Theatre>> movieIdVsTheatreListMap = city.getMovieIdVsTheatreListMap();
        List<Theatre> existingTheatres = movieIdVsTheatreListMap.get(movie);
        if (existingTheatres == null || existingTheatres.isEmpty()) {
            existingTheatres = new ArrayList<>();
        }
        existingTheatres.addAll(theatres);
        movieIdVsTheatreListMap.putIfAbsent(movie, existingTheatres);
        setUpMovieInShows(theatres, movie);
    }

    private void setUpMovieInShows(List<Theatre> theatres, Movie movie) {
        for (Theatre theatre : theatres) {
            for (Show show : theatre.getShowList()) {
                if (show.getMovieId() == null) {
                    show.setMovieId(movie.getId());
                    break;
                }
            }
        }
        System.out.println("No slot available for Movie: " + movie.getName() + " in any theatre");
    }
}
