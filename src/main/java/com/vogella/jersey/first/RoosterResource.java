package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Inroostering;
import com.vogella.jersey.first.database.RoosterDatabase;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by marti on 15-6-2017.
 */
@Path("rooster/{name}")
public class RoosterResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRooster(@PathParam("name") int id) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        RoosterDatabase dat = new RoosterDatabase();
        ArrayList<Inroostering> rooster = dat.getRooster(id);
        for (int i = 0; i < rooster.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("timeB_year", rooster.get(i).getDateB_year());
            job.add("timeB_month", rooster.get(i).getDateB_month());
            job.add("timeB_day", rooster.get(i).getDateB_day());
            job.add("timeB_time", rooster.get(i).getDateB_time());
            job.add("timeE_year", rooster.get(i).getDateE_year());
            job.add("timeE_month", rooster.get(i).getDateE_month());
            job.add("timeE_day", rooster.get(i).getDateE_day());
            job.add("timeE_time", rooster.get(i).getDateE_time());
            jab.add(job);
        }

        JsonArray array = jab.build();
        System.out.println(array.toString());
        return array.toString();
        }
        }
