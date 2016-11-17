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
<div id="main-view" class="callout">
    <div class="float-center"  id="conent-view"  >
        <h3 class="text-center">时间选择控件!! </h3>
        <br/>
        <div class="row">详细config参考:http://longbill.github.io/jquery-date-range-picker/</div>
        <div class="row" >
            <div class="large-6 columns" > <input type="text" id="date1"></div>
            <div class="large-6 columns" >  <input  type="button" class="button" value="获取时间(date)" onclick="getdate1();"></div>
        </div>
        <div class="row" >
            <div class="large-6 columns "  >
                <div id="date2" ></div>
            </div>
            <div class="large-6 columns" ><input  type="button" class="button" value="获取时间(daterange)" onclick="getdate2()"></div>
        </div>
        <div class="row">
            <div class="large-6 columns" > <input type="text" id="date3"></div>
            <div class="large-6 columns" >  <input  type="button" class="button" value="获取时间(datetime)" onclick="getdate3();"></div>
        </div>
        <div class="row">
            <div class="large-6 columns" >
                <div id="date4" ></div>
            </div>
            <div class="large-6 columns" >  <input  type="button" class="button" value="获取时间(datetimerange)" onclick="getdate4();"></div>
        </div>
        <div class="row">
            <div class="large-6 columns" > <input type="text" id="date5"></div>
            <div class="large-6 columns" >  <input  type="button" class="button" value="获取时间(date with config)" onclick="getdate5();"></div>
        </div>
        <div class="row">
            <div class="large-6 columns" > <input type="text" id="date6"></div>
            <div class="large-6 columns" >  <input  type="button" class="button" value="获取时间(daterangepicker)" onclick="getdate6();"></div>
        </div>


    </div>
</div>
</div>
</body>
</html>
<#-- ========================================== -->
<#-- # Style Begin -->
<#-- ========================================== -->
<style>

    #conent-view {
        width: 600px;

    }
    /* add a 1px black border around every div */
    .daterangeselect { border:1px solid #000000; }

    /* add a 2px orange bottom border to every div */
    .daterangeselect { border-bottom:2px solid #f5791f; }

</style>
<#-- ========================================== -->
<#-- # Script Begin -->
<#-- ========================================== -->
<script>
    //
    $().ready(function() {
        //id,type,config,start,end
        // $.quake.comp.daterangepicker({id:"date1"});
        $('#date1').dateRangePicker();
        $.quake.comp.daterangepicker({
            id: "date2",
            type: "daterange",
            start: "date-range200",
            end: "date-range201"
        });

        $.quake.comp.daterangepicker({id: "date3", type: "datetime"});
        $.quake.comp.daterangepicker({
            type: "datetimerange",
            id: "date4",
            start: "date-range41",
            end: "date-range42"
        });

        $.quake.comp.daterangepicker({id: "date5", config: {format: "YYYY-MM", language: "en"}});
        var s = new $.quake.comp.daterangeselect({id: "date6", type: "today", event: dataselected});
    });

    $.quake.regReady(function () {
//        console.log("==========================");

    });
    function dataselected(obj) {
        $.quake.comp.alert({type:"success",infor:"触发回调函数:"+obj.idtostr[obj.data.type]+":"+obj.daterangestart+" - "+obj.daterangeend});
        // console.log("触发回调函数:"+obj.idtostr[obj.data.type]+":"+obj.daterangestart+" - "+obj.daterangeend);
    }
    function getdate1() {
        alert($("#date1").val());
    }
    function getdate2() {
        alert($("#date-range200").val()+","+$("#date-range201").val());
    }
    function getdate3() {
        alert($("#date3").val());
    }
    function getdate4() {
        alert($("#date-range41").val()+","+$("#date-range42").val());
    }
    function getdate5() {
        alert($("#date5").val());

    }
</script>

