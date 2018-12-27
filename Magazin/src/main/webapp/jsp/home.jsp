<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Shop</title>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <title>Shop</title>
    <style>
        h1{
            color: white;
        }

        h3{
            color: white;
        }

        td{
            color: white;
        }
        .bkg{
            background: #28343b; /* Old browsers */
            background: -moz-linear-gradient(top, #28343b 0%, #b5bdc8 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top, #28343b 0%,#b5bdc8 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom, #28343b 0%,#b5bdc8 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#28343b', endColorstr='#b5bdc8',GradientType=0 ); /* IE6-9 */   </style>
    <script>

        function sendProductId(productId) {
            $.ajax({
                type: 'post',
                url: '/home',
                data: {
                    id: productId
                }
            }).done(function (data) {
                getProductsInBasket();
            }).fail(function () {
                alert("Когда-то я не верил в магию, но сейчас кроме как магией я не могу объяснить работу функции request.getParameter()")
            });
        }

        function getProductsInBasket() {
            $.ajax({
                type: 'post',
                url: '/products.json',
                data: {
                }
            }).done(function (data) {
                let contentTableHTML = "<table>";
                for (let i = 0; i < data.length; i++) {
                    contentTableHTML += "<tr>";
                    contentTableHTML += "<td>" + data[i].name + "</td>";
                    contentTableHTML += "<td>x" + data[i].count + "</td>";
                    contentTableHTML += "</tr>";
                }
                contentTableHTML += "</table>";
                let contentTableDiv = document.getElementById("table");
                contentTableDiv.innerHTML = contentTableHTML;
            }).fail(function () {
                alert("Корзина не пришла");
            });
        }

        function exit() {
            var date = new Date(0);
            document.cookie = "auth=; path=/; expires=" + date.toUTCString();
            document.cookie = "baskId=; path=/; expires=" + date.toUTCString();
            window.location.replace("/home");
        }

        function run() {
            alert("Тебе не убежать от сессии.");
            exit();
        }

        // $.ajax({
        //     type: 'get',
        //     url: '/products.json',
        //     data: {
        //     }
        // }).done(function (data, data2) {
        //     let contentTableHTML = "<table>";
        //     for (let i = 0; i < data.length; i++) {
        //         contentTableHTML += "<tr>";
        //         contentTableHTML += "<td>" + data[i].name + "</td>";
        //         contentTableHTML += "<td>" + "<button onclick='sendProductId(" + data[i].id + ");\'>Добавить</button></td>";
        //         contentTableHTML += "</tr>";
        //     }
        //     contentTableHTML += "</table>";
        //     let contentTableDiv = document.getElementById("products");
        //     contentTableDiv.innerHTML = contentTableHTML;
        //     // getProductsInBasket();
        //
        //
        //     let contentTableHTML2 = "<table>";
        //     for (let i = 0; i < data2.length; i++) {
        //         contentTableHTML += "<tr>";
        //         contentTableHTML += "<td>" + data2[i].name + "</td>";
        //         contentTableHTML += "<td>x" + data2[i].count + "</td>";
        //         contentTableHTML += "</tr>";
        //     }
        //     contentTableHTML += "</table>";
        //     let contentTableDiv2 = document.getElementById("table");
        //     contentTableDiv2.innerHTML = contentTableHTML;
        // }).fail(function () {
        //     alert("Продуктов не завезли")
        // });
        //
        // function addItem(itemId) {
        //     var xhr = new XMLHttpRequest();
        //
        //     var body = JSON.stringify({
        //         'id': itemId
        //     });
        //     xhr.open("POST", '/home', true);
        //     xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        //     xhr.send(body);
        // }

        // module.exports = function(app, fs) {
        //     app.get('/info', (req, res) => {
        //         var url = require('url');
        //         var url_parts = url.parse(req.url, true);
        //         var query = url_parts.query;
        //         // console.log('Name ' + query['name']);
        // // console.log('Surname ' + query['surname'])
        //         fs.appendFile('data.txt', query['name'] +
        //             ' ' + query['surname'],
        //             + function (err) {
        //             if (err) throw err;
        //             console.log('Saved!');
        //             res.send();
        //         });
        //     });
        // };
    </script>
</head>
<body class="bkg" onclick="getProductsInBasket()">
<div>
    <h1 style="left: 0%">Подготовка к экзаменам</h1>
</div>
<div>
    <button onclick="exit()">Уйти</button>
    <button onclick="run()">Бежать!</button>

</div>
<div style="text-align: center" id="products">
    <table >
        <c:forEach items="${products}" var="product">
            <tr>
                <%--<form action="/home" method="post">--%>
                    <td name = "id" value="${product.id}">${product.name}</td>
                    <td>
                            <button onclick="sendProductId( ${product.id});">Добавить</button>
                    </td>
            </tr>
        </c:forEach>
    </table>
</div>
<h3>Корзина</h3>
<div id="table">
    <table >
        <c:forEach items="${productsInBasket}" var="product">
            <tr>
                    <%--<form action="/home" method="post">--%>
                <td>${product.name}</td>
                <td>x${product.count}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
