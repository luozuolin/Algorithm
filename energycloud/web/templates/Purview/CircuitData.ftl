<!DOCTYPE html>
<html>
<head>
<#include "../encosaas/include/include.ftl">
</head>
<body>
<#include "../encosaas/include/header.ftl">
<div class="main">
<#include "../encosaas/include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>按回路组查询能耗</h2>
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-2 columns">
                            回路组
                        </div>
                        <div class="small-10 columns">
                            <input type="text" id="numCircuitGroupID" >
                        </div>
                        <div class="small-2 columns">
                            时间区间
                        </div>
                        <div class="small-10 columns">
                            <div id="daterange" ></div>
                        </div>
                        <div class="small-4 columns">
                            <div>
                                <button type="button" style="width: 100px"  class="button defaultbutton" id="btnsearch">查询</button>
                            </div>
                        </div>
                    </div>
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="row">
                            <div class="small-12 columns" >
                                <div>
                                    <button style="width: 100px"  type="button" id="btnshowcharts" class="button">曲线图</button>
                                    <button style="width: 100px"  type="button" id="btnshowdata" class="button">全部数据</button>
                                </div>
                            </div>
                        </div>
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
                { data: "numValue",text:"值"}
            ],
            dataurl:"/GetData/getCircuitDatasByDateRange",
            //reloadtable时需要传递参数
            "data":function(){
                return {numCircuitGroupID:$("#numCircuitGroupID").val()==null?"":$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()}
            }
        }).initdata();
        $("#btnshowcharts").click(function(){

            getchartdata();
        });
        $(window).scroll();
    });
    $("#btnsearch").click(function(){
        table.reloadtable();

    });
    function getchartdata()
    {
        $.post("/GetData/getCircuitDatasByDateRange/listAll" , {data:JSON.stringify({numCircuitGroupID:$("#numCircuitGroupID").val()==null?"":$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()})}, function (result) {

            if (result==null){
                zeroModal.alert({
                    content: '提示信息!',
                    contentDetail: '请选择回路后再进行操作',

                });
            };
            if (result==""){
                zeroModal.alert({
                    content: '提示信息!',
                    contentDetail: '查无数据',

                });
            }

            //行列转置
            var data=celltorow(result,"datStatistics","varCircuitGroupName","numValue");
            //使用json数据结构：http://c3js.org/reference.html#data-json
            c3.generate({
                bindto: '#divChart',
                padding: {
                    right: 25
                },
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


