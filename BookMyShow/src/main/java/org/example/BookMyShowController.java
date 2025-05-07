package org.example;

import org.example.service.BookingService;
import org.example.service.MovieService;
import org.example.service.TheatreService;
import org.example.service.impl.BookingServiceImpl;
import org.example.service.impl.MovieServiceImpl;
import org.example.service.impl.TheatreServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookMyShowController {
    private static volatile BookMyShowController instance;
    private final MovieService movieService;
    private final TheatreService theatreService;
    private final List<City> cities;
    private final BookingService bookingService;

    private BookMyShowController() {
        this.movieService = new MovieServiceImpl();
        this.theatreService = new TheatreServiceImpl();
        this.cities = new ArrayList<>();
        this.bookingService = new BookingServiceImpl();
        initialise();
    }

    public static BookMyShowController getInstance() {
        if (instance == null) {
            synchronized (BookMyShowController.class) {
                if (instance == null) {
                    instance = new BookMyShowController();
                }
            }
        }
        return instance;
    }


    public void initialise() {
        // Add theatres in the cities
        // Hard coding cities
        City city1 = new City("Bangalore");
        City city2 = new City("Hyderabad");
        cities.add(city1);
        cities.add(city2);

        Theatre theatre1 = new Theatre("PVR");
        Theatre theatre2 = new Theatre("Cinepolis");
        Theatre theatre3 = new Theatre("Inox");

        Movie movie1 = new Movie("3 Idiots", 165);
        Movie movie2 = new Movie("Kaho na Pyaar hai", 195);
        Movie movie3 = new Movie("Swades", 140);
        Movie movie4 = new Movie("Animal", 204);

        theatreService.setUpTheatres(city1, movie1, Arrays.asList(theatre1, theatre2));
        theatreService.setUpTheatres(city1, movie2, Arrays.asList(theatre2, theatre3));
        theatreService.setUpTheatres(city1, movie3, Arrays.asList(theatre1, theatre3));

        theatreService.setUpTheatres(city2, movie4, Arrays.asList(theatre2, theatre3));
        theatreService.setUpTheatres(city2, movie1, Arrays.asList(theatre1, theatre3));

        System.out.println("Printing availability---");
    }

    public List<Movie> getMoviesByCity(String cityName){
        for(City city : cities){
            if(city.getName().equalsIgnoreCase(cityName)){
                List<Movie> movies =  city.getMovieIdVsTheatreListMap().keySet().stream().toList();
                System.out.println("Movies available in " + cityName + " city are: ");
                for (Movie movie : movies) {
                    System.out.println(movie.getName());
                }
                return movies;
            }
        }
        System.out.println("No movies available in " + cityName + " city.");
        return null;
    }

    public List<Show> displayAllShowsByMovie(Movie movie, String cityName) {
        List<Show> availableShows = new ArrayList<>();
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(cityName)) {
                List<Theatre> theatres = city.getMovieIdVsTheatreListMap().get(movie);
                if (!theatres.isEmpty()) {
                    System.out.println("Movie " + movie.getName() + " is present in below mentioned theatres with following shows:");
                    for (Theatre theatre : theatres) {
                        for (Show show : theatre.getShowList()) {
                            if (show.getMovieId().equals(movie.getId())) {
                                availableShows.add(show);
                                System.out.println("Theatre: " + theatre.getName() + " show time: " + show.getStartTime() + " to " + show.getEndTime());
                            }
                        }
                    }
                }
            }
        }
        return availableShows;
    }

    public String createBooking(Movie findMovie, Show availableShow, List<Integer> seatNumbers) {
        boolean seatAvailable = areSeatsAvailable(availableShow, seatNumbers);
        if (seatAvailable) {
            List<Seat> selectedSeats = new ArrayList<>();
            for (int seatNumber : seatNumbers) {
                selectedSeats.add(availableShow.getById(seatNumber));
            }
            Booking booking = bookingService.createBooking(selectedSeats);
            if (booking != null) {
                bookingService.confirmBooking(booking.getBookingId());
                return booking.getBookingId();
            }
        } else {
            System.out.println("Required seats are not available");
        }
        return null;
    }

    private boolean areSeatsAvailable(Show availableShow, List<Integer> seatNumbers) {
        for (int seatNumber : seatNumbers) {
            boolean isAvailable = availableShow.getSeatList().stream().anyMatch(seat -> seat.getSeatNumber() == seatNumber && seat.getSeatStatus() == SeatStatus.AVAILABLE);
            if (!isAvailable) return false;
        }
        return true;
    }

    public void cancelBooking(String bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
