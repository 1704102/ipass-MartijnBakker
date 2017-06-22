package com.vogella.jersey.first;

import com.vogella.jersey.first.Model.Reservering;
import com.vogella.jersey.first.Model.Tafel;
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
    public String getReserveringen() {
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
    @Produces(MediaType.TEXT_PLAIN)
    @PUT
    public String addReservering(@PathParam("data") String data) {
        ReserveringDatabase database = new ReserveringDatabase();
        String[] s = data.split(",");
        String name = s[0];
        String surname = s[1];
        String email = s[2];
        String personen = s[3];
        String date = s[4];
        String time = s[5];
        int id = 0;
        ArrayList<Tafel> tafels = database.getTafels();

       for (int i = 0; i < tafels.size(); i++){  outerloop:
           if(tafels.get(i).getPlaces() >= Integer.parseInt(personen)){
               ArrayList<Reservering> out = getReserveringen(tafels.get(i).getId(),date);
               if (out.size() == 0){
                   System.out.println("succes");
                   database.inserReservering(tafels.get(i).getId(), name, surname, email, personen, date, time);
                   return "succes";
               }else {
                   for (int j = 0; j < out.size(); j++) {
                           String[] s1 = out.get(j).getTime().split(":");
                           int hour1 = Integer.parseInt(s1[0]);
                           String[] s2 = time.split(":");
                           int hour2 = Integer.parseInt(s2[0]);
                           if (hour1 - hour2 < 2 && hour1 - hour2 > -2) {
                               System.out.println("fauk");
                               break outerloop;

                       }
                   }
                   System.out.println("succes");
                   database.inserReservering(tafels.get(i).getId(), name, surname, email, personen, date, time);
                   return "succes";
               }
           }
       }

        return "error";
    }

    public ArrayList<Reservering> getReserveringen(int id, String date){
        ArrayList<Reservering> data = new ArrayList<Reservering>();
        ReserveringDatabase dat = new ReserveringDatabase();
        ArrayList<Reservering> reserveringen = dat.getTableRes(id,date);
        for (int i = 0; i < reserveringen.size(); i++){
            if (reserveringen.get(i).getTable() == id){
                if(date.equals(reserveringen.get(i).getDateS())) {
                    data.add(reserveringen.get(i));
                }
            }
        }
        return data;
    }


}
