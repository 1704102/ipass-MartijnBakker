<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <style>
        th{
            width: 200px;
            text-align: center;
        }
        td{
            width: 200px;
            text-align: center;
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
            <div onclick="changePage('beschikbaarheid.jsp')">Beschikbaarheid</div>
            <div class="selected" onclick="changePage('Reserveringen.jsp')">Reserveringen</div>
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

<div class="content">
   <table id="Reserveringen" style="position: relative; top: 20px; background-color: #cecece; border-radius: 10%; padding: 10px;">

        <script> $.get("rest/reserveren", function (data) {
            $("#Reserveringen").append("" +
            "<tr>" +
            "<th style='text-align: center;'>" + 'tafel' + "</th>" +
            "<th style='text-align: center;'>" + 'datum' + "</th>" +
            "<th style='text-align: center;'>" + 'aantal personen' + "</th>" +
            "<th style='text-align: center;'>" + 'naam' + "</th>" +
            "<th style='text-align: center;'>" + 'email' + "</th>" +
            "</tr>");
            for(dat in data){
                reservering = data[dat];
            $("#Reserveringen").append("" +
                "<tr>" +
                "<td>" + reservering.table + "</td>" +
                "<td>" + reservering.date + "</td>" +
                "<td>" + reservering.count + "</td>" +
                "<td>" + reservering.name + "</td>" +
                "<td>" + reservering.email + "</td>" +
                "</tr>")
        }})</script>
   </table>
</div>


<script>

    function logout() {
        sessionStorage.removeItem("login");
        document.location.href = "index.jsp";
    }

    function changePage(page) {
        document.location.href = page;
    }

</script>


</body>
</html>
