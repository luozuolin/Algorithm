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
                <h2>系统拓扑图</h2>
                <div class="space" style="height: calc(100% - 60px);">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            回路组
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varCircuitGroupName" >
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
        $(document).foundation();
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numCircuitGroupID",text:"numCircuitGroupID" ,visible:false},
                { data: "varCircuitGroupName",text:"回路组" ,edit:{
                    type:"text"
                }},
                {
                    data: null,
                    text:'包含回路',
                    orderable:false,
                    defaultContent: '<a   onclick="alertCircuits(this);">查看</a>'
                },
                {
                    data: null,
                    text:'拓扑图',
                    orderable:false,
                    defaultContent: '<a   onclick="alertsvgpage(this);">查看</a>'
                }

            ],
            dataurl:"/WD_CircuitGroup/readByName",
            "data":function(){
                return {varCircuitGroupName:$("#varCircuitGroupName").val().toString()}

            }
        }).initdata();
    });
    $("#btnsearch").click(function(){
        table.reloadtable();
    });
    function  alertsvgpage(obj)
    {
        window.open("/FirstPage/svgpage?numCircuitGroupID="+table.table.DataTable.rows($(obj).closest('tr')).data()[0].numCircuitGroupID)
    }
    //弹出回路组下面的回路
    function  alertCircuits(obj) {
        var numCircuitGroupID = table.table.DataTable.rows($(obj).closest('tr')).data()[0].numCircuitGroupID;
        //获取所有的树节点
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="10" style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>',
            type:"alert"
        });
        var table1 = new $.quake.comp.datatables({
            id: "tableedit1",
            columns: [
                {data: "numCircuitID", text: "ID"},

                {
                    data: "varCircuitName", text: "回路名称", edit: {
                    type: "text"
                }
                },
                {
                    data: "varID", text: "varID", edit: {
                    type: "text"
                }
                },
                {data: "varOrganizeName", text: "上级组织名称"}
            ],
            dataurl: "/wd_circuitInfos/readBynumCircuitGroupID",
            "data": function () {
                return {numCircuitGroupID: numCircuitGroupID}
            }
        }).initdata();
    }

</script>

