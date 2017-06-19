package com.vogella.jersey.first.database;

import armdb.QueryResult;
import com.vogella.jersey.first.Model.Inroostering;
import com.vogella.jersey.first.Model.User;

import java.util.ArrayList;

/**
 * Created by marti on 19-6-2017.
 */
public class InroosterDatabase extends DatabaseHelper{

    public ArrayList<User> getAllEmployees(){
        connect();
        ArrayList<User> employees = new ArrayList();
        QueryResult s = select("select * from werknemers");
        while (s.nextFlag()){
            int id = Integer.parseInt(s.getValue("id"));
            String username = s.getValue("username");
            String password = s.getValue("wachtwoord");
            String voornaam = s.getValue("voornaam");
            String achternaam = s.getValue("achternaam");
            String functie = s.getValue("functie");
            String geboorteDatum = s.getValue("geboorteD");
            String email = s.getValue("email");
            String adres = s.getValue("adres");
            String aangenomen = s.getValue("aangenomen");

            employees.add(new User(id, username, voornaam , achternaam, functie, geboorteDatum, email, adres, aangenomen, password));
        }
        return employees;
    }

    public ArrayList<Inroostering> getRooster(String date1,String date2){
        ArrayList<Inroostering> rooster = new ArrayList();
      QueryResult s = select("SELECT * FROM `inroostering` WHERE ( tijdB BETWEEN '2017-06-01' AND '2017-06-29')");
        while (s.nextFlag()) {
            rooster.add(new Inroostering(Integer.parseInt(s.getValue("medewerkerId")), s.getValue("tijdB").replace(":00", ""), s.getValue("tijdE").replace(":00", "")));
        }
      return rooster;
    }

}
