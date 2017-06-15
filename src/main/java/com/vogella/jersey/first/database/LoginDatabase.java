package com.vogella.jersey.first.database;

import com.vogella.jersey.first.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by marti on 10-5-2017.
 */
public class LoginDatabase extends DatabaseHelper {

    public User checkLogin(String username, String password){
        if(username.equals("") || password.equals("")){
            return null;
        }else {
            connect();
            ResultSet s = select("select * from werknemers");
            User user;
            System.out.println("start");
            try {
                while (s.next()){
                    if (username.equals(s.getString("username"))){
                        if (password.equals(s.getString("wachtwoord"))){
                            String voornaam = s.getString("voornaam");
                            String achternaam = s.getString("achternaam");
                            String functie = s.getString("functie");
                            String geboorteDatum = s.getString("geboorteD");
                            String email = s.getString("email");
                            String adres = s.getString("adres");
                            String aangenomen = s.getString("aangenomen");

                            return new User(username, voornaam , achternaam, functie, geboorteDatum, email, adres, aangenomen);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } disconnect();
        }
        return null;
    }
}
