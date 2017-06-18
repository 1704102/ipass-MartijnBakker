package com.vogella.jersey.first.Model;

/**
 * Created by marti on 18-6-2017.
 */
public class Beschikbaarheid {
    private String day;
    private String timeB;
    private String timeE;

    public Beschikbaarheid(String day, String timeB, String timeE) {
        this.day = day;
        this.timeB = timeB;
        this.timeE = timeE;
    }

    public String getDay() {
        return day;
    }

    public String getTimeB() {
        return timeB;
    }

    public String getTimeE() {
        return timeE;
    }
}
