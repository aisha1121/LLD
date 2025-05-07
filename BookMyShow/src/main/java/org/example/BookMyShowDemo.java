package org.example;

import java.util.Arrays;
import java.util.List;

public class BookMyShowDemo {

    public static void main(String[] args) {
        BookMyShowController controller = BookMyShowController.getInstance();
        List<Movie> movieList = controller.getMoviesByCity("Bangalore");
        if (movieList != null && !movieList.isEmpty()) {
            Movie findMovie = movieList.getFirst();
            List<Show> availableShows = controller.displayAllShowsByMovie(findMovie, "Bangalore");
            if (!availableShows.isEmpty()) {
                String bookingId = controller.createBooking(findMovie, availableShows.getFirst(), Arrays.asList(1, 60, 45));
                controller.cancelBooking(bookingId);
            } else {
                System.out.println("No show available!");
            }
        }
    }
}
