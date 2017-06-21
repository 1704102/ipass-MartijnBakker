package com.vogella.jersey.first.database;

import com.vogella.jersey.first.Model.Reservering;
import com.vogella.jersey.first.database.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by marti on 4-5-2017.
 */
public class ReserveringDatabase extends DatabaseHelper {

    public void inserReservering(String name, String surName, String email, String personen, String date, String time){
        String date1 = "STR_TO_DATE('" + date + " " + time + ":00','%Y-%m-%d %H:%i:%s')";
        insert(String.format("insert into reservering (tafelId, voornaam, achternaam, email, aantal, datum, aangemaakt) values (1,\"%s\",\"%s\",\"%s\",%d,%s, now())",
                name, surName, email, Integer.parseInt(personen), date1));
    }

    public ArrayList<Reservering> getReserveringen(){
        ArrayList<Reservering> reserveringen = new ArrayList();
        ResultSet s = select("select * from reservering where datum > curdate() and datum < DATE_ADD(CURDATE(), INTERVAL +1 DAY);");
        try {
            while (s.next()){
                int table = s.getInt("tafelId");
                String date=  s.getString("datum");
                date = date.substring(0, date.length() - 5);
                int count = s.getInt("aantal");
                String name = s.getString("voornaam") + " " + s.getString("achternaam");
                String email = s.getString("email");
                reserveringen.add(new Reservering(table, date, count, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserveringen;
    }
}
