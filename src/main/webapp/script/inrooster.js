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
    date2 = new Date(date);
    date2.setDate(date2.getDate() + 6);
    $("#date").append(date.getFullYear() + " " + month_of_year[date.getMonth()] + " " + date.getDate() + " - "+ month_of_year[date2.getMonth()] + date2.getDate());
}

function next() {
    date.setDate(date.getDate() + 7);
    setDate();
    createTable()
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
    createTable()
}

function createTable() {
    dateIn = ["M", "T", "W", "T", "F", "S", "S"];
    $("#table").empty();
    $("#table").append("<tr><th>werknemer</th><th>inroostering</th></tr>");
    $.get("rest/inrooster/employees", function (data) {
        for(var dat in data){
            var user = data[dat];
            $("#table").append("<tr><td>" + user.name + "</td><td>" +  "<div class='week" + user.id + "'>");
            for(var i = 0; i < 7; i++) {
                var input = (parseInt(date.getDate() + i));
                if(input < 10){
                    input = "0"  + input;
                }
                console.log("start add")
                $(".week" + user.id).append("<div onclick='setEditDiv(event)' id='" + input + "-" + user.id + "'>" + dateIn[i] + "</div>");
            }
            console.log("end")
            $("#table").append("</div>" + "</td></tr>");
        }
        filltable();
    });

}

function filltable() {
    dateB =date.getFullYear() + "-" + (parseInt(date.getMonth()) + 1)+ "-" + date.getDate();
    dateE = date2.getFullYear() + "-" + (parseInt(date2.getMonth()) + 1) + "-" + (parseInt(date2.getDate()) + 1);
    $.get("rest/inrooster/rooster/" + dateB + "/" + dateE,function (data) {
        for(dat in data){
            inroostering = data[dat];
            $("#" + inroostering.timeB_day +"-"+ inroostering.id).css("background-color", "#70A3C1");
            $("#" + inroostering.timeB_day +"-"+ inroostering.id).prop("title",(inroostering.timeB_time + " - " + inroostering.timeE_time));
        }
    });
}

function setEditDiv(event) {
    $("#inroosterBlock").css("display", "inline-block   ");
    $("#editHeader").empty();
    $("#editTime").empty();
    $("#timeB").val("");
    $("#timeE").val("");
    var a = event.target.id;
    var data = a.split("-");
    var date1 = data[0];
    var id = data[1];
    sessionStorage.setItem("id", id);
    $("#id").append(id);
    $.get("rest/inrooster/" + id, function (data) {
        console.log(data);
        $("#editHeader").append(data[0].name);
        $("#editTime").append(days_of_the_week[date.getDay()] + " " + date1 + " " + month_of_year[date.getMonth()] + " " + date.getFullYear());
    });
    dateB =date.getFullYear() + "-" + (parseInt(date.getMonth()) + 1)+ "-" + date1;
    sessionStorage.setItem("time", dateB);
    $.get("rest/inrooster/employee/"+ dateB + "/" + id, function (data) {
        $("#timeB").val(data[0].timeB_time);
        $("#timeE").val(data[0].timeE_time);
    })

    getBeschikbaarheid(id);
}

function getBeschikbaarheid(id) {
    $.get("rest/beschikbaarheid/" + id, function (data) {
        $("#beschikbaarheid").empty();
        $("#beschikbaarheid").append("<div style='display:block; text-align: center'>Beschikbaarheid</div>");
        for(dat in data){
            beschikbaarheid = data[dat];
            $("#beschikbaarheid").append(
                "<div style='display: block'>" +
                "<div style='display: inline-block; width: 100;'>"+ beschikbaarheid.day +"</div>" +
                "<div style='display: inline-block; width: 100;'>"+ beschikbaarheid.timeB +"</div>" +
                "<div style='display: inline-block; width: 100;'>"+ beschikbaarheid.timeE +"</div>" +
                "</div>"

            );
        }
    })
}

function saveData() {
    var id = sessionStorage.getItem("id");
    var date1 = sessionStorage.getItem("time");
    var time1 = $("#timeB").val();
    var time2 = $("#timeE").val();
    dateC1 = date1 + " " + time1+":00";
    dateC2 = date1 + " " + time2+":00";

    $.ajax({
        url: "rest/inrooster/save/" + id + "/" + dateC1 + "/" + dateC2    ,
        type: 'put',
        dataType: 'text'
    });
    $("#inroosterBlock").css("display", "none");
    createTable()
}
