package com.vogella.jersey.first.Model;

/**
 * Created by marti on 13-6-2017.
 */
public class User {
    private String username;
    private String voornaam;
    private String achternaam;
    private String functie;
    private String geboorteDatum;
    private String email;
    private String adres;
    private String aangenomen;

    public User(String username, String voornaam, String achternaam, String functie, String geboorteDatum, String email, String adres, String aangenomen) {
        this.username = username;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.functie = functie;
        this.geboorteDatum = geboorteDatum;
        this.email = email;
        this.adres = adres;
        this.aangenomen = aangenomen;
    }

    public String getUsername() {
        return username;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getFunctie() {
        return functie;
    }

    public String getGeboorteDatum() {
        return geboorteDatum;
    }

    public String getEmail() {
        return email;
    }

    public String getAdres() {
        return adres;
    }

    public String getAangenomen() {
        return aangenomen;
    }
}
