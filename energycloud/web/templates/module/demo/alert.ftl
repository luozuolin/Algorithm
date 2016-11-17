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
    <div class="float-center" style="width: 700px">
        <h3 class="text-center">Alert控件!! </h3>
        <br/>
        <div class="row" >
            <div class="large-2 columns" >  <input  type="button" style="width: 100px;" class="button success" value="success" onclick="showalert1();"></div>
            <div class="large-2 columns" >  <input  type="button" style="width: 100px;" class="button alert" value="alert" onclick="showalert2();"></div>
            <div class="large-2 columns" >  <input  type="button" style="width: 100px;" class="button" value="info" onclick="showalert3();"></div>
            <div class="large-2 columns" >  <input  type="button" style="width: 100px;" class="button warning" value="warning" onclick="showalert4();"></div>
            <div class="large-2 columns" >  <input  type="button" class="button success" style="width: 100px;" value="confirm" onclick="showconfirm();"></div>
        </div>
        <div class="row" id="chart" ></div>
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
    $().ready(function() {
        var chart = c3.generate({
            bindto: '#chart',
            data: {
                columns: [
                    ['data1', 30, 200, 100, 400, 150, 250],
                    ['data2', 50, 20, 10, 40, 15, 25]
                ]
            }
        });
    });
    //// 详细使用参见:http://longbill.github.io/jquery-date-range-picker/

    function showalert1()
    {
        $.quake.comp.alert({type:"success",infor:"操作成功"});


        // $.quake.comp.confirm({html:"",ok:"",cancle:""});
    }
    function showalert2()
    {
        $.quake.comp.alert({type:"alert",infor:"操作失败"});


    }
    function showalert3()
    {
        $.quake.comp.alert({type:"info",infor:"提示信息"});
    }
    function showalert4()
    {
        $.quake.comp.alert({type:"warning",infor:"产生告警"});
    }
    function showconfirm()
    {
        // $.quake.comp.alert({type:"success",infor:"操作成功"});
        $.quake.comp.confirm({html:"需要执行该操作吗?",ok:"ok",cancle:"cancle"});
    }
    function  ok() {
        alert("ok");
    }
    function cancle() {
        // alert("calcle");

    }
    function refresh(this){
        this.window.location.reload();//强制刷新
    }
</script>

