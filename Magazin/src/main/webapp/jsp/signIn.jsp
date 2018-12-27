<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script>
        function sendUser(){
            var f = Math.floor(Math.random() * 2) + 1;
            var login = $('.for-login').val();
            var pass = $('.for-pass').val();
            var type = "signIn";
            $.ajax({
                type: 'post',
                url: '/signIn',
                data: {
                    'login': login,
                    'password': pass,
                    'from': type
                }
            }).done(function (data) {
                if(data[0] == 1){
                    window.location.replace("/home");
                }else {
                    if(f == 1){
                        alert("Неверный логин");
                    }else
                        alert("Неверный пароль");
                }
            }).fail(function () {
                alert("Не удалось достучаться до сервера. Позвоните в серверную для решения проблемы. Ном. 8 800 555 35 35");
            });
        }

        $(document).ready(function(){
            $('input').keypress(function(e){
                if(e.keyCode==13)
                    sendUser();
            });
        });
    </script>
</head>
<body>
<h1>Вход</h1>
    <table>
        <tr>
            <td>Логин</td>
            <td>Пароль</td>
        </tr>
        <tr>
            <td><input type="text" class="for-login"></td>
            <td><input type="password" class="for-pass"></td>
        </tr>
        <tr>
            <td>
                <button name="send" onclick="sendUser()">SignIn</button>
            </td>
            <td>
                <a href="/signUp">SignUp</a>
            </td>
        </tr>
    </table>
</body>
</html>
