package com.vogella.jersey.first.database;

import com.vogella.jersey.first.Model.Inroostering;
import com.vogella.jersey.first.Model.User;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by marti on 19-6-2017.
 */
public class InroosterDatabase extends DatabaseHelper {

    public ArrayList<User> getAllEmployees() {
        ArrayList<User> employees = new ArrayList();
        ResultSet s = select("select * from werknemers");
        try {
            while (s.next()) {
                int id = s.getInt("id");
                String username = s.getString("username");
                String password = s.getString("wachtwoord");
                String voornaam = s.getString("voornaam");
                String achternaam = s.getString("achternaam");
                String functie = s.getString("functie");
                String geboorteDatum = s.getString("geboorteD");
                String email = s.getString("email");
                String adres = s.getString("adres");
                String aangenomen = s.getString("aangenomen");

                employees.add(new User(id, username, voornaam, achternaam, functie, geboorteDatum, email, adres, aangenomen, password));
            }
            disconnect();
        } catch (Exception e) {

        }
        return employees;
    }

    public ArrayList<Inroostering> getRooster(String date1, String date2) {
        ArrayList<Inroostering> rooster = new ArrayList();
        ResultSet s = select(String.format("SELECT * FROM `inroostering` WHERE ( tijdB BETWEEN \"%s\" AND \"%s\")", date1, date2));
        try {
            while (s.next()) {
                rooster.add(new Inroostering(
                        s.getInt("medewerkerId"),
                        s.getString("tijdB").replace(":00", ""),
                        s.getString("tijdE").replace(":00", "")));
            }
            disconnect();
        } catch (Exception e) {

        }
        return rooster;
    }

    public Inroostering getSpRooster(String date, int id) {
        String[] split = date.split("-");
        if (Integer.parseInt(split[1]) < 10) {
            split[1] = "0" + split[1];
        }
        String date1 = split[0] + "-" + split[1] + "-" + split[2] + "%";
        ResultSet s = select(String.format("SELECT * FROM `inroostering` WHERE ( tijdB like \"%s\") and medewerkerId = %d", date1, id));
        Inroostering rooster = null;
        try {
            while (s.next()) {
                String dB = s.getString("tijdB");
                dB = dB.substring(0, dB.length() - 5);
                String dE = s.getString("tijdB");
                dE = dE.substring(0, dE.length() - 5);
                rooster = new Inroostering(s.getInt("medewerkerId"), dB, dE);
            }
            disconnect();
        }catch (Exception e){

        }
        return rooster;
    }

    public void saveInroostering(int id, String date1, String date2) {
        System.out.println(date1.length() + "-" + date2.length());
        System.out.println(date1);
        System.out.println(date2);
        String[] split = date1.split(" ");
        String[] split2 = split[0].split("-");
        System.out.println("pass");
        if (Integer.parseInt(split2[1]) < 10) {
            split2[1] = "0" + split2[1];
        }
        System.out.println(split2[2]);
        if (Integer.parseInt(split2[2]) < 10) {
            split2[2] = "0" + split2[2];
        }
        split2[2] = split2[2] + "%";
        System.out.println(date1.length() + "-" + date2.length());
        System.out.println(date1);
        System.out.println(date2);
        String date = split2[0] + "-" + split2[1] + "-" + split2[2] + "%";
        delete(String.format("delete from inroostering where medewerkerId = %d and tijdB like \"%s\"", id, date));
            if(date1.length() >= 17 && date2.length() >= 17) {
                System.out.println(String.format("insert into inroostering values (%d, \"%s\", \"%s\")", id, date1, date2));
                insert(String.format("insert into inroostering values (%d, \"%s\", \"%s\")", id, date1, date2));
            }


    }


}
