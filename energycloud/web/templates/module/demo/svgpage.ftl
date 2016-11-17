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
<div id="main-view">
    <div id="main-view" >
        <div id="svgid"></div>
    </div>
</div>
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
    $().ready(function() {

        $.ajax({
            url: '/resources/dongzhimenvarSysXml.xml',
            dataType: 'xml',
            success: function (data) {
                $.quake.comp.commdraw.draw(data);
                $.quake.comp.commdraw.initmeterdata('svgid');
            },
            error: function (e) {
                alert(e);
            }
        });
    });

    $.quake.regReady(function () {
//        console.log("==========================");

    });

</script>
