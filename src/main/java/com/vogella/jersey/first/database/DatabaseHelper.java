package com.vogella.jersey.first.database;


import java.sql.*;

/**
 * Created by marti on 20-1-2017.
 */
public class DatabaseHelper {
    String host="37.128.146.87";                               //server host name
    String user="martijn";                                            //username
    String pass="Hogeschool@Utrecht!";                                        //password
    String DBName="ipass-martijn";                                        //database name
    //make connection
    Connection con;

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");;
            con = DriverManager
                    .getConnection("jdbc:mysql://"+ host +"/" + DBName + "?"
                            + "user=" + user +"&password=" + pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet select(String query){
        ResultSet s = null;
        try {
        connect();
        Statement st = con.createStatement();
        s = st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void delete(String query){
        try {
            connect();
            PreparedStatement st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void insert(String query){
        try {
            connect();
            PreparedStatement st = con.prepareStatement(query);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public void disconnect(){
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
