package com.vogella.jersey.first.database;


import armdb.ConnectHost;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by marti on 20-1-2017.
 */
public class DatabaseHelper {
    String fileURL="http://sharp-band.nl/Scripts/handleSQL.php";   //URL of 'handleSQL.php' file
    String host="sharp-band.nl.mysql";                               //server host name
    String user="sharp_band_nl";                                            //username
    String pass="klbxjmpv526f";                                        //password
    String DBName="sharp_band_nl";                                        //database name
    //make connection
    ConnectHost con;

    Connection connection;
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");;
            con=new ConnectHost(fileURL, host, user, pass, DBName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public QueryResult select(String query){
        connect();
        SQLQuery query1=new SQLQuery(con);
        QueryResult s = null;
        try {
        s = query1.statement(query);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public void delete(String query){
        try{
            //statement
            SQLUpdate query1=new SQLUpdate(con);
            query1.statement(query);
        }catch(Exception e){                                  //catch exception if occurred
            System.out.println(e.getMessage());                        //print exception message
        }
    }

    public void insert(String query){
        try{
            SQLUpdate query1=new SQLUpdate(con);
            query1.statement(query);
        }catch(Exception e){                                  //catch exception if occurred
            System.out.println(e.getMessage());                        //print exception message
        }
    }

    public void disconnect(){
        try {
//            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
