date = new Date();
month_of_year = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "Oktober", "November", "December"];
month = date.getMonth();
year = date.getFullYear();

function getRooster() {
    var rooster;
    user = JSON.parse(sessionStorage.getItem("login"));
    $.ajax({
        url: "http://localhost:8080/rest/rooster/" + user.id,
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (data) {
            rooster = data;
        }
    });
    return rooster;


}

function makeCalender() {
    $("#date").empty();
    $("#Calender").empty();
    var rooster = getRooster();
    var calender = $("#Calender");
    var Smonth = month_of_year[month];
    $("#date").append(year + " " + Smonth);
    calender.append("<div class='row'>");
    for (var i = 0; i < 31; i++) {
        if ((i % 7) == 0) {
            calender.append("</div>");
            calender.append("<div class='row'>");
        }
        calender.append(
            "<div class='tableSquare'>" +
            "<div class='dateHeader'>" +
            (i + 1)
            + "</div><div class='time' id='calender" + (i + 1) + "'></div>"
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

function next() {
    month = month + 1;
    if(month == 12){ month = 0; year = year + 1}
    makeCalender();
}
function previous() {
    month = month - 1;
    if(month == -1){
        month = 11;
        year = year - 1;
    }
    makeCalender();
}
