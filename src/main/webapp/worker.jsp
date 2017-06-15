<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
</head>
<body>

<div class="header">

    <div id="Home" onclick="changePage('index.jsp')">Home</div>
    <div id="Rooster" onclick="changePage('rooster.jsp')">Home</div>
    <div id="Logout" onclick="logout()">logout</div>

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
            <div id="aangenomen"></div></div>
    </div>

    <script>
        var user = JSON.parse(localStorage.getItem("login"));
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

    }

    function changePage(page) {
        document.location.href = page;
    }

</script>


</body>
</html>
