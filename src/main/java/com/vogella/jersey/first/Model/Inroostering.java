package com.vogella.jersey.first.Model;

/**
 * Created by marti on 15-6-2017.
 */
public class Inroostering {
    private int id;
    private String dateB_year;
    private String dateB_month;
    private String dateB_day;
    private String dateB_time;
    private String dateE_year;
    private String dateE_month;
    private String dateE_day;
    private String dateE_time;

    public Inroostering(int id, String dateB, String dateE) {
        this.id = id;
        String[] s = dateB.split(" ");
        dateB_time = s[1].replace(":00.0", "");
        String[] s1 = s[0].split("-");
        dateB_year = s1[0];
        dateB_month = s1[1];
        dateB_day= s1[2];
         s = dateE.split(" ");
        dateE_time = s[1].replace(":00.0", "");
         s1 = s[0].split("-");
        dateE_year = s1[0];
        dateE_month = s1[1];
        dateE_day= s1[2];
    }

    public String getDateB_year() {
        return dateB_year;
    }

    public String getDateB_month() {
        return dateB_month;
    }

    public String getDateB_day() {
        return dateB_day;
    }

    public String getDateB_time() {
        return dateB_time;
    }

    public String getDateE_year() {
        return dateE_year;
    }

    public String getDateE_month() {
        return dateE_month;
    }

    public String getDateE_day() {
        return dateE_day;
    }

    public String getDateE_time() {
        return dateE_time;
    }

    public int getId() {
        return id;
    }
}
