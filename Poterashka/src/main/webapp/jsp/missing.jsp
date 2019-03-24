<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="assets/js/svg4everybody.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Report missing</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/urku-ico.svg">
    <link rel="stylesheet" href="assets/css/aurora-pack.min.css">
    <link rel="stylesheet" href="assets/css/aurora-theme-base.min.css">
    <link rel="stylesheet" href="assets/css/urku.css">
</head>
<body class="top-fixed">
<header class="ae-container-fluid ae-container-fluid--full rk-header ">
    <input type="checkbox" id="mobile-menu" class="rk-mobile-menu">
    <label for="mobile-menu">
        <svg>
            <use xlink:href="assets/img/symbols.svg#bar"></use>
        </svg>
        <svg>
            <use xlink:href="assets/img/symbols.svg#bar"></use>
        </svg>
        <svg>
            <use xlink:href="assets/img/symbols.svg#bar"></use>
        </svg>
    </label>
    <div class="ae-container-fluid rk-topbar">
        <h1 class="rk-logo"><a href="index.html">Потеряшка<sup>tm</sup></a></h1>
        <nav class="rk-navigation">
            <ul class="rk-menu">
                <li class="rk-menu__item"><a href="/home" class="rk-menu__link">Home</a>
                </li>
                <li class="rk-menu__item"><a href="/missing" class="rk-menu__link">Report missing</a>
                </li>
                <li class="rk-menu__item"><a href="/profile" class="rk-menu__link">Profile</a>
                </li>
            </ul>
        </nav>
    </div>
</header>
<section class="ae-container-fluid ae-container-fluid--full">
    <c:if test="${true == false}">
    <header style="background-image: url('${order.item.image}');" class="rk-portfolio-cover">
    </header>
    <div class="ae-container-fluid">
        <div class="ae-grid ae-grid--collapse rk-portfolio-info ">
            <div class="ae-grid__item item-lg-8 item-sm--center au-xs-ta-center au-lg-ta-left">
                <h2 class="rk-portfolio-inner-title ">${order.item.itemName}</h2>
            </div>
            <div class="ae-grid__item item-lg-4 au-xs-ta-center au-lg-ta-right rk-portfolio-details">
                <p class="rk-portfolio-inner-client "><span
                        class="ae-u-bolder">Found in ${order.building.address}, ${order.building.buildingName}, ${order.location.locationName}</span>
                </p>
            </div>
        </div>
    </div>
    <div class="ae-container-fluid ae-container-fluid--inner rk-portfolio--inner">
        <div class="ae-grid ae-grid--collapse au-xs-ptp-1 au-xs-pbp-1">
            <div class="ae-grid__item item-lg-8 item-lg--offset-2">
                <h4 class="ae-u-bolder">Order description</h4>
                <p>${order.order.description}</p>

            </div>
        </div>
        <div class="ae-grid ae-grid--collapse au-xs-ptp-1 au-xs-pbp-1">
            <div class="ae-grid__item item-lg-8 item-lg--offset-2">
                <h4 class="ae-u-bolder">Item description</h4>
                <p>${order.item.description}</p>

            </div>
        </div>
        <c:if test="${order.locNow != null}">
            <script>
                function closeOrder(orderId) {
                    $.ajax({
                        type: 'post',
                        url: '/order',
                        data: {
                            action: 'close',
                            orderId: orderId
                        }
                    }).done(function (data) {
                        if (data == 1) {
                            alert("Order closed. Don't lose it again :)");
                            window.location = "/home";
                        } else {
                            alert("Somebody toke it :/");
                            window.location = "/home";
                        }
                    }).fail(function () {
                        alert("Ошибка, попробуйте позже");
                    });
                }
            </script>
            <div class="ae-grid ae-grid--collapse">
                <div class="ae-grid__item item-lg-6"></div>
                <div class="ae-grid__item item-lg-5 item-lg--offset-1 au-lg-ptp-1">
                    <div class="rk-comment__actions ae-kappa"><p style="font-size: 14pt">Now
                        in ${order.locNow.locationName} </p>
                        <button onclick="closeOrder(${order.order.orderId});" style="font-size: 16pt" class="ae-u-bold">
                            It's mine! I'l teke it!
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${order.locNow == null}">
            <div class="ae-grid ae-grid--collapse">
                <div class="ae-grid__item item-lg-6"></div>
                <div class="ae-grid__item item-lg-5 item-lg--offset-1 au-lg-ptp-1">
                    <div class="rk-comment__actions ae-kappa"><h3>I found it!</h3>
                        <p style="font-size: 14pt">I left it in </p>

                        <button onclick="changeOrder(${order.order.orderId});" style="font-size: 16pt"
                                class="ae-u-bold">you can take it.
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
        </c:if>
        <div class="ae-grid ae-grid--collapse au-xs-ptp-1 au-xs-pbp-1">
            <script>
                var isFound = false;
                function getItems() {
                    $.ajax({
                        type: 'post',
                        url: '/missingItemsAjax',
                        data: {
                            diap: 'u'
                        }
                    }).done(function (data) {
                        putItems(data);
                        isFound = false;
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                }
                function getAllItems() {
                    $.ajax({
                        type: 'post',
                        url: '/missingItemsAjax',
                        data: {
                            diap: 'a'
                        }
                    }).done(function (data) {
                        putItems(data);
                        isFound = true;
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                }

                function displayItem() {
                    var elemBuild = document.getElementById("items");
                    var val = elemBuild.options[elemBuild.selectedIndex].value;
                    $.ajax({
                        type: 'post',
                        url: '/missingItemsAjax',
                        data: {
                            diap: 'one',
                            itemId: val
                        }
                    }).done(function (data) {
                        document.getElementById("itemImage").innerHTML = "<img src='"+data[0].image+"'>";
                        document.getElementById("itemDescr").innerHTML = data[0].description;
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                }

                function putItems(data) {
                    var elemLoc = document.getElementById("items");
                    var locHTML = "";
                    for (var f = 0; f < data.length; f++) {
                        locHTML += "<option value='" + data[f].itemId + "'>" + data[f].itemName + "</option>";
                    }
                    elemLoc.innerHTML = locHTML;
                    displayItem();
                }

                getItems();
                function post() {
                    var elemItem = document.getElementById("items");
                    var itemId = elemItem.options[elemItem.selectedIndex].value;
                    var elemLoc = document.getElementById("buildingDP");
                    var loc = elemLoc.options[elemLoc.selectedIndex].value;
                    var elemDescr = document.getElementById("ordDescr");
                    var descr = elemDescr.value;
                    $.ajax({
                        type: 'post',
                        url: '/missing',
                        data: {
                            itemId: itemId,
                            loc: loc,
                            descr: descr
                        }
                    }).done(function (data) {
                        window.location = "/order?order=" + data.orderId;
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                }
                function createItem() {
                    var elemItem = document.getElementById("newItemName");
                    var itemName = elemItem.value;
                    var elemDescr = document.getElementById("newItemDescr");
                    var descr = elemDescr.value;
                    var elemImage = document.getElementById("newItemImage");
                    var image = elemImage.value;
                    $.ajax({
                        type: 'post',
                        url: '/createItemAjax',
                        data: {
                            itemName: itemName,
                            descr: descr,
                            founder: isFound,
                            image: image
                        }
                    }).done(function (data) {
                        if(isFound) getAllItems();
                        else getItems();
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                }
            </script>
            <div class="ae-grid__item item-lg-8 item-lg--offset-2">
                <div class="btn-group btn-group-toggle" data-toggle="buttons">
                    <label onclick="getItems()" class="btn btn-secondary active">
                        <input  type="radio" name="options" id="option1" autocomplete="off" checked> I lost
                    </label>
                    <label onclick="getAllItems()" class="btn btn-secondary">
                        <input  type="radio" name="options" id="option2" autocomplete="off"> I found
                    </label>
                </div>
            </div>
            <div class="ae-grid__item item-lg-8 item-lg--offset-2">
                <div class="btn-group btn-group-toggle" data-toggle="buttons">

                </div>
            </div>
        </div>
    </div>
        <div class="ae-container-fluid ae-container-fluid--inner rk-portfolio--inner">
            <div class="ae-grid ae-grid--collapse au-xs-ptp-1">
                <div class="ae-grid__item item-lg-5 au-lg-ptp-1">
                    <p class="ae-u-bolder">Chose item</p>
                    <select id="items" onchange="displayItem()"></select>
                    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                        create Item
                    </button>

                    <!-- Модальное окно -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel"></h4>
                                </div>
                                <div class="modal-body">
                                    <input type="text" id="newItemName" placeholder="* Name" required>
                                    <input type="text" id="newItemImage" placeholder="* Image link" required>
                                    <textarea placeholder="Description ..." id="newItemDescr" cols="30" rows="7"></textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" onclick="createItem()" class="btn btn-primary">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p class="ae-u-bolder">Description</p>
                    <p id="itemDescr"></p>
                </div>
                <div id="itemImage" class="ae-grid__item item-lg-6 item-lg--offset-1"></div>
            </div>
        </div>
        <div class="ae-grid ae-grid--collapse inner-box-3">
            <div class="ae-grid__item item-lg-8 item-lg--offset-2">
                <script>
                    function selectLocation() {
                        var elem = document.getElementById("locationDP");
                        var val = elem.options[elem.selectedIndex].value;
                        $.ajax({
                            type: 'post',
                            url: '/HomeLocationsAjax',
                            data: {
                                loc: val
                            }
                        }).done(function (data) {
                            putLoc(data);
                        }).fail(function () {
                            alert("Где вы? Мы такого места не знаем!");
                        });
                    }

                    function putBuildings(data) {
                        var elemBuild = document.getElementById("buildingDP");
                        var elemLoc = document.getElementById("locationDP");
                        var buildHTML = "";
                        var locHTML = "";
                        locHTML += "<option> Выберите </option>";
                        for (var i = 0; i < data.length; i++) {
                            buildHTML += "<option value='" + data[i].building.id + "'>" + data[i].building.buildingName + "</option>";
                            for (var f = 0; f < data[i].locations.length; f++) {
                                locHTML += "<option value='" + data[i].locations[f].locationId + "'>" + data[i].locations[f].locationName + "</option>";
                            }
                        }
                        elemBuild.innerHTML = buildHTML;
                        elemLoc.innerHTML = locHTML;
                    }

                    function putLoc(data) {
                        var elemLoc = document.getElementById("locationDP");
                        var locHTML = "";
                        for (var f = 0; f < data.length; f++) {
                            locHTML += "<option value='" + data[f].locationId + "'>" + data[f].locationName + "</option>";
                        }
                        elemLoc.innerHTML = locHTML;
                    }

                    function selectBuilding() {
                        var elemBuild = document.getElementById("buildingDP");
                        var val = elemBuild.options[elemBuild.selectedIndex].value;
                        $.ajax({
                            type: 'post',
                            url: '/HomeLocationsAjax',
                            data: {
                                bild: val
                            }
                        }).done(function (data) {
                            putBuildings(data);
                        }).fail(function () {
                            alert("Где вы? Мы такого места не знаем!");
                        });
                    }

                    $.ajax({
                        type: 'get',
                        url: '/HomeLocationsAjax',
                        data: {}
                    }).done(function (data) {
                        putBuildings(data);
                    }).fail(function () {
                        alert("Ошибка связи с сервером!");
                    });
                </script>
                <table>
                    <td class="rk-container">
                        <tr class="rk-menu__item"><select id="buildingDP" onchange="selectBuilding()"></select>
                        </tr>
                        <tr></tr>
                        <tr class="rk-menu__item"><select id="locationDP" onchange="selectLocation()"></select>
                        </tr>
                    </td>
                </table>
                <div action="#0" class="ae-form--full">
                    <textarea id="ordDescr" placeholder="Leave your comment here ..." cols="30" rows="7"></textarea>
                    <div class="au-pt-3 group-buttons"><a onclick="post()" class="au-mt-2 arrow-button arrow-button--right arrow-button--out">Post<span class="arrow-cont">
                  <svg>
                    <use xlink:href="assets/img/symbols.svg#arrow"></use>
                  </svg></span></a></div>
                </div>
            </div>
        </div>
        </ul>
</section>
<footer class="ae-container-fluid rk-footer ">
    <div class="ae-grid ae-grid--collapse">
        <div class="ae-grid__item item-lg-4 au-xs-ta-center au-lg-ta-left">
            <ul class="rk-menu rk-footer-menu">
                <li class="rk-menu__item"><a href="about.html" class="rk-menu__link">About</a>
                </li>
                <li class="rk-menu__item"><a href="documentation.html" class="rk-menu__link">Docs</a>
                </li>
                <li class="rk-menu__item"><a href="contact.html" class="rk-menu__link">Contact</a>
                </li>
            </ul>
            <p class="rk-footer__text rk-footer__copy "> <span class="ae-u-bold">© </span><span class="ae-u-bolder">2015 URKU PORTFOLIO </span>All Right Reserved.</p>
        </div>
        <div class="ae-grid__item item-lg-4 au-xs-ta-center"><a href="#0" class="rk-social-btn ">
            <svg>
                <use xlink:href="assets/img/symbols.svg#icon-facebook"></use>
            </svg></a><a href="#0" class="rk-social-btn ">
            <svg>
                <use xlink:href="assets/img/symbols.svg#icon-twitter"></use>
            </svg></a><a href="#0" class="rk-social-btn ">
            <svg>
                <use xlink:href="assets/img/symbols.svg#icon-pinterest"></use>
            </svg></a><a href="#0" class="rk-social-btn ">
            <svg>
                <use xlink:href="assets/img/symbols.svg#icon-tumblr"></use>
            </svg></a></div>
        <div class="ae-grid__item item-lg-4 au-xs-ta-center au-lg-ta-right">
            <p class="rk-footer__text rk-footer__contact "> <span class="ae-u-bold">Email: </span><span class="ae-u-bolder"> <a href="#0" class="rk-dark-color ">contact@urkuportfolio.com </a></span></p>
            <p class="rk-footer__text rk-footer__by">Theme by <a href="http://pixeden.com" class="ae-u-bolder">Pixeden.</a></p>
        </div>
    </div>
</footer>
<script src="assets/js/svg4everybody.min.js"></script>
<script>svg4everybody();</script>
</body>
</html>