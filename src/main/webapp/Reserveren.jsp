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
        <div class="Rosetti"><h1>Rosetti</h1></div>
        <div class="login" onclick="changePage('login.jsp')">Login</div>
    </div>

    <div class="hrefBlock">
        <div onclick="changePage('index.jsp')">Home</div>

        <div onclick="changePage('Reserveren.jsp')">reserveren</div>
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
        <textarea id='voornaam' name="voornaam" placeholder="voornaam"></textarea>
        <textarea id="achternaam" name="achternaam" placeholder="achternaam"></textarea>
        <textarea id="mail" name="email" placeholder="email"></textarea>
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

    <input type="button" name="submit" value="submit" onclick="postData()"/>
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
        var data = $("#voornaam").val() + "," + $("#achternaam").val() + "," + $("#mail").val() + "," + $("#personen").val() + "," + $("#date").val() + "," + $("#time").val();
        $.ajax({
            url: "https://ipassmartijnbakker.herokuapp.com/rest/reserveren/" + data,
            type: 'put',
            dataType: 'text'
        });
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
