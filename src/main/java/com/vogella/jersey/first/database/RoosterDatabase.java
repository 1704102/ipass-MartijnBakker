package com.vogella.jersey.first.database;

import com.vogella.jersey.first.Model.Inroostering;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by marti on 15-6-2017.
 */
public class RoosterDatabase extends DatabaseHelper {
    public ArrayList<Inroostering> getRooster(int id) {
        ArrayList<Inroostering> rooster = new ArrayList<Inroostering>();
        ResultSet s = select(String.format("select * from inroostering where medewerkerId = %d", id));
        try {
            while (s.next()) {
                rooster.add(new Inroostering(s.getInt("medewerkerId"), s.getString("tijdB").replace(":00", ""), s.getString("tijdE").replace(":00", "")));
            }
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooster;
    }
}
