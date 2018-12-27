<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="assets/js/svg4everybody.min.js"></script>


    <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>

    <link rel="stylesheet" href="assets/css/styleLogin.css">

    <script>
        function sendUser() {
            var log = document.getElementById("username").value;
            var pass = document.getElementById("password").value;
            $.ajax({
                type: 'post',
                url: '/signIn',
                data: {
                    login: log,
                    password: pass
                }
            }).done(function (data) {
                if (data != 0)
                    window.location = "/home";
                else
                    alert("Неверный логин или пароль!");
            }).fail(function () {
                alert("Нет связи с сервером");
            });
        }
    </script>

</head>

<body>

<div class="wrapper">
    <div class="form-signin">
        <h2 class="form-signin-heading">Please login</h2>
        <input type="text" class="form-control" id="username" placeholder="Email Address" required="" autofocus="" />
        <input type="password" class="form-control" id="password" placeholder="Password" required=""/>
        <button class="btn btn-lg btn-primary btn-block" onclick="sendUser()">Login</button>
    </div>
</div>



</body>

</html>
