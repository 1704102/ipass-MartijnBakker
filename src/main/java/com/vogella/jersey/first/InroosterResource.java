package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Inroostering;
import com.vogella.jersey.first.Model.User;
import com.vogella.jersey.first.database.InroosterDatabase;
import com.vogella.jersey.first.database.RoosterDatabase;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by marti on 19-6-2017.
 */
@Path("inrooster")
public class InroosterResource {

    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getEmployees(){
        InroosterDatabase dat = new InroosterDatabase();
        ArrayList<User> employees = dat.getAllEmployees();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < employees.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", employees.get(i).getId());
            job.add("name", employees.get(i).getVoornaam() + " " + employees.get(i).getAchternaam());
            job.add("functie", employees.get(i).getFunctie());
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("Rososter/{date1}/{date2}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRooster(){
        InroosterDatabase dat = new InroosterDatabase();
        ArrayList<User> employees = dat.getAllEmployees();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < employees.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", employees.get(i).getId());
            job.add("name", employees.get(i).getVoornaam() + " " + employees.get(i).getAchternaam());
            job.add("functie", employees.get(i).getFunctie());
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }
}
