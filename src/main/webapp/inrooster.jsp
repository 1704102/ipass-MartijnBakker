<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/inrooster.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <style>
        #date {
            display: block;
            background-color: white;
        }

        #previous {
            display: inline-block;
            width: 80px;
            height: 20px;
        }

        #next {
            display: inline-block;
            width: 80px;
            height: 20px;
        }

        #topbar {
            width: 853px;
            height: 20px;
            background: white;
            display: table;
            margin: auto;
        }

        #topbar div {
            text-align: center;
            display: inline-block;
            width: 652.8px;
            height: 20px;
        }

        .week {
            display: inline-block;
        }

        .week div {
            text-align: center;
            width: 20px;
            height: 20px;
            display: inline-block;
            border: 1px solid black;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="headerBlock">
        <div class="Rosetti"><h1>Rosetti</h1></div>
        <div class="Logout" onclick="logout()">logout</div>
    </div>

    <div id="hrefBlock" class="hrefBlock">
        <div onclick="changePage('worker.jsp')">Home</div>
        <div onclick="changePage('rooster.jsp')">Rooster</div>
        <div onclick="changePage('beschikbaarheid.jsp')">Beschikbaarheid</div>
        <div id="inrooster" style="display: none" onclick="changePage('inrooster.jsp')">Inroosteren</div>
        <script>
            var user = JSON.parse(sessionStorage.getItem("login"));
            if (user.functie == "administrator") {
                $("#inrooster").css("display", "inline-block");
            }
        </script>
    </div>
</div>

<div class="content">
    <div style="display:block;">
        <div style="display: inline-block; background-color: #878787; text-align: center; width: 100%">
            Beschikbaar
        </div>
        <div id="topbar"><input type="button" id="previous" value="previous" onclick="previous()">
            <div id="date"></div>
            <input id="next" type="button" value="next" onclick="next()"></div>
    </div>
    <table style="display:inline-block;" id="table">
    </table>
    <div id="inroosterBlock" style="display: none; background-color: #878787; text-align: center;">
        <div style="display:block; text-align: center" id="editHeader"></div>
        <div style="display: block;text-align: center" id="editTime"></div>
        <div id="editValues">
            <input id="timeB" style=" display: inline-block; width: 200px;" type="text">
            <div style="display:inline-block; width: 20px"></div>
            <input id="timeE" style="display: inline-block; width: 200px;" type="text">
            <div style="display: none" id="id"></div>
        </div>
        <input style="display: block; width: 100%" type="button" value="save" onclick="saveData()">
    </div>
</div>

<script>

    makeCalender();
    createTable();

    function logout() {
        sessionStorage.removeItem("login")
        document.location.href = "index.jsp";
    }

    function changePage(page) {
        document.location.href = page;
    }

</script>


</body>
</html>
