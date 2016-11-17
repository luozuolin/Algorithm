<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Main Page</title>
</head>
<#-- ========================================== -->
<#-- # Body Begin -->
<#-- ========================================== -->

<body id="app">
<div class="top-bar-left">
    <ul class="dropdown menu" data-dropdown-menu>
        <li class="menu-text">Quake Foundation</li>
        <li class="has-submenu">
            <a href="#">演示</a>
            <ul class="submenu menu vertical" data-submenu>
                <!--
                <li><a href="/demo/vue">Vue</a></li>
                <li><a href="/demo/foundation">Foundation</a></li>
                <li><a href="/demo/database">Database</a></li>
                <li><a href="/demo/layout">Layout</a></li>
                <li><a href="/demo/charts">charts</a></li>
                <li><a href="/demo/chosen">chosen</a></li>
                <li><a href="/demo/datatrees">DataTree</a></li>-->
                <li><a href="/demo/datatablescomp">datatablescomp</a></li>
                <li><a href="/demo/daterangepickercomp">daterangepickercomp</a></li>
                <li><a href="/demo/alert">alert</a></li>
                <li><a href="/demo/svgpage">svgpage</a></li>
                <li><a href="/demo/datatrees">datatrees</a></li>
            </ul>
        </li>
        <li><a href="/health">健康</a></li>
        <li><a href="/metrics">指标</a></li>
    </ul>
</div>
</body>
</html>
<#-- ========================================== -->
<#-- # Style Begin -->
<#-- ========================================== -->
<style>

</style>
<#-- ========================================== -->
<#-- # Script Begin -->
<#-- ========================================== -->
<script>
    $(document).ready(function () {
        $(document).foundation();
    });
</script>
