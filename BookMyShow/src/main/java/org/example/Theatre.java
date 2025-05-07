package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Theatre {
    private String name;
    private String id;
    private List<Show> showList;

    public Theatre(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString().substring(0, 5);
        setUpShows();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList(List<Show> showList) {
        this.showList = showList;
    }

    private void setUpShows(){
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= 70; i++) {
            Seat seat = new Seat(i);
            seats.add(seat);
        }
        Show show1 = new Show(null, LocalTime.of(9, 0), LocalTime.of(12, 0), seats);
        Show show2 = new Show(null, LocalTime.of(13, 0), LocalTime.of(16, 0), seats);
        Show show3 = new Show(null, LocalTime.of(20, 0), LocalTime.of(23, 0), seats);

        this.showList = new ArrayList<>(Arrays.asList(show1, show2, show3));
    }
}
