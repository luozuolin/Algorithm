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
                <h2>仪表信息管理</h2>
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
                { data: "numTableID",text:"ID", visible:false},

                { data: "varIP",text:"IP"  ,edit:{
                    type:"text"
                }},
                { data: "numPort",text:"端口"  ,edit:{
                    type:"text"
                }},
                { data: "numDeviceAddress",text:"装置地址"  ,edit:{
                    type:"text"
                }},
                { data: "numBandRate",text:"比率"  ,edit:{
                    type:"text"
                }},
                { data: "varStatus",text:"正常"},

                { data: "numEnergyTypeId",text:"能耗类型",visible:false,edit:{
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

                { data: "varEnergyTypeName",text:"能耗名称"},
                { data: "varCom",text:"接口"  ,edit:{
                    type:"text"
                }},
                { data: "numPOrganizeID",text:"组织名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Organize/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numPOrganizeID + '">' + result[i].varPOrganizeName + '</option>';
                            }
                            var id="numPOrganizeID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }

                }},
                { data: "varPOrganizeName",text:"组织名称"},
                { data: "varName",text:"指标值",edit:{
                    type:"text"
                }},
                {
                    data: null,
                    text:'分配回路',
                    orderable:false,
                    defaultContent: '<a   onclick="addCircuit(this);">分配回路</a>'
                }

            ],
            dataurl:"/WD_TableInfos/read",
            //添加删除修改所在的页面
            updateurl:"/WD_TableInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
        $(window).scroll();
    });
    function addCircuit(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numTableID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numTableID;
        $.post("/WD_TableInfos/addCircuitByNumTableID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlCircuit(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateCircuit",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function  updateCircuit()
    {
        //  alert("updateFunction");
        $.post("/WD_TableInfos/updateByNumTableID" , {data:JSON.stringify({numTableID:selectdata.numTableID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }

    function gethtmlCircuit(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            //alert(datas[i].numCircuitGroupID);
            treehtml+="<ul><li isselected='"+(datas[i].numTableID=="-1"?"false":"true")+"' id='"+datas[i].numCircuitID+"'>"+datas[i].varCircuitName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
</script>


