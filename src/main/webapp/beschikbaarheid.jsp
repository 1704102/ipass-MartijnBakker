<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rooster.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <style>
        .frame {
            display: block;
            border-radius: 10%;
            padding: 10px;
            background-color: #878787;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .day {
            display: inline-block;
            width: 100px;
        }

        .bRow {
            display: block;
        }

        .bRow div {
            display: inline-block;
        }

        .bRow div input {
            display: block;
            width: 100px;
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
        <div class="Rosetti"></div>
    </div>
    <div style="background-color: #cecece">
        <div class="hrefBlock">
            <div onclick="changePage('worker.jsp')">Home</div>
            <div onclick="changePage('rooster.jsp')">Rooster</div>
            <div class="selected" onclick="changePage('beschikbaarheid.jsp')">Beschikbaarheid</div>
            <div onclick="changePage('Reserveringen.jsp')">Reserveringen</div>
            <div id="inrooster" style="display: none" onclick="changePage('inrooster.jsp')">Inroosteren</div>
            <script>
                var user = JSON.parse(sessionStorage.getItem("login"));
                if (user.functie == "administrator") {
                    $("#inrooster").css("display", "inline-block");
                }
            </script>
            <div class="Logout" onclick="logout()">logout</div>
        </div>
    </div>
</div>

<div id="content">
    <div class="frame" style="display:block;">
        <div id="beschikbaarheidTable">
            <script>
                user = JSON.parse(sessionStorage.getItem("login"));
                $.get("rest/beschikbaarheid/" + user.id, function (data) {
                    for (var dat in data) {
                        $("#beschikbaarheidTable").append("<div class='bRow'><div class='day'>" + data[dat].day +
                            "</div><div><input id='" + data[dat].day + "-timeB' type='text' value='" + data[dat].timeB + "'>" + "</div>" +
                            "<div class='token'>" + "  -  " +
                            "</div><div><input id='" + data[dat].day + "-timeE' type='text' value='" + data[dat].timeE + "'>" + "</div>" + "</div>");
                    }
                })
            </script>

        </div>
        <input style="display: block; margin: auto" type="button" value="save" onclick="save()">
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
        for (var day in days_of_week) {

            data += "{";
            data += '"timeB"' + ':"' + $("#" + days_of_week[day] + "-timeB").val() + '",';
            data += '"timeE"' + ':"' + $("#" + days_of_week[day] + "-timeE").val() + '"';
            data += "}";
            if (timer != 6) {
                data += ",";
            }
            timer++;
        }
        data += "]";
        var user = JSON.parse(sessionStorage.getItem("login"));
        $.ajax({
            url: "rest/beschikbaarheid/save/" + data + "/" + user.id,
            type: 'put',
            dataType: 'text'
        });
    }
</script>


</body>
</html>
