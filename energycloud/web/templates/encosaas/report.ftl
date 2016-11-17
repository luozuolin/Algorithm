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
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-2 columns">
                            选择回路
                        </div>
                        <div class="small-10 columns">
                            <input type="text" id="numCircuitID" >
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
                                <button type="button" style="width: 100px;"  class="button"    id="btnexport">导出</button>
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
       // numCircuitID,varCircuitName
        $.post("/wd_circuitInfos/getCircuitsBynumUserID" , null, function (result) {
            var data = '<option value="-1">-请选择-</option>';
            for (var i = 0; i < result.length; i++) {
                data += '<option value="' + result[i].numCircuitID + '">' + result[i].varCircuitName + '</option>';
            }
            var id="numCircuitID";
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
                { data: "numCircuitID",text:"回路ID"},
                { data: "varCircuitName",text:"回路名称"},
                { data: "datStatistics",text:"统计数据"},
                { data: "numValue",text:"使用电量(千瓦时)"},
                {
                    data: null,
                    text:'分时电量',
                    orderable:false,
                    defaultContent: '<a   onclick="alertDatas(this);">查看当日分时电量</a>'
                }
            ],
            dataurl:"/GetData/getCircuitDatasByDateRangeAndnumCircuitGroupIDs",
            //reloadtable时需要传递参数
            "data":function(){
                return {numCircuitID:$("#numCircuitID").val()==null?"":$("#numCircuitID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()}
            }
        }).initdata();



        $("#btnshowcharts").click(function(){
             getchartdata();
        });
        $(window).scroll();
        //  {data:JSON.stringify({numFunctionUserGroupID:selectdata.numFunctionUserGroupID,ids:jstree.ids.toString()})}

    });
    function  alertDatas(obj) {
        selectdata=table.table.DataTable.rows($(obj).closest('tr')).data()[0];
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="5"  cellspacing="0"  class=" datatable hover" ></table><div id="divChart1"/>',
            type:"alert"
        });
        new $.quake.comp.datatables({
            id:"tableedit1",
            columns:[
                { data: "varCircuitName",text:"回路名称"},
                { data: "datBuild",text:"统计时间","render": function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD HH:mm:ss');
                }},
                { data: "numValue",text:"使用电量(千瓦时)"}

            ],
            dataurl:"/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit",
            //reloadtable时需要传递参数
            "data":function(){
                return {numCircuitID:selectdata.numCircuitID,datStart:selectdata.datStatistics,datEnd:selectdata.datStatistics+" 23:59:59"}
            }
        }).initdata();

        var  d= new Date();
        $.post("/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit/listAll" , {data:JSON.stringify({numCircuitID:selectdata.numCircuitID,datStart:selectdata.datStatistics,datEnd:selectdata.datStatistics+" 23:59:59"})}, function (result) {
            //行列转置
            var data=celltorow(result,"datBuild","varCircuitName","numValue");
            //使用json数据结构：http://c3js.org/reference.html#data-json
            c3.generate({
                bindto: '#divChart1',
                data: {
                    xFormat: '%Y-%m-%d %H:%M:%S',
                    json:data.data,
                    keys: {
                        x: 'datBuild', // it's possible to specify 'x' when category axis
                        value:data.cell
                    }
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                            format:'%H:%M',
                            count:12,
                            fit: true,
                            culling:{
                                max:5
                            }
                        }
                    }
                }
            });
        },'json');


    }
    $("#btnsearch").click(function(){

        table.reloadtable();
    });
    $("#btnexport").click(function(){
        $.post("/GetData/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll" , {data:JSON.stringify({numCircuitID:$("#numCircuitID").val()==null?"":$("#numCircuitID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()})}, function (result) {
            var tablestr="<tr><th style='border:solid 1px #000000;'>统计数据</th><th style='border:solid 1px #000000;'>回路名称</th><th style='border:solid 1px #000000;'>使用电量(千瓦时)</th></tr>";
           // tablestr+="<tr><td style='border:solid 1px #000000;'  colspan='2'>统计数据</td><td style='border:solid 1px #000000;'>使用电量(千瓦时)</td></tr>";
            for(var i=0;i<result.length;i++)
            {
                tablestr+="<tr>"+"<td style='border:solid 1px #000000;'>"+result[i].datStatistics+"</td>"+"<td style='border:solid 1px #000000;'>"+result[i].varCircuitName+"</td>"+"<td style='border:solid 1px #000000;'>"+result[i].numValue+"</td>"+"</tr>";
            }
            var uri = 'data:application/vnd.ms-excel;base64,',
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                    format = function(s, c) {
                        return s.replace(/{(\w+)}/g,
                                function(m, p) { return c[p]; }) };
            var ctx = {worksheet: "能源报表" || 'Worksheet', table: tablestr};
            window.location.href = uri + base64(format(template, ctx));
        },'json');
    });
    function getchartdata()
    {
        $.post("/GetData/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll" , {data:JSON.stringify({numCircuitID:$("#numCircuitID").val()==null?"":$("#numCircuitID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()})}, function (result) {
           if (result==null){

                   zeroModal.alert({
                       content: '操作提示!',
                       contentDetail: '请选择回路后再进行操作',

                   });


           }
            if (result==""){
                zeroModal.alert({
                    content: '提示信息!',
                    contentDetail: '查无数据',

                });
            }
            //行列转置
            var data=celltorow(result,"datStatistics","varCircuitName","numValue");
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
