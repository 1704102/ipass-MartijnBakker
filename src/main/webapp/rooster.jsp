<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/rooster.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <style>
        .tableSquare {
            width: 120px;
            height: 120px;
            background-color: #8e8889;
            display: inline-block;
            border: 1px solid black;
        }

        .row {
            display: block;
        }

        .dateHeader {
            display: table;
            margin: auto;
        }

        #table {
            position: relative;
            top:50px;
            background-color: #cecece;
            display: block;
            border-radius: 10%;
            padding: 30px;
        }

        #date {
            display: block;
            background-color: white;
        }

        .time {
            display: table;
            margin: auto;
        }

        #topbar {
            width: 80%px;
            height: 20px;
            background-color: #878787;
            display: table;
            margin: auto;
        }

        #previous {
            float: left;
            width: 100px;
            height: 20px;
        }

        #next {
            float: right;
            width: 100px;
            height: 20px;
        }

        #topbar div {
            text-align: center;
            display: inline-block;
            width: 652.8px;
            height: 20px;
        }

        #Calender {
            background-color: #878787;;
            display: table;
            margin: auto;
        }

        .days {
            background-color: #878787;
            display: table;
            margin: auto;
        }

        .days div {
            text-align: center;
            border: 1px solid black;
            width: 120px;
            display: inline-block;
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
            <div class="selected" onclick="changePage('rooster.jsp')">Rooster</div>
            <div onclick="changePage('beschikbaarheid.jsp')">Beschikbaarheid</div>
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
    <div id="table">
        <div id="topbar"><input type="button" id="previous" value="previous" onclick="previousCalender()">
            <div id="date"></div>
            <input id="next" type="button" value="next" onclick="nextCalender()">
        </div>

        <div class="days">
            <div>Monday</div>
            <div>Tuesday</div>
            <div>Wednessday</div>
            <div>Tuesday</div>
            <div>Friday</div>
            <div>Saturday</div>
            <div>Sunday</div>
        </div>
        <div id="Calender"></div>
        <script>makeCalender()</script>
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
</script>


</body>
</html>
