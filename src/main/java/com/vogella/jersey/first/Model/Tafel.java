package com.vogella.jersey.first.Model;

/**
 * Created by marti on 21-6-2017.
 */
public class Tafel {
    private int id;
    private int places;

    public Tafel(int id, int places) {
        this.id = id;
        this.places = places;
    }

    public int getId() {
        return id;
    }

    public int getPlaces() {
        return places;
    }
}
