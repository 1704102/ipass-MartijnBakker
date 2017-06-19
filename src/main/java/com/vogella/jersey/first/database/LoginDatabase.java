package com.vogella.jersey.first.database;

import armdb.QueryResult;
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
            QueryResult s = select("select * from werknemers");
            User user;
            System.out.println("start");
            try {
                while (s.nextFlag()){
                    if (username.equals(s.getValue("username"))){
                        if (password.equals(s.getValue("wachtwoord"))){
                            int id = Integer.parseInt(s.getValue("id"));
                            String voornaam = s.getValue("voornaam");
                            String achternaam = s.getValue("achternaam");
                            String functie = s.getValue("functie");
                            String geboorteDatum = s.getValue("geboorteD");
                            String email = s.getValue("email");
                            String adres = s.getValue("adres");
                            String aangenomen = s.getValue("aangenomen");

                            return new User(id, username, voornaam , achternaam, functie, geboorteDatum, email, adres, aangenomen, password);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } disconnect();
        }
        return null;
    }
}
