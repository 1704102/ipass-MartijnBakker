<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rooster.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <style>
        #beschikbaarheidTable{
            display: table;
            margin: auto;
            background-color: white;
        }
        .day{
            display: inline-block;
            width: 100px;
        }
        .bRow {
            display: block;
        }
        .bRow div {
            display: inline-block;
        }
        .bRow div input{
            display: block;
            width:100px;
            text-align: center;
        }
        .token {
            text-align: center;
            display: inline-block;
            width: 20px;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="headerBlock">
        <div class="Rosetti"><h1>Rosetti</h1></div>
        <div class="Logout" onclick="logout()">logout</div>
    </div>

    <div class="hrefBlock">
        <div onclick="changePage('worker.jsp')">Home</div>
        <div onclick="changePage('rooster.jsp')">Rooster</div>
        <div onclick="changePage('beschikbaarheid.jsp')">Beschikbaarheid</div>
        <div id="inrooster" style="display: none" onclick="changePage('inrooster.jsp')">Inroosteren</div>
        <script>
            var user = JSON.parse(sessionStorage.getItem("login"));
            if(user.functie == "administrator"){
                $("#inrooster").css("display", "inline-block");
            }
        </script>
    </div>
</div>

<div id="content">
    <div id="beschikbaarheidTable">
        <script>
            user = JSON.parse(sessionStorage.getItem("login"));
            $.get("http://localhost:8080/rest/beschikbaarheid/" + user.id, function (data) {
                for (var dat in data) {
                    $("#beschikbaarheidTable").append("<div class='bRow'><div class='day'>" + data[dat].day +
                        "</div><div><input id='"+ data[dat].day + "-timeB' type='text' value='" + data[dat].timeB + "'>" + "</div>" +
                        "<div class='token'>" + "  -  " +
                        "</div><div><input id='"+ data[dat].day + "-timeE' type='text' value='"+ data[dat].timeE + "'>" + "</div>" + "</div>");
                }
            })
        </script>
        <input type="button" value="save" onclick="save()">
    </div>
</div>

<script>
    function changePage(page) {
        document.location.href = page;
    }
    function logout() {
        sessionStorage.removeItem("login")
        document.location.href = "index.jsp";
    }
    function save() {
        var days_of_week = ["Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag"];
        var data = "[";
        var timer = 0;
        for (var day in days_of_week){

            data += "{";
            data += '"timeB"' + ':"' + $("#" + days_of_week[day] + "-timeB").val() + '",';
            data +=  '"timeE"' + ':"' + $("#" + days_of_week[day] + "-timeE").val() + '"';
            data += "}";
            if(timer != 6){
                data += ",";
            }
            timer++;
        }
        data += "]";
        var user = JSON.parse(sessionStorage.getItem("login"));
        $.ajax({
            url: "http://localhost:8080/rest/beschikbaarheid/save/" + data + "/" + user.id,
            type: 'put',
            dataType: 'text'
        });
    }
</script>


</body>
</html>
