<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Main Page</title>
</head>
<body id="app" style="background-image: url(/images/background.png)" >

<div  class="row fullWidth" style="background: #445664; ">
    <div class="small-8 columns">
        <ul class="dropdown menu"  data-dropdown-menu>
            <li class="menu-text" style="color: #00adee;float: left">Quake Foundation</li>
            <li class="has-submenu">
                <a href="#" >演示</a>
                <ul  class="submenu menu vertical" data-submenu>
                    <li><a href="/demo/datatablescomp" >datatablescomp</a></li>
                    <li><a href="/demo/daterangepickercomp" >daterangepickercomp</a></li>
                    <li><a href="/demo/alert" >alert</a></li>
                    <li><a href="/demo/svgpage" >svgpage</a></li>
                    <li><a href="/demo/datatrees" >datatrees</a></li>
                </ul>
            </li>
            <li><a href="/health" class="lia">健康</a></li>
            <li><a href="/metrics" class="lia">指标</a></li>
        </ul>
    </div>
    <div class="small-4 columns">
        <ul class="dropdown menu align-right" data-dropdown-menu>
        <li class="has-submenu">
            <a href="#" id="user">请登录</a>
            <ul  class="submenu menu vertical" data-submenu>
                <li><a href="/admin/logout" id="logout" >退出</a></li>
                <li><a href="/demo/" >重新登录</a></li>
            </ul>
        </li>
    </ul>
    </div>


</div>
<div id="main-view" class="callout" style=" background-color: rgba(111, 1, 211, 0); ">
    <div id="conent-view" class="float-center" style=" background-color: rgba(111, 1, 211, 0); ">
        <h3 class="text-center commoncolor" >欢迎来到java世界!! </h3>
        <br/>
        <div  id="app1">
            <fieldset class="callout" style=" background-color: rgba(111, 1, 211, 0); ">
                <legend >
                    <span class="commoncolor"> 登录（用户名密码(wode,wode),(root,root)）</span>
                </legend>
                <label class="commoncolor"  for="email">用户名: </label>
                <input type="text" id="email">
                <label class="commoncolor" for="password">密码: </label>
                <input type="text"  id="password">
                <input  type="button" value="登录" class="button expanded" id="btnSubmit" >
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
<style>
    #main-view {
        border: 0;
        vertical-align: middle;
    }
    #conent-view {
        width: 400px;

    }
    .commoncolor{
        color: white;
    }
    .fullWidth {
        width: 100%;
        margin-left: auto;
        margin-right: auto;
        max-width: initial;
    }
</style>
<script>
    $(document).ready(function () {
        $(document).foundation();
    });
    $("#btnSubmit").click(function () {
        var  data={};
        data["id"]=$("#email").val();
        data["password"]=$("#password").val();
        $.post("/admin/login" , {data:JSON.stringify(data)}, function (result) {
            console.log(result);
            //登录是否成功
            if(result.login==true)
            {
              //  alert("登录成功");
                $("#user").html(result.id);
                $("#main-view").css("display","none");
            }
            else
            {
              //  alert("登录失败");
                $("#main-view").css("display","block");
            }
        },'json');
    });
    $("#logout").click(function () {
        $.post("/admin/logout" , {data:null}, function (result) {
            console.log(result);
            //登录是否成功
            if(result.logout==true)
            {
                alert("退出成功");
                $("#user").html("请登录");
                $("#main-view").css("display","block");
            }
            else
            {
                alert("退出失败");
                $("#main-view").css("display","none");
            }
        },'json');
    });
</script>
