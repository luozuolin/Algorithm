<!DOCTYPE html>
<html lang="en">
<head>
<#include "../encosaas/include/include.ftl">
    <title>Main Page</title>
</head>
<body id="app" style="background: url(/images/backgroud1.jpg);background-repeat:no-repeat;" >
<div id="main-view" class="callout" style=" background-color: rgba(111, 1, 211, 0); ">
    <div id="conent-view" class="float-center" style=" background-color: rgba(111, 1, 211, 0); ">
        <h3 class="text-center commoncolor" >欢迎 </h3>
        <br/>
        <div  id="app1">
            <fieldset class="callout" style=" background-color: rgba(111, 1, 211, 0); ">
                <legend >
                    <span class="commoncolor"> 登录</span>
                </legend>
                <label class="commoncolor"  for="varLoginName">用户名: </label>
                <input type="text" id="varLoginName">
                <label class="commoncolor" for="varPassword">密码: </label>
                <input type="password"  id="varPassword">
                <input  type="button" value="登录" class="button expanded defaultbutton" id="btnSubmit" >
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
</style>
<script>

    $(document).ready(function () {
        $(document).foundation();
    });
    $("#btnSubmit").click(function () {
        var  data={};
        data["varLoginName"]=$("#varLoginName").val();
        data["varPassword"]=hex_md5($("#varPassword").val());
        $.post("/login" , {data:JSON.stringify(data)}, function (result) {
            console.log(result);
            //登录是否成功
            if(result.login==true)
            {
                window.location.href = "/encosaas/firstpage";
            }
            else
            {
              $.quake.comp.alert({type:"alert",infor:"用户名或密码错误"});
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
