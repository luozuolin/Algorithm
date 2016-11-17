<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Vue Demo</title>
</head>
<#-- ========================================== -->
<#-- # Body Begin -->
<#-- ========================================== -->
<body id="app">
<div id="main-view" >
    <h3 class="text-center">下拉树形复选过滤框!! </h3>
    <div class="float-center row" style="width: 700px">

        <input type="text" id="data4" style="width: 200px;" class="large-6 columns">
        <input type="button" class="button large-3 columns" value="获取选中值"  onclick="getval();">
    </div>
</div>
<div id="calloutdiv" style="position: absolute;right:0px;width:400px;top:0px"></div>
</body>
</html>
<#-- ========================================== -->
<#-- # Style Begin -->
<#-- ========================================== -->
<style>
    #conent-view {
        width: 400px;
    }
</style>
<#-- ========================================== -->
<#-- # Script Begin -->
<#-- ========================================== -->
<script>
    var jstree1;
    $().ready(function() {
        jstree1=   $.quake.comp.jstree({id:"data4"});
    });
    function getval()
    {
        alert(jstree1.ids.join(', ')+"(" +jstree1.text.join(', ')+")");
    }
</script>

