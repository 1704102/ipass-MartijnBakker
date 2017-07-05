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
                String date1 = s.getString("tijdB");
                String date2 = s.getString("tijdE");
                String dB = date1.substring(0, date1.length() - 5);
                String dE = date2.substring(0, date2.length() - 5);
                System.out.println(dB);
                System.out.println(dE);
                rooster.add(new Inroostering(s.getInt("medewerkerId"),dB, dE ));
            }
            disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooster;
    }
}
