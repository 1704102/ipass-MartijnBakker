package com.vogella.jersey.first.Model;

/**
 * Created by marti on 21-6-2017.
 */
public class Reservering {

    private int table;
    private String date;
    private int count;
    private String name;
    private String email;

    public Reservering(int table, String date, int count, String name, String email) {
        this.table = table;
        this.date = date;
        this.count = count;
        this.name = name;
        this.email = email;
    }

    public int getTable() {
        return table;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
