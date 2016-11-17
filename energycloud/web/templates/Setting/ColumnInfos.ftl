<!DOCTYPE html>
<html lang="en">
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
                <h2>数据类型信息管理</h2>
                <div class="space" style="height: calc(100% - 60px);">
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
                { data: "numColID","orderable": true,visible:false},

                { data: "varEName",text:"英文名字" ,edit:{
                    type:"text"
                }},

                { data: "varCName",text:"中文名字" ,edit:{
                    type:"text"
                }},

                { data: "numForCircuit",text:"是否给回路使用",render: function ( data, type, full, meta ) {
                    return  data==1?"使用":"不使用";
                } ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $("#numForCircuit").parent().html('<select class="form-control" id="numForCircuit"><option value="-1">Select...</option><option value="1"  id="1">使用</option><option value="0" id="0">不使用</option></select>');
                        if(datas!=null) {$("#numForCircuit").val(datas);}
                        $("#numForCircuit").select2();


                    }
                }},

                { data: "numForGroup",text:"是否给回路组使用",render: function ( data, type, full, meta ) {
                    return  data==1?"使用":"不使用";
                } ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $("#numForGroup").parent().html('<select class="form-control" id="numForGroup"><option value="-1">Select...</option><option value="1"  id="1">使用</option><option value="0" id="0">不使用</option></select>');
                        if(datas!=null) {$("#numForGroup").val(datas);}
                        $("#numForGroup").select2();


                    }
                }},
                { data: "numColType",text:"列类型" ,edit:{
                    type:"text"
                }},
                { data: "numEnergyTypeId",text:"能耗ID",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_EnergyType/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numEnergyTypeId + '">' + result[i].varEnergyTypeName + '</option>';
                            }
                            var id="numEnergyTypeId";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varEnergyTypeName",text:"能耗名称"}



            ],
            dataurl:"/WD_ColumnInfos/read",
            //添加删除修改所在的页面
         //   updateurl:"/WD_ColumnInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    })

</script>


