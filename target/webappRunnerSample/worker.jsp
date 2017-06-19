<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
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
            if(user.functie == "administrator"){
                $("#inrooster").css("display", "inline-block");
            }
        </script>
    </div>
</div>

<div class="content">
    <div id="worker">
        <div id="workerLeft">
            <div>naam</div>
            <div>functie</div>
            <div>geboorteDatum</div>
            <div>email</div>
            <div>adres</div>
            <div>aangenomen</div>
        </div>
        <div id="workerRight">
            <div id="name"></div>
            <div id="functie"></div>
            <div id="geboorteDatum"></div>
            <div id="email"></div>
            <div id="adres"></div>
            <div id="aangenomen"></div>
        </div>
    </div>

    <script>
        var user = JSON.parse(sessionStorage.getItem("login"));
        $("#name").append(user.voornaam + " " + user.achternaam);
        $("#functie").append(user.functie);
        $("#geboorteDatum").append(user.geboortedatum);
        $("#email").append(user.email);
        $("#adres").append(user.adres);
        $("#aangenomen").append(user.aangenomen);
    </script>
</div>


<script>

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
