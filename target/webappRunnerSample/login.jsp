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
            <div onclick="changePage('index.jsp')">Home</div>
            <div onclick="changePage('Reserveren.jsp')">reserveren</div>
            <div class="selected" onclick="changePage('login.jsp')">Login</div>
        </div>
    </div>
</div>
<script>if (sessionStorage.getItem("login") != null) {
    user = sessionStorage.getItem("login");
    $.get("rest/login/" + user.username + "/" + user.password, function (data) {
        document.location.href = 'worker.jsp'

    });
}</script>
<div class="content">
    <div id="loginBlock">
        <div id="loginLeft">
            <div>username</div>
            <div>password</div>
        </div>
        <div id="loginRight">
            <div id="usernameBlock"><input type="text" id="username" placeholder="username"/></div>
            <div id="passwordBlock"><input type="password" id="password" placeholder="username"/></div>
        </div>
        <div id="error" style="color:red; display: none;"> wrong input</div>

        <input style="display: block; position: relative; left: 105px; width: 100px" id="login" type="button" value="login" onclick="login()">
    </div>
</div>


<script>
    function login() {
        $.get("rest/login/" + $("#username").val() + "/" + $("#password").val(), function (data) {
            if (data == null) {
                $("#error").css("display", "block")
            } else {
                sessionStorage.setItem("login", JSON.stringify(data));
                document.location.href = 'worker.jsp'
            }
        });
    }

    function changePage(page) {
        document.location.href = page;
    }

    function autoLogin() {
        if (sessionStorage.getItem("login") != null) {
            var user = JSON.parse(sessionStorage.getItem("login"));
            $.get("rest/login/" + user.username + "/" + user.password, function (data) {
                document.location.href = 'worker.jsp'

            });
        }
    }

</script>


</body>
</html>
