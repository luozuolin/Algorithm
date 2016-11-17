<!DOCTYPE html>
<html>
<head>
<#include "include/include.ftl">
    <script>
        $(function(){
            $("img[id='load']").load(function(){
                $(this).hide();
                $(this).fadeIn(5000);
            });
        });
    </script
</head>
<body>
<#include "include/header.ftl">
<div class="main">
<#include "include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>能源计划</h2>
               <#--// <div class="space" style="height: calc(100% - 80px)">-->
                <div class="space" style="margin-left: auto;margin-right: auto;height:calc(100% - 80px)" >
                    <div class="row" style="margin-top:60px;margin-left:260px;margin-right:100px" >

                        <div class="small-3 columns">
                            <input type="button" id="varCircuit"  value="暖通空调" style="width: 150px;height: 50px" onclick="alertDatas(this);">
                        </div>
                        <div class="small-3 columns">
                            <input type="button" id="varCircuit" value="室内照明"  style="width: 150px;height: 50px">
                        </div>
                        <div class="small-3 columns">
                            <input type="button" id="varCircuit"value="直梯扶梯"  style="width: 150px;height: 50px" >
                        </div>
                        <div class="small-3 columns">
                            <input type="button" id="varCircuit" value="给排水"  style="width: 150px;height: 50px">
                        </div>


                    </div>

                    <div  style="margin-top:60px;margin-left:270px;margin-right:278px">
                        <img  src="/commpage/img/tt66.jpg"  id="load"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    function  alertDatas(obj) {
        //selectdata=table.table.DataTable.rows($(obj).closest('tr')).data()[0];
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="10"  cellspacing="0"  class=" datatable hover" ></table>',
            type:"alert"
        });
        new $.quake.comp.datatables({
            id:"tableedit1",
            columns:[
                { data: "varCircuitName",text:"一号楼"},
                { data: "varCircuitName",text:"二号楼"},
                { data: "varCircuitName",text:"三号楼"}


            ]
//            dataurl:"/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit",
            //reloadtable时需要传递参数
          //  "data":function(){
                //   debugger;
              //  return {numCircuitID:selectdata.numCircuitID,datStart:selectdata.datStatistics,datEnd:selectdata.datStatistics+" 23:59:59"}
        //    }
        }).initdata();

    }

</script>
</body>
</html>
