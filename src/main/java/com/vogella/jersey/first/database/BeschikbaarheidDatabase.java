package com.vogella.jersey.first.database;


import com.vogella.jersey.first.Model.Beschikbaarheid;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by marti on 18-6-2017.
 */
public class BeschikbaarheidDatabase extends DatabaseHelper{

    public ArrayList<Beschikbaarheid> getBeschikbaarheid(int id){
        ArrayList<Beschikbaarheid> beschikbaarheid = new ArrayList();

        ResultSet s = select(String.format("select * from beschikbaarheid where medewerkerId = %d", id));

        try{
            while (s.next()){
                String day = s.getString("dag");
                String timeB = s.getString("tijdB");
                String timeE =  s.getString("tijdE");

                beschikbaarheid.add(new Beschikbaarheid(day, timeB, timeE));

            }   disconnect();
        }catch (Exception c){

        }

        return beschikbaarheid;
    }

    public void saveBeschikbaarheid(ArrayList<Beschikbaarheid> beschikbaarheid, int id){
        delete(String.format("delete from beschikbaarheid where medewerkerId = %d", id));
        for(int i = 0; i < beschikbaarheid.size(); i++){
            Beschikbaarheid s = beschikbaarheid.get(i);
            insert(String.format( "insert into beschikbaarheid values (%d, \"%s\", \"%s\", \"%s\")", id, s.getDay(), s.getTimeB(), s.getTimeE()));
        }
    }


}
