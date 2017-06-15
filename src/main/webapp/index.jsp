<html>
<head>
  <script src="script/jquery-3.2.1.min.js"></script>
  <link type="text/css" rel="stylesheet" href="css/template.css">
</head>
<body>

<div class="header">

  <div id="Home" onclick="changePage('index.jsp')">Home</div>
  <div id="Reserveren"onclick="changePage('Login.jsp')">Reserveren</div>

</div>

<script>
    function changePage(page) {
        document.location.href = page;
    }
</script>


</body>
</html>
