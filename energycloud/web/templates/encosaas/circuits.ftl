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
                <h2>回路信息管理</h2>
                <div class="space" style="height: calc(100% - 80px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            回路名称：
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varCircuit" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" style="width: 100px"  value="查询" class="button defaultbutton" id="btnsearch">
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
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numCircuitID",text:"ID",visible:false},

                { data: "varCircuitName",text:"回路名称" ,edit:{
                    type:"text"
                }},
                { data: "varID",text:"变量值" ,edit:{
                    type:"text"
                }},
                { data: "numOrganizeID",text:"上级组织名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Organize/OrganizelistAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numOrganizeID + '">' + result[i].varOrganizeName + '</option>';
                            }
                            var id="numOrganizeID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varOrganizeName",text:"上级组织名称"},
                {
                    data: null,
                    text:'分时电量',
                    orderable:false,
                    defaultContent: '<a   onclick="DayEnergyConsumption(this);">查看当日分时电量</a>'
                },
                {
                    data: null,
                    text:'当月电量',
                    orderable:false,
                    defaultContent: '<a   onclick="monthEnergyConsumption(this);">查看当月能耗</a>'
                }


            ],
            dataurl:"/wd_circuitInfos/read",
            //添加删除修改所在的页面
            updateurl:"/wd_circuitInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {varCircuitName:$("#varCircuit").val().toString()}

            }
        }).initdata();
        $(window).scroll();
    });
    $("#btnsearch").click(function(){

        table.reloadtable();
    });
    function  DayEnergyConsumption(obj) {
        selectdata = table.table.DataTable.rows($(obj).closest('tr')).data()[0];
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="10"  cellspacing="0"  class=" datatable hover" ></table>',
            type: "alert"
        });
        new $.quake.comp.datatables({
            id: "tableedit1",
            columns: [
                {data: "varCircuitName", text: "回路名称"},
                {
                    data: "datBuild", text: "统计时间", "render": function (data, type, full, meta) {
                    return moment(data).format('YYYY-MM-DD HH:mm:ss');
                }
                },
                {data: "numValue", text: "使用电量(千瓦时)"}

            ],
            dataurl: "/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit",
            //reloadtable时需要传递参数
            "data": function () {
              //  debugger;
                return {
                    numCircuitID: selectdata.numCircuitID,
                    datStart: selectdata.datStatistics,
                    datEnd: selectdata.datStatistics + " 23:59:59"
                }
            }
        }).initdata();
    }
    function  monthEnergyConsumption(temp) {
        selectdata = table.table.DataTable.rows($(temp).closest('tr')).data()[0];
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="10"  cellspacing="0"  class=" datatable hover" ></table>',
            type: "alert"
        });
        new $.quake.comp.datatables({
            id: "tableedit1",
            columns: [
                {data: "varCircuitName", text: "回路名称"},
                {
                    data: "currentTime", text: "统计时间", "render": function (data, type, full, meta) {
                    return moment(data).format('YYYY-MM-DD HH:mm:ss');
                }
                },
                {data: "valuee", text: "使用电量(千瓦时)"}

            ],
            dataurl: "/statisticsCircuit/listByBeginningOfMonth",
            //reloadtable时需要传递参数
            "data": function () {
                return {
                    numCircuitID: selectdata.numCircuitID
//                    datStart: selectdata.datStatistics,
//                    datEnd: selectdata.datStatistics + " 23:59:59"
                }
            }
        }).initdata();
    }

</script>
