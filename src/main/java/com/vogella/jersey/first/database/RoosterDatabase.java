package com.vogella.jersey.first.database;

import armdb.QueryResult;
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
        QueryResult s = select(String.format("select * from inroostering where medewerkerId = %d", id));
        try {
            while (s.nextFlag()) {
                rooster.add(new Inroostering(s.getValue("tijdB"), s.getValue("tijdE")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return rooster;
    }
}
