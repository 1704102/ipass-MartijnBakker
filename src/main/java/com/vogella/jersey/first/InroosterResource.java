package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Inroostering;
import com.vogella.jersey.first.Model.User;
import com.vogella.jersey.first.database.InroosterDatabase;
import com.vogella.jersey.first.database.LoginDatabase;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        System.out.println("call");
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
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", "1");
        job.add("name", "martijn Bakker");
        job.add("functie", "admin");
        jab.add(job);
        JsonArray array = jab.build();
        return array.toString();
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployee(@PathParam("id") int begin){
        LoginDatabase dat = new LoginDatabase();
        User user = dat.getUser(begin);
        JsonArrayBuilder jab = Json.createArrayBuilder();
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", user.getId());
            job.add("name", user.getVoornaam() + " " + user.getAchternaam());
            jab.add(job);
        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("rooster/{date1}/{date2}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRooster(@PathParam("date1") String begin, @PathParam("date2") String end){
        InroosterDatabase dat = new InroosterDatabase();
        ArrayList<Inroostering> rooster = dat.getRooster(begin, end);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (int i = 0; i < rooster.size(); i++) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", rooster.get(i).getId());
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
        return array.toString();
    }
    @Path("/employee/{date1}/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSpRooster(@PathParam("date1") String begin, @PathParam("id") int id){
        InroosterDatabase dat = new InroosterDatabase();
        Inroostering rooster = dat.getSpRooster(begin, id);
        JsonArrayBuilder jab = Json.createArrayBuilder();
            JsonObjectBuilder job = Json.createObjectBuilder();
            if(rooster != null) {
                job.add("id", rooster.getId());
                job.add("timeB_year", rooster.getDateB_year());
                job.add("timeB_month", rooster.getDateB_month());
                job.add("timeB_day", rooster.getDateB_day());
                job.add("timeB_time", rooster.getDateB_time());
                job.add("timeE_year", rooster.getDateE_year());
                job.add("timeE_month", rooster.getDateE_month());
                job.add("timeE_day", rooster.getDateE_day());
                job.add("timeE_time", rooster.getDateE_time());
                jab.add(job);
            }



        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/save/{id}/{date1}/{date2}")
    @PUT
    public void saveData(@PathParam("id")int id, @PathParam("date1")String date1,@PathParam("date2")String date2){
        InroosterDatabase dat = new InroosterDatabase();
        dat.saveInroostering(id,date1,date2);
    }
}
