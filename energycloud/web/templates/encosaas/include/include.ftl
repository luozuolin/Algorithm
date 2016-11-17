<!--添加样式-->
<link href="/scss/quake/c3.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/clockpicker.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<!--
<link href="/scss/quake/pickmeup.css" rel="stylesheet" type="text/css"/>-->
<link href="/scss/quake/select2.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/jstree.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/foundation.min.css" rel="stylesheet" type="text/css"/>
<!--添加基本方法-->
<script src="/vendor/jquery/jquery.js"></script>
<script src="/vendor/jquery/jquery.form.js"></script>
<script src="/js/comm.js"></script>
<script src="/vendor/motion-ui/motion-ui.js"></script>
<script src="/vendor/what-input/what-input.js"></script>
<script src="/vendor/foundation/foundation.js"></script>
<script src="/vendor/vue/vue.js"></script>
<script src="/vendor/vuex/vuex.js"></script>
<script src="/vendor/dynamics/dynamics.js"></script>
<script src="/vendor/d3/d3.js"></script>
<script src="/vendor/c3/c3.js"></script>
<script src="/vendor/datatables/moment.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.select.min.js"></script>

<script src="/vendor/3rd/clockpicker/clockpicker.js"></script>
<script src="/vendor/3rd/daterangepicker/jquery.daterangepicker.js"></script>
<script src="/vendor/3rd/jstree/jstree.min.js"></script>
<script src="/vendor/3rd/pickmeup/jquery.pickmeup.js"></script>
<script src="/vendor/3rd/select2/select2.js"></script>



<script src="/javascript/selfcomp/alert.js"></script>
<script src="/javascript/selfcomp/chosen.js"></script>
<script src="/javascript/selfcomp/CommDraw.js"></script>
<script src="/javascript/selfcomp/confirm.js"></script>
<script src="/javascript/selfcomp/datatables.js"></script>
<!--
<script src="/javascript/selfcomp/date.js"></script>-->
<script src="/javascript/selfcomp/daterangepicker.js"></script>
<script src="/javascript/selfcomp/daterange.js"></script>
<script src="/javascript/selfcomp/daterangeselect.js"></script>
<script src="/javascript/selfcomp/commdraw.js"></script>
<script src="/javascript/selfcomp/initmeterdata.js"></script>
<script src="/javascript/selfcomp/jstree.js"></script>
<script src="/javascript/selfcomp/refreshstatus.js"></script>

<script src="/javascript/md5.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">


<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="shortcut icon" href="favicon.ico" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<link rel="stylesheet" type="text/css" href="/commpage/css/commpage.css"/>
<!--
<link rel="stylesheet" type="text/css" href="/commpage/css/base.css"/>

<link rel="stylesheet" type="text/css" href="/commpage/css/style.css"/>

<script src="/commpage/js/jquery1.42.min.js" type="text/javascript"></script>-->
<script src="/commpage/js/frameLayout.js" type="text/javascript"></script>
<script src="/commpage/js/sdmenu.js" type="text/javascript"></script>

<#--上传 下载提示框-->
<link rel="stylesheet" type="text/css" href="/zero/zeroModal.css" />
<script src="/zero/zeroModal.js" type="text/javascript"></script>
<!--拓扑图中的右键菜单-->
<link href="/commpage/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="/commpage/js/jquery-ui.min.js"></script>
<script src="/commpage/js/jquery.ui-contextmenu.js"></script>
<style>
    .date-picker-wrapper{z-index:2500 !important;}
    .ssss{z-index:2500 !important;}
    .date-picker-wrapper{z-index:2500 !important;}
    .chosen-with-drop{z-index:2600 !important;}
    .chosen-drop{z-index:2700 !important;}
</style>
<script>
    $(document).ready(function () {
        $(document).keydown(function(event){
            if (event.keyCode == 13)
            {
                event.returnValue=false;
                event.cancel = true;
                $(".defaultbutton").click();
            }
        });
        $("#logout").click(function () {
          /*  $.post("/admin/logout" , {data:null}, function (result) {
                console.log(result);
                //登录是否成功
                if(result.logout==true)
                {
                    window.location.href = "/encosaas/firstpage";
                }
                else
                {
                    alert("退出失败");
                    $("#main-view").css("display","none");
                }
            },'json');*/
            $.ajax({
                type: "post",
                url: "/admin/logout",
                data: null,
                dataType: "json",
                success: function(result) {
                    if(result.logout==true)
                    {
                        window.location.href = "/encosaas/firstpage";
                    }
                    else
                    {
                        alert("退出失败");
                        $("#main-view").css("display","none");
                    }
                },
                error: function (e) {
                    window.location.href = "/encosaas/firstpage";
                }
            });

        });


        //初始化用户权限
        $.ajax({
            type: "GET",
            async: false,
            url: "/rightlist",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function (data) {
                $("#my_menu").html("");
                for(var i=0;i<data.length;i++){
                    if(data[i].varFunctionURL==""){
                        $("#my_menu").append('<div id="func' + i + '"><span class="i1"><i></i>'+data[i].varFunctionCode+'</span><p id="funcl' + i + '"></p>');
                        for (var j = 0;j < data.length; j++){
                            if (data[j].numPFunctionID == data[i].numFunctionID) {
                                var right = data[j].varFunctionURL.split('_');
                                $("#funcl" + i + "").append(" <a  class='subitem' href='/" + right[0] + "/" + right[1] + "' target='_self'><i></i>" + data[j].varFunctionCode + "</a>");
                            }
                        }
                        if($("#funcl" + i + "").children().length==0)
                            $("#func"+i+"").remove();
                    }
                }
                myMenu = new SDMenu("my_menu");
                myMenu.init();
                $(".current").attr("class","subitem");
                if($("a[href$='"+location.pathname+"']").length==0)
                {
                  //  var firstSubmenu = myMenu.submenus[0];
                  //  myMenu.expandMenu(firstSubmenu);
                    myMenu.expandMenu(myMenu.submenus[0]);

                    if($(".subitem:first").length==0)
                    {
                        window.location.href ="/page/noright";
                    }
                    else
                    {
                        window.location.href =$(".subitem:first").attr("href");
                    }
                }
                else
                {
                    $("a[href$='"+location.pathname+"']").attr("class","current");
                    myMenu.expandMenu($("a[href$='"+location.pathname+"']").parent().parent()[0]);
                }

            },
            error: function (data) {

            }
        });

    });
</script>
<style>
    .datatable td{
        text-align:center;
    }
    .datatable th{
        text-align:center;
    }
</style>