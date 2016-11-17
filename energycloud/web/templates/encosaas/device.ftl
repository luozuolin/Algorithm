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
                <h2>设备管理</h2>
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            设备名称：
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varDevice" >
                        </div>
                        <div class="small-4 columns">
                            <button type="button" style="width: 100px"  class="button defaultbutton" id="btnsearch">查询</button>
                        </div>
                    </div>
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
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
                { data: "numDeviceID",text:"ID",visible:false},

                { data: "varDeviceName",text:"用能设备名称",edit:{
                    type:"text"
                }},

                { data: "numInCircuitID",text:"输入回路ID",visible:false},

                { data: "numInCircuitID",text:"输入回路",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/wd_circuitInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numCircuitID + '">' + result[i].varCircuitName + '</option>';
                            }
                            var id="numInCircuitID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                {data:"varInCircuitName",text:"输入回路"},

                { data: "numOutCircuitID",text:"输出回路ID",visible:false},


                { data: "numOutCircuitID",text:"输出回路",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/wd_circuitInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numCircuitID + '">' + result[i].varCircuitName + '</option>';
                            }
                            var id="numOutCircuitID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varOutCircuitName",text:"回路名称"}


            ],
            dataurl:"/wd_userDevice/read",
            //添加删除修改所在的页面
            updateurl:"/wd_userDevice/edit/",
            selectstyle:"multi",
            "data":function(){
                return {varDeviceName:$("#varDevice").val().toString()}
            }
        }).initdata();
        $(window).scroll();
    });
    $("#btnsearch").click(function(){
        // getchartdata();
        table.reloadtable();
    });

</script>
