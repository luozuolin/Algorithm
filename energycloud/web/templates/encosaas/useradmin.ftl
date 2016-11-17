<!DOCTYPE html>
<html>
<head>
<#include "include/include.ftl">
</head>
<body>
<#include "include/header.ftl">
<div class="main">
<#include "include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>用户管理</h2>
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            用户名称：
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varname" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" value="查询" class="button defaultbutton" id="btnsearch">
                        </div>
                        <#--<div style="float:left;width: 200px;" id="divul">-->
                            <#--<ul style="background: black"  id="ulmenu" class="vertical accordion-menu menu" data-accordion-menu>-->
                            <#--</ul>-->
                        <#--</div>-->

                        <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                            <table id="tableedit" data-page-length='10' style="width: 130%"  cellspacing="0"  class=" datatable hover" ></table>
                        </div>
                        <#--<div  style="margin-top:10px;margin-left:10px;margin-right:10px">-->
                            <#--<table id="tableedit" data-page-length='10' style="width: 140%"  cellspacing="0"  class=" datatable hover" ></table>-->
                        <#--</div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    var   table;
    $(document).ready(function () {


        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numUserID",text:"numUserID" ,visible:false},
                { data: "varUserName",text:"姓名" ,edit:{
                    type:"text"
                }},

                { data: "varLoginName",text:"登录名" ,edit:{
                    type:"text"
                }},
                { data: "datCreate",text:"出生日期",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datCreate").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datCreate").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },
                { data: "numPositionID",text:"岗位编号" ,visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Position/getAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numPositionID + '">' + result[i].varPositionName + '</option>';
                            }
                            var id="numPositionID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "varPositionName",text:"岗位名称"},

            ],
            dataurl:"/WD_User/read",
            //添加删除修改所在的页面
            updateurl:"/WD_User/edit/",
            selectstyle:"multi",
            "data":function(){
                return {varUserName:$("#varname").val().toString()}
            }
        }).initdata();
    });
    $("#btnsearch").click(function(){
        // getchartdata();
        table.reloadtable();
    });

</script>
