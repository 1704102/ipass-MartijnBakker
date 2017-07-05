date = new Date();
month_of_year = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "Oktober", "November", "December"];
days_of_the_week = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
month = date.getMonth();
year = date.getFullYear();

function getRooster() {
    var rooster;
    user = JSON.parse(sessionStorage.getItem("login"));
    $.ajax({
        url: "rest/rooster/" + user.id,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            rooster = data;
        }
    });
    return rooster;


}

function setDate() {
    $("#date").empty();
    var Smonth = month_of_year[month];
    $("#date").append(year + " " + Smonth);
}

function makeCalender() {
    $("#Calender").empty();
    var rooster = getRooster();
    var calender = $("#Calender");
    var Smonth = month_of_year[month];
    setDate();

    d = new Date(date);
    d.setDate(0);
    calender.append("<div class='row'>");
    if(d.getDay() != 0){
        var t= new Date(date);
        t.setMonth(t.getMonth() - 1);
        tD = new Date(t.getFullYear(), t.getMonth() + 1, 0, 23, 59, 59);

        for( var g = d.getDay(); g > 0; g--){
            console.log( parseInt(tD.getDate()) - (5 - parseInt(g)));
            var temp =  parseInt(tD.getDate() - (parseInt(g))+1);
            calender.append(
                "<div class='tableSquare' style='background-color: #c1b9ba'>" +
                "<div class='dateHeader'>" +
                temp
                + "</div><div class='time'></div>"
                + "</div>");
        }
    }
    tdD = new Date(date.getFullYear(), date.getMonth() + 1, 0, 23, 59, 59);
    console.log("day =" + d.getDay());
    for (var i = d.getDay(); i < (tdD.getDate() + d.getDay()); i++) {
        if ((i % 7) == 0) {
            calender.append("</div>");
            calender.append("<div class='row'>");
        }
        input = ((i - d.getDay()) + 1);
        if(parseInt(input) < 10){
            input = "0" + input;
        }
        calender.append(
            "<div class='tableSquare'>" +
            "<div class='dateHeader'>" +
            ((i - d.getDay()) + 1)
            + "</div><div class='time' id='calender" + input + "'></div>"
            + "</div>")
    }
    for (data in rooster) {
        if (rooster[data].timeB_year == year) {
            if (month_of_year[rooster[data].timeB_month - 1] == Smonth) {
                $("#calender" + rooster[data].timeB_day).append(rooster[data].timeB_time + " - " + rooster[data].timeE_time);
            }
        }
    }
}

function nextCalender() {
    date.setMonth(date.getMonth() + 1)
    month = month + 1;
    if(month == 12){ month = 0; year = year + 1}
    makeCalender();
}
function previousCalender() {
    month = month - 1;
    date.setMonth(date.getMonth() - 1)
    if(month == -1){
        month = 11;
        year = year - 1;
    }
    makeCalender();
}