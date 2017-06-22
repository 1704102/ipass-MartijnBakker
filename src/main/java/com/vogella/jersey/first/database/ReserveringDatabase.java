package com.vogella.jersey.first.database;

import com.vogella.jersey.first.Model.Reservering;
import com.vogella.jersey.first.Model.Tafel;
import com.vogella.jersey.first.database.DatabaseHelper;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by marti on 4-5-2017.
 */
public class ReserveringDatabase extends DatabaseHelper {

    public void inserReservering(int id,String name, String surName, String email, String personen, String date, String time){
        String date1 = "STR_TO_DATE('" + date + " " + time + ":00','%Y-%m-%d %H:%i:%s')";
        insert(String.format("insert into reservering (tafelId, voornaam, achternaam, email, aantal, datum, aangemaakt) values (%d,\"%s\",\"%s\",\"%s\",%d,%s, now())",
               id, name, surName, email, Integer.parseInt(personen), date1));
        disconnect();
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
        disconnect();
        return reserveringen;
    }


    public ArrayList<Reservering> getTableRes(int id, String date1){
        ArrayList<Reservering> reserveringen = new ArrayList();
        String dat2 = date1 + "%";
        ResultSet s = select(String.format("select * from reservering where datum like \"%s\" and tafelId = %d;",dat2, id));
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
        disconnect();
        return reserveringen;
    }

    public ArrayList<Tafel> getTafels(){
        ArrayList<Tafel> tafels = new ArrayList();
        ResultSet s = select("select * from tafel");
        try {
            while (s.next()){
                tafels.add(new Tafel(s.getInt("id"), s.getInt("plaatsen")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();return tafels;
    }
}
