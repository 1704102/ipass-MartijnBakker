package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Reservering;
import com.vogella.jersey.first.database.ReserveringDatabase;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


/**
 * Created by marti on 1-5-2017.
 */
@Path("reserveren")
public class ReserveringResource {
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getReserveringen(){
        ReserveringDatabase dat = new ReserveringDatabase();
        ArrayList<Reservering> reserveringen = dat.getReserveringen();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < reserveringen.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("table", reserveringen.get(i).getTable());
            job.add("date", reserveringen.get(i).getDate());
            job.add("count", reserveringen.get(i).getCount());
            job.add("name", reserveringen.get(i).getName());
            job.add("email", reserveringen.get(i).getEmail());
            jab.add(job);
        }
        JsonArray array = jab.build();
        return array.toString();
    }
    @Path("/{data}")
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
