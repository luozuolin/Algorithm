<!DOCTYPE html>
<html lang="en">
<head>
<#include "../Purview/include.ftl">
    <title>Main Page</title>
</head>
<body id="app"  >
<div class="title-bar" data-responsive-toggle="main-menu" data-hide-for="medium">
    <button class="menu-icon" type="button" data-toggle></button>
    <div class="title-bar-title">Menu</div>
</div>

<div class="top-bar" id="main-menu">
    <div class="top-bar-left">
        <ul class="dropdown menu" data-dropdown-menu>
            <li class="menu-text">能源云平台</li>
        </ul>
    </div>
    <div class="top-bar-right">
        <ul class="menu" data-responsive-menu="drilldown medium-dropdown">
            <li class="has-submenu">
                <a href="#">One</a>
                <ul class="submenu menu vertical" data-submenu>
                    <li><a href="#">One</a></li>
                    <li><a href="#">Two</a></li>
                    <li><a href="#">Three</a></li>
                </ul>
            </li>
            <li><a href="#">Two</a></li>
            <li><a href="#">Three</a></li>
        </ul>
    </div>
</div>

<div style="float:left;width: 200px;" id="divul">
    <ul style="background: black"  id="ulmenu" class="vertical accordion-menu menu" data-accordion-menu>
    </ul>
</div>
<div style="float:left;"  id="divtable">
</div>
</div>


</body>
</html>

<script>
    $(document).ready(function () {
        $(document).foundation();});
</script>