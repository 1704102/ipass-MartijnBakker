<html>
<head>
    <title>website</title>
    <link rel="stylesheet" href="css/template.css" type="text/css">
    <link rel="stylesheet" href="css/Reserveren.css" type="text/css">
    <script src="script/jquery-3.2.1.min.js"></script>
</head>
<body>
<div class="header">
    <div class="headerBlock">
        <div class="Rosetti"></div>

    </div>
    <div style="background-color: #cecece">
        <div class="hrefBlock">
            <div onclick="changePage('index.jsp')">Home</div>
            <div class="selected" onclick="changePage('Reserveren.jsp')">reserveren</div>
            <div onclick="changePage('login.jsp')">Login</div>
        </div>
    </div>
</div>
<form class="form">
    <div style="display: block">Reservering</div>
    <div style="display: inline-block;">
        <label>Voornaam</label>
        <label>Achternaam</label>
        <label>Email</label>
        <label>aantal Personen</label>
        <label>Datum</label>
        <label>Tijd</label>
    </div>
    <div style="display: inline-block;">
        <input type="text" id='voornaam' name="voornaam" placeholder="voornaam">
        <input type="text" id="achternaam" name="achternaam" placeholder="achternaam">
        <input type="text" id="mail" name="email" placeholder="email">
        <select id="personen" name="personen">
            <script>
                for (var i = 1; i < 21; i++) {
                    document.write("<option value='" + i + "'>" + i + "</option>")
                }
            </script>
        </select>
        <input id="date" name="date" id="date" type="date">
        <select id="time" name="time">
            <script>
                for (var i = 0; i < 11; i++) {
                    var open = 1700;
                    var time = (open + (i * 50)).toString()
                    if (time.includes("50")) {
                        time = time.replace("50", ":30");
                    } else if (time == "2000") {
                        time = "20:00";
                    } else {
                        time = time.replace("00", ":00");
                    }
                    document.write("<option value='" + time + "'>" + time + "</option>")
                }
            </script>
        </select>

    </div>
    <div style="display: block;"></div>

    <input style="display: block" type="button" name="submit" value="submit" onclick="postData()"/>
    <div style="display: block" id="message"></div>
</form>


<script type="text/javascript">
    var date = new Date();

    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    if (month < 10) month = "0" + month;
    if (day < 10) day = "0" + day;

    var today = year + "-" + month + "-" + day;
    document.getElementById("date").value = today;
</script>
<script>
    function postData() {
        $("#message").empty();
        if ($("#voornaam").val() == "" || $("#achternaam").val() == "" || $("#mail").val() == "" || $("#personen").val() == "" || $("#date").val() == "" || $("#time").val() == "") {
            $("#message").append('<div style="display: block; color:red">vul alle velden in </div>')
        } else {
            var data = $("#voornaam").val() + "," + $("#achternaam").val() + "," + $("#mail").val() + "," + $("#personen").val() + "," + $("#date").val() + "," + $("#time").val();
            $.ajax({
                url: "rest/reserveren/" + data,
                type: 'put',
                dataType: 'text',
                success: function (data) {
                    if (data == "succes") {
                        $("#message").append('<div style="display: block; color:green">reservering verwerkt </div>')
                    } else if (data == "error") {
                        $("#message").append('<div style="display: block; color:red">alle tafels zijn bezet of er zijn niet genoeg plaatsen </div>')
                    }
                }

            });
        }
    }
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
