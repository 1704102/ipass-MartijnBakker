<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
</head>
<body>

<div class="header">
    <div class="headerBlock">
        <div class="Rosetti"></div>

    </div>
    <div style="background-color: #cecece">
        <div class="hrefBlock">
            <div class="selected" onclick="changePage('index.jsp')">Home</div>
            <div onclick="changePage('Reserveren.jsp')">reserveren</div>
            <div class="login" onclick="changePage('login.jsp')">Login</div>
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
