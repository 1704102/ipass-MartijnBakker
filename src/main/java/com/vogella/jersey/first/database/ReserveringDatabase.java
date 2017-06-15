package com.vogella.jersey.first.database;

import com.vogella.jersey.first.database.DatabaseHelper;

/**
 * Created by marti on 4-5-2017.
 */
public class ReserveringDatabase extends DatabaseHelper {

    public void inserReservering(String name, String surName, String email, String personen, String date, String time){
        String date1 = "STR_TO_DATE('" + date + " " + time + ":00','%Y-%m-%d %H:%i:%s')";
        System.out.println(date1);
        insert(String.format("insert into reservering (tafelId, voornaam, achternaam, email, aantal, datum, aangemaakt) values (1,\"%s\",\"%s\",\"%s\",%d,%s, now())",
                name, surName, email, Integer.parseInt(personen), date1));
    }
}
