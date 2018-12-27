<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Потеряшка</title>
    <link rel="icon" type="image/svg+xml" href="assets/img/urku-ico.svg">
    <link rel="stylesheet" href="assets/css/aurora-pack.min.css">
    <link rel="stylesheet" href="assets/css/aurora-theme-base.min.css">
    <link rel="stylesheet" href="assets/css/urku.css">

    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="assets/js/svg4everybody.min.js"></script>

    <script>

        function selectBuilding() {
            var elemBuild = document.getElementById("buildingDP");
            var val = elemBuild.options[elemBuild.selectedIndex].value;
            $.ajax({
                type: 'post',
                url: '/HomeLocationsAjax',
                data: {
                    bild : val
                }
            }).done(function (data) {
                putBuildings(data);
                getItemsInBuilding(val);
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
            for(var i = 0; i < data.length; i++){
                buildHTML += "<option value='"+data[i].building.id+"'>" + data[i].building.buildingName + "</option>";
                for (var f = 0; f < data[i].locations.length; f++){
                    locHTML += "<option value='"+data[i].locations[f].locationId+"'>" + data[i].locations[f].locationName + "</option>";
                }
            }
            elemBuild.innerHTML = buildHTML;
            elemLoc.innerHTML = locHTML;
        }

        function putLoc(data) {
            var elemLoc = document.getElementById("locationDP");
            var locHTML = "";
            for (var f = 0; f < data.length; f++){
                locHTML += "<option value='"+data[f].locationId+"'>" + data[f].locationName + "</option>";
            }
            elemLoc.innerHTML = locHTML;
        }

        function getItemsInBuilding(val) {
            $.ajax({
                type: 'post',
                url: '/HomeItemsAjax',
                data: {
                    bild : val
                }
            }).done(function (data) {
                printOrders(data);
            }).fail(function () {
                alert("");
            });
        }

        function getItemsInLocation(val) {
            $.ajax({
                type: 'post',
                url: '/HomeItemsAjax',
                data: {
                    loc : val
                }
            }).done(function (data) {
                printOrders(data);
            }).fail(function () {
                alert("");
            });
        }

        function selectLocation(){
            var elem = document.getElementById("locationDP");
            var val = elem.options[elem.selectedIndex].value;
            $.ajax({
                type: 'post',
                url: '/HomeLocationsAjax',
                data: {
                    loc : val
                }
            }).done(function (data) {
                putLoc(data);
                getItemsInLocation(val);
            }).fail(function () {
                alert("Где вы? Мы такого места не знаем!");
            });
        }

        $.ajax({
            type: 'get',
            url: '/HomeLocationsAjax',
            data: {
            }
        }).done(function (data) {
            putBuildings(data);
        }).fail(function () {
            alert("Где вы? Мы такого места не знаем!");
        });


        $.ajax({
            type: 'get',
            url: '/HomeItemsAjax',
            data: {
            }
        }).done(function (data) {
            printOrders(data)
        }).fail(function () {
            alert("Лента не пришла");
        });

        function openOrder(ordId) {
            window.location = "/order?order=" + ordId;
        };

        function printOrders(data) {
            let contentTableHTML = "";
            if (data.length > 0) {
                contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[0].item.image +")\"  class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-landscape \">\n" +
                    "            <div class=\"item-meta\">\n" +
                    "                <h2>" + data[0].item.itemName + "</h2>\n" +
                    "                <p>" + data[0].building.buildingName + ", " + data[0].location.locationName + "</p>\n" +
                    "            </div></a>"
            }
            if (data.length > 1) {
                contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[1].item.image +")\"  class=\"rk-item rk-item--flex rk-size-6  rk-portrait  \">\n" +
                    "            <div class=\"item-meta\">\n" +
                    "                <h2>"+ data[1].item.itemName+"</h2>\n" +
                    "                <p>" + data[1].building.buildingName + ", " + data[1].location.locationName + "</p>\n" +
                    "            </div></a>"
            }
            if (data.length > 2) {
                contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[2].item.image +")\"  class=\"rk-item rk-item--flex rk-size-6 rk-tosize-4 rk-portrait rk-tosquare\">\n" +
                    "            <div class=\"item-meta\">\n" +
                    "                <h2>"+ data[2].item.itemName+"</h2>\n" +
                    "                <p>" + data[2].building.buildingName + ", " + data[2].location.locationName + "</p>\n" +
                    "            </div></a>"
            }
            var n = 0;
            for(var i = 3; i < data.length; i++){
                var f = i - 3;
                var b1 = "";
                var b2 = "";
                if((f)%2 === 0){
                    if((f+2)%4 === 0){
                        contentTableHTML += "<div class=\"rk-items-cont rk-size-6 rk-tosize-8\"><a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex item-4 rk-size-12 rk-tosize-6 rk-landscape \">\n" +
                            "                <div class=\"item-meta\">\n" +
                            "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                            "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                            "                </div></a>";
                        i++;
                        if(i < data.length){
                            contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-landscape \">\n" +
                                "                <div class=\"item-meta\">\n" +
                                "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                                "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                                "                </div></a>";
                        }
                        contentTableHTML += "</div>";
                        i++;
                        if(i < data.length){
                            contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\"  class=\"rk-item rk-item--flex rk-size-6 rk-tosize-4 rk-portrait rk-tosquare\">\n" +
                                "            <div class=\"item-meta\">\n" +
                                "                <h2>"+ data[i].item.itemName+"</h2>\n" +
                                "                <p>" + data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                                "            </div></a>";
                        }
                    }else {
                        contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\"  class=\"rk-item rk-item--flex rk-size-6 rk-tosize-3 rk-portrait rk-tosquare\">\n" +
                            "            <div class=\"item-meta\">\n" +
                            "                <h2>"+ data[i].item.itemName+"</h2>\n" +
                            "                <p>" + data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                            "            </div></a>";
                        i++;
                        contentTableHTML += "<div class=\"rk-items-cont rk-size-6 rk-tosize-6\">";
                        if(i < data.length){
                            contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-landscape rk-tosquare\">\n" +
                                "                <div class=\"item-meta\">\n" +
                                "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                                "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                                "                </div></a>";
                            i++;
                            if(i < data.length){
                                contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-landscape rk-tosquare\">\n" +
                                    "                <div class=\"item-meta\">\n" +
                                    "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                                    "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                                    "                </div></a>";
                            }
                        }
                        contentTableHTML += "</div>";
                    }
                }else if((f - n)%3 === 0){
                    contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\"  class=\"rk-item rk-item--flex rk-size-8 rk-tosize-3 rk-square  \">\n" +
                        "            <div class=\"item-meta\">\n" +
                        "                <h2>"+ data[i].item.itemName+"</h2>\n" +
                        "                <p>" + data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                        "            </div></a>";
                    i++;
                    contentTableHTML += "<div class=\"rk-items-cont rk-size-4 rk-tosize-6\">";
                    if(i < data.length){
                        contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-square \">\n" +
                            "                <div class=\"item-meta\">\n" +
                            "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                            "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                            "                </div></a>";
                        i++;
                        if(i < data.length){
                            contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-square \">\n" +
                                "                <div class=\"item-meta\">\n" +
                                "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                                "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                                "                </div></a>";
                        }
                    }
                    contentTableHTML += "</div>";
                    n++;
                }else{
                    contentTableHTML += "<div class=\"rk-items-cont rk-size-4 rk-tosize-6\"><a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-square \">\n" +
                        "                <div class=\"item-meta\">\n" +
                        "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                        "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                        "                </div></a>";
                    i++;
                    if(i < data.length){
                        contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\" class=\"rk-item rk-item--flex rk-size-12 rk-tosize-6 rk-square  \">\n" +
                            "                <div class=\"item-meta\">\n" +
                            "                    <h2>"+data[i].item.itemName+"</h2>\n" +
                            "                    <p>"+ data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                            "                </div></a>";
                    }
                    contentTableHTML += "</div>";
                    i++;
                    if(i < data.length){
                        contentTableHTML += "<a onclick='openOrder("+data[0].order.orderId+")' style=\"background-image: url(" + data[i].item.image +")\"  class=\"rk-item rk-item--flex rk-size-8 rk-tosize-3 rk-square \">\n" +
                            "            <div class=\"item-meta\">\n" +
                            "                <h2>"+ data[i].item.itemName+"</h2>\n" +
                            "                <p>" + data[i].building.buildingName + ", " + data[i].location.locationName + "</p>\n" +
                            "            </div></a>";
                    }
                }
            }
            let contentTableDiv = document.getElementById("lostItems");
            contentTableDiv.innerHTML = contentTableHTML;
        }
    </script>
    <script>svg4everybody();</script>
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
        <h1 class="rk-logo"><a href="/home">Потеряшка<sup>tm</sup></a></h1>
        <nav class="rk-navigation">
            <ul class="rk-menu">
                <li class="active rk-menu__item"><a href="index.html" class="rk-menu__link">Home</a>
                </li>
                <li class="rk-menu__item"><a class="rk-menu__link">Locations</a>
                    <nav class="rk-menu__sub">
                        <ul class="rk-container">
                            <li class="rk-menu__item"><select id="buildingDP" onchange="selectBuilding()"></select></li>
                            <li class="rk-menu__item"><select id="locationDP" onchange="selectLocation()"></select></li>
                        </ul>
                    </nav>
                </li>
                <li class="rk-menu__item"><a href="/missing" class="rk-menu__link">report missing</a>
                </li>
                <li class="rk-menu__item"><a href="/profile" class="rk-menu__link">profile</a>
                </li>
            </ul>
            <form class="rk-search">
                <input type="text" placeholder="Search" id="urku-search" class="rk-search-field">
                <label for="urku-search">
                    <svg>
                        <use xlink:href="assets/img/symbols.svg#icon-search"></use>
                    </svg>
                </label>
            </form>
        </nav>
    </div>
</header>
<section class="ae-container-fluid rk-main">
    <div class="rk-layout-ctrl-mobile">
        <svg viewBox="0 0 9 9" class="layout-mob-1">
            <rect width="100%" height="100%" fill="currentColor"></rect>
        </svg>
        <svg viewBox="0 0 9 9" class="layout-mob-2">
            <rect width="100%" height="100%" fill="currentColor"></rect>
        </svg>
        <svg viewBox="0 0 9 9" class="layout-mob-3">
            <rect width="100%" height="100%" fill="currentColor"></rect>
        </svg>
        <svg viewBox="0 0 9 9" class="layout-mob-4">
            <rect width="100%" height="100%" fill="currentColor"></rect>
        </svg>
    </div>
    <section class="ae-container-fluid ae-container-fluid--inner loading rk-portfolio ">
        <div class="rk-portfolio__items" id="lostItems">
        </div>
    </section>
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
</body>
</html>