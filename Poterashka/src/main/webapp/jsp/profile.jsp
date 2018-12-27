<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Profile</title>
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
<style>
    .hash-link {
        position: relative;
    }
    .hash-link:before {
        content: '#';
        opacity: 0;
        position: absolute;
        right: 100%;
        transform: translatex(.25em);
        transition: .3s;
    }
    .hash-link:hover:before {
        opacity: 1;
        transform: translatex(0);
    }

    .layout-ctrl-input:checked + .demo-switch {
        color: #a0a0a0;
    }
    .demo-switch {
        cursor: pointer;
        display: none;
        height: 32px;
        transition: color .3s;
        width: 28px;
    }
    .demo-switch:hover {
        color: #a0a0a0;
    }
    .demo-switch svg {
        height: 100%;
        width: 100%;
    }

    @media (min-width: 40em) {
        .demo-switch {
            display: inline-block !important;
        }
    }

</style>
<section class="ae-container-fluid ae-container-fluid--full rk-main">
    <div class="item-inside__meta">
        <h1 class="ae-u-bolder rk-portfolio-title ">${user.name}</h1>
    </div>
    <div class="ae-container-fluid ae-container-fluid--inner">
        <div class="ae-grid ae-grid au-xs-ptp-1">
            <div class="ae-grid__item item-lg-12">
                <h2 id="portfolio-items"><a class="hash-link">Items</a>
                </h2>
                <p>There you can see your items. Select one to see where somebody found it or select one to make order.</p>
                <div class="ae-grid">
                    <c:forEach items="${items}" var="item">
                        <div class="ae-grid__item item-lg-4">
                            <c:if test="${item.order != null}">
                                <a href="/order?order=${item.order.orderId}" class="rk-item"><img src="${item.item.image}" alt="">
                                    <div class="item-meta">
                                    <h2>${item.item.itemName}</h2>
                                    <c:if test="${item.building != null && item.location != null}">
                                        <p>Found in ${item.building.buildingName}, ${item.location.locationName}</p>
                                    </c:if>
                                </div></a>
                            </c:if>
                            <c:if test="${item.order == null}">
                                <a href="/missing?${item.item.itemId}" class="rk-item"><img src="${item.item.image}" alt="">
                                    <div class="item-meta">
                                        <h2>${item.item.itemName}</h2>
                                        <p>Find it!</p>
                                    </div></a>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
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
<script src="assets/js/main.min.js"></script>
<script src="assets/js/pe-navscroll.min.js"></script>
<script>
    /// navScroll init
    peNavScroll.init({
        nav:'.rk-navigation',
        class:'active',
        duration: 500,
        hashChange: true,
        offset: 50,
        allLinks: true,
        easingFunction: easing.easeInOutCubic
    });

    //- Select Code
    var pres = document.querySelectorAll('pre');
    [].slice.call(pres).forEach(function(elem, index){
        pres[index].addEventListener('click', function(){
            SelectText(this)
        })
    })
</script>
</body>
</html>