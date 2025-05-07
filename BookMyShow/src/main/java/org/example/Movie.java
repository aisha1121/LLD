package org.example;

import java.util.List;
import java.util.UUID;

public class Movie {
    private String name;
    private int duration;
    private String id;

    public Movie(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.id = UUID.randomUUID().toString().substring(0, 5);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
