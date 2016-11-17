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
                <h2>能源报表</h2>
                <div class="space" style="height: calc(100% - 80px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            回路组
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="numCircuitGroupID" >
                        </div>
                        <div class="small-1 columns">
                            时间区间
                        </div>
                        <div class="small-3 columns">
                            <div id="daterange" ></div>
                        </div>
                        <div class="small-4 columns">
                            <input type="button" value="查询" class="button" id="btnsearch">
                        </div>

                    </div>
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div><button id="btnshowcharts" class="button">曲线图</button><button id="btnshowdata" class="button">全部数据</button> </div>
                        <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
                        <div id="divChart"/>
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
        $.post("/WD_CircuitGroup/listAll" , null, function (result) {
            var data = '<option value="-1">-请选择-</option>';
            for (var i = 0; i < result.length; i++) {
                data += '<option value="' + result[i].numCircuitGroupID + '">' + result[i].varCircuitGroupName + '</option>';
            }
            var id="numCircuitGroupID";
            $("#"+id).parent().html('<select  id="'+id+'">'+data+'</select>');
            $("#"+id).val(null);
            $("#"+id).select2({multiple:"multiple"});
        },'json');
        $.quake.comp.daterangepicker({
            id: "daterange",
            type: "daterange",
            start: "date-range200",
            end: "date-range201"
        });
        $("#btnshowcharts").click(function(){
            $("#tableedit_wrapper").hide();
            $("#divChart").show();
        });
        $("#btnshowdata").click(function(){
            $("#tableedit_wrapper").show();
            $("#divChart").hide();
        });
        //select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "varCircuitGroupName",text:"回路组"},
                { data: "datStatistics",text:"日期"},
                { data: "numvalue",text:"值"}
            ],
            dataurl:"/GetData/getCircuitDatasByDateRange",
            //reloadtable时需要传递参数
            "data":function(){
                return {numCircuitGroupID:$("#numCircuitGroupID").val().toString()==""?"11,16":$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val()==""?"2014-1-1":$("#date-range200").val(),end:$("#date-range201").val()==""?"2014-2-1":$("#date-range201").val()}
            }
        }).initdata();
        $("#btnshowcharts").click();
        //  {data:JSON.stringify({numFunctionUserGroupID:selectdata.numFunctionUserGroupID,ids:jstree.ids.toString()})}
        getchartdata();
    });
    $("#btnsearch").click(function(){
        table.reloadtable();
        getchartdata();
    });
    function getchartdata()
    {
        $.post("/GetData/getCircuitDatasByDateRange/listAll" , {data:JSON.stringify({numCircuitGroupID:$("#numCircuitGroupID").val().toString()==""?"11,16":$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val()==""?"2014-1-1":$("#date-range200").val(),end:$("#date-range201").val()==""?"2014-2-1":$("#date-range201").val()})}, function (result) {
            //行列转置
            var data=celltorow(result,"datStatistics","varCircuitGroupName","numvalue");
            //使用json数据结构：http://c3js.org/reference.html#data-json
            c3.generate({
                bindto: '#divChart',
                data: {
                    json:data.data,
                    keys: {
                        x: 'datStatistics', // it's possible to specify 'x' when category axis
                        value:data.cell
                    }
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                            format: '%Y-%m-%d'
                        }
                    }
                }
            });
        },'json');
    }
    function celltorow(json,row,cell,val) {
        var ret = {};
        for (var i = 0; i < json.length; i++)
        {
            if(ret[json[i][row]]==null)
                ret[json[i][row]]={};
            if( ret[json[i][row]][json[i][cell]]==null)
                ret[json[i][row]][json[i][cell]]={};
            ret[json[i][row]][json[i][cell]]=json[i][val];
        }
        var retArray=[];
        var cells={};
        for(var i in ret)
        {
            var data={};
            data[row]=i;
            for(var j in ret[i])
            {
                cells[j]=j;
                data[j]=ret[i][j];
            }
            retArray.push(data);
        }
        var cell=[];
        for(var i in cells)
        {
            cell.push(i);
        }
        ret={};
        ret["data"]=retArray;
        ret["cell"]=cell;
        return ret;
    }
</script>
