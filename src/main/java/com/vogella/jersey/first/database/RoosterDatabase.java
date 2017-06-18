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
        connect();
        ResultSet s = select(String.format("select * from inroostering where medewerkerId = %d", id));
        try {
            while (s.next()) {
                rooster.add(new Inroostering(s.getString("tijdB"), s.getString("tijdE")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return rooster;
    }
}
