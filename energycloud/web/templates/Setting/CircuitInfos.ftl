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
                <h2>回路信息管理</h2>
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
                    text:'分配仪表',
                    orderable:false,
                    defaultContent: '<a   onclick="getFuncUG(this);">分配仪表</a>'
                }


            ],
            dataurl:"/wd_circuitInfos/read",
            //添加删除修改所在的页面
            updateurl:"/wd_circuitInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
        $(window).scroll();
    });
    function getFuncUG(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numCircuitID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numCircuitID;
        $.post("/WD_TableInfos/listAll" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlFuncUG(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateFuncUG",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function  updateFuncUG()
    {
        //  alert("updateFunction");
        $.post("/WD_TableInfos/updateCGCircuit" , {data:JSON.stringify({numCircuitID:selectdata.numCircuitID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }

    function gethtmlFuncUG(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            //alert(datas[i].numCircuitGroupID);
            treehtml+="<ul><li isselected='"+(datas[i].numCircuitID=="-1"?"false":"true")+"' id='"+datas[i].numTableID+"'>"+datas[i].varName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
</script>


