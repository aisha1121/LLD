package org.example.service;

import org.example.City;
import org.example.Movie;
import org.example.Theatre;

import java.util.List;

public interface TheatreService {
    void setUpTheatres(City city, Movie movie, List<Theatre> theatres);
}
