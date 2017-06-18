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

        #date {
            display: block;
            background-color: white;
        }

        .time {
            display: table;
            margin: auto;
        }
        #topbar{
            width: 853px;
            height: 20px;
            background: white;
            display: table;
            margin:auto;
        }
        #previous{
            display: inline-block;
            width: 100px;
            height:20px;
        }
        #next{
            display: inline-block;
            width: 100px;
            height:20px;
        }
        #topbar div{
            text-align: center;
            display: inline-block;
            width:  652.8px;
            height: 20px;
        }
        #Calender{
            background-color: #878787; ;
            display: table;
            margin:auto;
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
    </div>
</div>

<div id="content">
    <div id="table">
        <div id="topbar"><input type="button" id="previous" value="previous" onclick="previous()">
            <div id="date"></div>
            <input id="next" type="button" value="next" onclick="next()"></div>
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
