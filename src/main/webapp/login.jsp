<html>
<head>
    <script src="script/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/template.css">
</head>
<body>

<div class="header">

    <div id="Home" onclick="changePage('index.jsp')">Home</div>
    <div id="Reserveren"onclick="changePage('reserveren.jsp')">Reserveren</div>

</div>

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
        <div id="error" style="color:red; display: none;"> wrong input </div>

        <input id="login" type="button" value="login" onclick="login()">
    </div>
</div>


<script>
    function login() {
        console.log("start");
        $.get("https://ipass-1704102.herokuapp.com/rest/login/" + $("#username").val() + "/" + $("#password").val(), function (data) {
            if(data == null){
                $("#error").css("display", "block")
            }else{
                localStorage.setItem("login", JSON.stringify(data));
                document.location.href('workerHome.jsp')
            }
        });
    }

    function changePage(page) {
        document.location.href = page;
    }

</script>


</body>
</html>
