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
      QueryResult s = select(String.format("SELECT * FROM `inroostering` WHERE ( tijdB BETWEEN \"%s\" AND \"%s\")", date1,date2));
        while (s.nextFlag()) {
            rooster.add(new Inroostering(Integer.parseInt(s.getValue("medewerkerId")), s.getValue("tijdB").replace(":00", ""), s.getValue("tijdE").replace(":00", "")));
        }
      return rooster;
    }
    public Inroostering getSpRooster(String date, int id){
        String[] split = date.split("-");
        if(Integer.parseInt(split[1]) < 10){
            split[1] = "0" + split[1];
        }
        String date1 = split[0] + "-" + split[1] + "-" + split[2] + "%";
        QueryResult s = select(String.format("SELECT * FROM `inroostering` WHERE ( tijdB like \"%s\") and medewerkerId = %d", date1, id));
        Inroostering rooster = null;
        while (s.nextFlag()) {
            rooster = new Inroostering(Integer.parseInt(s.getValue("medewerkerId")), s.getValue("tijdB").replace(":00", ""), s.getValue("tijdE").replace(":00", ""));
        }
        return rooster;
    }

    public void saveInroostering(int id, String date1, String date2){
        connect();
        String[] split = date1.split(" ");
        split[0] = split[0] + "%";
        String[] split2 = split[0].split("-");
        if(Integer.parseInt(split2[1]) < 10){
            split2[1] = "0" + split2[1];
        }
        String date = split2[0] + "-" + split2[1] + "-" + split2[2] + "%";
        delete(String.format("delete from inroostering where tijdB like \"%s\"", date));
        insert(String.format("insert into inroostering values (%d, \"%s\", \"%s\")", id, date1, date2));
    }


}
