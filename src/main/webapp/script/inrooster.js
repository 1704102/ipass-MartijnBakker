/**
 * Created by marti on 19-6-2017.
 */
date = new Date();
date2 = new Date(date);
month_of_year = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "Oktober", "November", "December"];
days_of_the_week = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
month = date.getMonth();
year = date.getFullYear();
day = 0;


function setDate() {
    $("#date").empty();
    var Smonth = month_of_year[month];
    date2.setDate(date2.getDate() + 6);
    $("#date").append(date.getFullYear() + " " + month_of_year[date.getMonth()] + " " + date.getDate() + " - "+ month_of_year[date2.getMonth()] + date2.getDate());
}

function next() {
    date.setDate(date.getDate() + 7);
    setDate();
}

function makeCalender() {
    var Smonth = month_of_year[month];
    date.setMonth(date.getMonth())
    dayoffset = date.getDay();
    date.setDate(date.getDate() - dayoffset+1);
    day = date.getDate()
    setDate();
}


function previous() {
    date.setDate(date.getDate() - 7);
    setDate();
}

function createTable() {
    $("#table").empty();
    $("#table").append("<tr><th>werknemer</th><th>inroostering</th></tr>");
    $.get("http://localhost:8080/rest/inrooster/employees", function (data) {
        for(var dat in data){
            var user = data[dat];
            $("#table").append("<tr><td>" + user.name + "</td><td>" +
                "<div class='week'>" +
                "<div id='Monday_" + user.id + "'>M</div>" +
                "<div id='Tuesday_" + user.id + "'>T</div>" +
                "<div id='Wednessday_" + user.id + "'>W</div>" +
                "<div id='Thusday_" + user.id + "'>T</div>" +
                "<div id='Vryday_" + user.id + "'>V</div>" +
                "<div id='Saturday_" + user.id + "'>S</div>" +
                "<div id='Sunday_" + user.id + "'>S</div>" +
                "</div>"
                + "</td></tr>");
        }

    })
    filltable();
}

function filltable() {
        console.log(date);
        console.log(date2);
}
