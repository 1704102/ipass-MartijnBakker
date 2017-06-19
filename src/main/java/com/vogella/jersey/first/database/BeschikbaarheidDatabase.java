package com.vogella.jersey.first.database;


import armdb.QueryResult;
import com.vogella.jersey.first.Model.Beschikbaarheid;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by marti on 18-6-2017.
 */
public class BeschikbaarheidDatabase extends DatabaseHelper{

    public ArrayList<Beschikbaarheid> getBeschikbaarheid(int id){
        ArrayList<Beschikbaarheid> beschikbaarheid = new ArrayList();

        connect();
        QueryResult s = select(String.format("select * from beschikbaarheid where medewerkerId = %d", id));

        try{
            while (s.nextFlag()){
                String day = s.getValue("dag");
                String timeB = s.getValue("tijdB");
                String timeE =  s.getValue("tijdE");

                beschikbaarheid.add(new Beschikbaarheid(day, timeB, timeE));
            }
        }catch (Exception c){

        }
        disconnect();

        return beschikbaarheid;
    }

    public void saveBeschikbaarheid(ArrayList<Beschikbaarheid> beschikbaarheid, int id){
        connect();
        delete(String.format("delete from beschikbaarheid where medewerkerId = %d", id));
        for(int i = 0; i < beschikbaarheid.size(); i++){
            Beschikbaarheid s = beschikbaarheid.get(i);
            insert(String.format( "insert into beschikbaarheid values (%d, \"%s\", \"%s\", \"%s\")", id, s.getDay(), s.getTimeB(), s.getTimeE()));
        }
        disconnect();
    }


}
