/**
 * Created by marti on 19-6-2017.
 */
date = new Date();
month_of_year = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "Oktober", "November", "December"];
days_of_the_week = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
month = date.getMonth();
year = date.getFullYear();


function setDate() {
    $("#date").empty();
    var Smonth = month_of_year[month];
    $("#date").append(year + " " + Smonth);
}

function next() {
    month = month + 1;
    if(month == 12){ month = 0; year = year + 1}
    setDate();
}

function makeCalender() {
    var Smonth = month_of_year[month];
    setDate();
    console.log(days_of_the_week[date.getDay()]);
}


function previous() {
    month = month - 1;
    if(month == -1){
        month = 11;
        year = year - 1;
    }
    setDate();
}
