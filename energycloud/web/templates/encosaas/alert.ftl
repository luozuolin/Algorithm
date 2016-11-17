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
                <h2>数据预警</h2>
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
                            <button type="button" style="width: 100px;"  class="button"    id="btnexport">导出</button><#--onclick="downloadFile(this);"-->
                            <button type="button" style="width: 100px"  class="button defaultbutton" id="btnsearch">查询</button>                        </div>
                    </div>
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">

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
//        $("#btnshowcharts").click(function(){
//            $("#tableedit_wrapper").hide();
//            $("#divChart").show();
//        });
//        $("#btnshowdata").click(function(){
//            $("#tableedit_wrapper").show();
//            $("#divChart").hide();
//        });
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "varCircuitName",text:"回路名称"},
                { data: "datStart",text:"预警发生时间"},
                { data: "varMessage",text:"预警详细内容"},

            ],
            dataurl:"/wd_alarmLog/read",
            //reloadtable时需要传递参数
            "data":function(){
                return { numCircuitID:$("#numCircuitID").val()==null?"":$("#numCircuitID").val().toString(),start:$("#date-range200").val()==""?"":$("#date-range200").val(),end:$("#date-range201").val()==""?"":$("#date-range201").val()}

            }
        }).initdata();

       $(window).scroll();
    });
         $("#btnsearch").click(function(){
             table.reloadtable();

         });
//    function downloadFile(e) {
//
//        window.location.href ="/exportXls/uploads"
//    }
    $("#btnexport").click(function() {
        $.post("/wd_alarmLog/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll", {
            data: JSON.stringify({
                numCircuitID: $("#numCircuitID").val() == null ? "" : $("#numCircuitID").val().toString(),
                start: $("#date-range200").val() == "" ? "" : $("#date-range200").val(),
                end: $("#date-range201").val() == "" ? "" : $("#date-range201").val()
            })
        }, function (result) {
            var tablestr = "<tr><th style='border:solid 1px #000000;'>统计数据</th><th style='border:solid 1px #000000;'>回路名称</th><th style='border:solid 1px #000000;'>预警详细内容</th></tr>";
            // tablestr+="<tr><td style='border:solid 1px #000000;'  colspan='2'>统计数据</td><td style='border:solid 1px #000000;'>使用电量(千瓦时)</td></tr>";
            for (var i = 0; i < result.length; i++) {
                tablestr += "<tr>" + "<td style='border:solid 1px #000000;'>" + result[i].datStart + "</td>" + "<td style='border:solid 1px #000000;'>" + result[i].varCircuitName + "</td>" + "<td style='border:solid 1px #000000;'>" + result[i].varMessage + "</td>" + "</tr>";
            }
            var uri = 'data:application/vnd.ms-excel;base64,',
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
                    base64 = function (s) {
                        return window.btoa(unescape(encodeURIComponent(s)))
                    },
                    format = function (s, c) {
                        return s.replace(/{(\w+)}/g,
                                function (m, p) {
                                    return c[p];
                                })
                    };
            var ctx = {worksheet: "数据预警" || 'Worksheet', table: tablestr};
            window.location.href = uri + base64(format(template, ctx));
        }, 'json');
    });

</script>