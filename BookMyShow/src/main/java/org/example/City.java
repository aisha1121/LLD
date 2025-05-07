package org.example;

import java.util.HashMap;
import java.util.List;

public class City {
    private String name;
    private HashMap<Movie, List<Theatre>> movieIdVsTheatreListMap;

    public City(String name) {
        this.name = name;
        movieIdVsTheatreListMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Movie, List<Theatre>> getMovieIdVsTheatreListMap() {
        return movieIdVsTheatreListMap;
    }

    public void setMovieIdVsTheatreListMap(HashMap<Movie, List<Theatre>> movieIdVsTheatreListMap) {
        this.movieIdVsTheatreListMap = movieIdVsTheatreListMap;
    }
}
