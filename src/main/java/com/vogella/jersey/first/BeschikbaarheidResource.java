package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Beschikbaarheid;
import com.vogella.jersey.first.database.BeschikbaarheidDatabase;


import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by marti on 18-6-2017.
 */
@Path("beschikbaarheid/")
public class BeschikbaarheidResource {
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBeschikbaarheid(@PathParam("id") String id) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        BeschikbaarheidDatabase dat = new BeschikbaarheidDatabase();
        ArrayList<Beschikbaarheid> beschikbaarheid = dat.getBeschikbaarheid(Integer.parseInt(id));
        for (int i = 0; i < beschikbaarheid.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("day", beschikbaarheid.get(i).getDay());
            job.add("timeB", beschikbaarheid.get(i).getTimeB());
            job.add("timeE", beschikbaarheid.get(i).getTimeE());
            jab.add(job);
        }

        JsonArray array = jab.build();
        System.out.println(array.toString());
        return array.toString();
    }

    @Path("save/{data}/{id}")
    @PUT
    public void saveData(@PathParam("data") String e, @PathParam("id") int id) {
        JsonReader jsonReader = Json.createReader(new StringReader(e));
        JsonArray array = jsonReader.readArray();
        ArrayList<Beschikbaarheid> beschikbaarheid = new ArrayList();
        String[] days_of_week = {"Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag"};
        for (int i = 0; i < array.size(); i++) {
            beschikbaarheid.add(new Beschikbaarheid(days_of_week[i], array.getJsonObject(i).getString("timeB"),array.getJsonObject(i).getString("timeE")));
        }
        jsonReader.close();

        BeschikbaarheidDatabase dat= new BeschikbaarheidDatabase();
        dat.saveBeschikbaarheid(beschikbaarheid, id);

    }
}
