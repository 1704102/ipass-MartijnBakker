package com.vogella.jersey.first;

import com.vogella.jersey.first.database.ReserveringDatabase;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



/**
 * Created by marti on 1-5-2017.
 */
@Path("reserveren/{data}")
public class ReserveringResource {

    @PUT
    public void addReservering(@PathParam("data") String data) {
        String[] s = data.split(",");
        String name = s[0];
        String surname = s[1];
        String email = s[2];
        String personen = s[3];
        String date = s[4];
        String time = s[5];

        ReserveringDatabase database = new ReserveringDatabase();
        database.inserReservering(name, surname, email, personen, date, time);


    }


}
