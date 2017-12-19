<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
</head>
<body>

<div class="header">
    <div class="headerBlock">
        <div class="Rosetti"></div>
        updated
    </div>
    <div style="background-color: #cecece">
        <div class="hrefBlock">
            <div class="selected" onclick="changePage('index.jsp')">Home</div>
            <div onclick="changePage('Reserveren.jsp')">reserveren</div>
            <div class="login" onclick="changePage('login.jsp')">Login</div>
        </div>
    </div>
</div>
<div class="content">
    <div style="display: block; padding: 35px; border: 1px solid black;background-color: #cecece; border-radius: 10%; margin: 35px;">
        Welcome bij de webpagina van Restaurant Rosetti<br>
        Rosetti is een restaurant gespecialiseerd in italiaanse gerechten.<br>
    </div>
    <div style="display: block; padding: 35px; border: 1px solid black; background-color: #cecece; border-radius: 10%; text-align: center; margin: 35px;">
        Openingstijden
        <div style="display:block;">
        <div style="display:inline-block;">
            Maandag <br>
            Dinsdag<br>
            Woensdag<br>
            Donderdag<br>
            Vrijdag<br>
            Zaterdag<br>
            Zondag<br>
        </div>
        <div style="display:inline-block;">
            17:00 - 23:00 <br>
            17:00 - 23:00<br>
            17:00 - 23:00<br>
            17:00 - 23:00<br>
            16:00 - 00:00<br>
            16:00 - 00:00<br>
            16:00 - 23:00<br>
        </div>
    </div>
</div>
</div>

<script>
    function changePage(page) {
        document.location.href = page;
    }
</script>


</body>
</html>
