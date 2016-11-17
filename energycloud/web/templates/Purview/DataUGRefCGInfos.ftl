<!DOCTYPE html>
<html lang="en">
<head>
<#include "../Purview/include.ftl">
    <title>用能设备信息管理</title>
</head>
<body id="app"  >
<#include "menu.ftl">
<div style="float:left;padding: 10px"  id="divtable">
    <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
</div>
</body>
</html>
<script>
    var   table;
    $(document).ready(function () {
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numRefID",text:"numRefID",visible:false},
//                { data: "numDataUserGroupId",text:"用户组ID",visible:false},

                { data: "numDataUserGroupID",text:"输入回路",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_DataUserGroup/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numDataUserGroupID + '">' + result[i].varDataUserGroupName + '</option>';
                            }
                            var id="numDataUserGroupID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                {data:"varDataUserGroupName",text:"数据用户组名称"},

//                { data: "numCircuitGroupID",text:"回路组ID",visible:false},


                { data: "numCircuitGroupID",text:"输出回路",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_CircuitGroup/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numCircuitGroupID + '">' + result[i].varCircuitGroupName + '</option>';
                            }
                            var id="numCircuitGroupID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varCircuitGroupName",text:"回路组名称"}


            ],
            dataurl:"/wd_dataUGRefCG/read",
            //添加删除修改所在的页面
            updateurl:"/wd_dataUGRefCG/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });


</script>


