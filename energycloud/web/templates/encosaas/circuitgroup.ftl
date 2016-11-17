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
                <h2>回路组管理</h2>
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="small-1 columns">
                        回路组名称：
                    </div>
                    <div class="small-3 columns">
                        <input type="text" id="varCircuitGroup" >
                    </div>
                    <div class="small-4 columns">
                        <input type="button" style="width: 100px"  value="查询" class="button defaultbutton" id="btnsearch">
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
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numCircuitGroupID",text:"numCircuitGroupID",visible:false },

                { data: "varCircuitGroupName",text:"回路组名称" ,edit:{
                    type:"text"
                }},
                { data: "varSubComFlag",text:"是否标记",edit:{


                    type:"select",
                    init:function(obj,datas)
                    {
                        $("#varSubComFlag").parent().html('<select class="form-control" id="varSubComFlag"><option value="-1">Select...</option><option value="1"  id="1">是</option><option value="2" id="2">否</option></select>');
                        if(datas!=null) {$("#varSubComFlag").val(datas);}
                        $("#varSubComFlag").select2();


                    }
                }},
                { data: "numEnergyTypeId",text:"能源编号",visible:false },
                { data: "numEnergyTypeId",text:"能源编号",visible:false ,edit:{
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

                { data: "varEnergyTypeName",text:"能源名称"},

                { data: "numOrganizeID",text:"组织单位",visible:false ,edit:{
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

                { data: "varOrganizeName",text:"组织单位"},




                {
                    data: null,
                    text:'回路',
                    orderable:false,
                    defaultContent: '<a   onclick="getFuncUG(this);">添加回路</a>'
                },
//                {
//                    data: null,
//                    text:'添加数据用户组',
//                    orderable:false,
//                    defaultContent: '<a   onclick="addDataGroup(this);">添加数据用户组</a>'
//                },
                {
                    data: null,
                    text:'当月电量',
                    orderable:false,
                    defaultContent: '<a   onclick="lookTopological(this);">查看拓扑图</a>'
                }


            ],
            dataurl:"/WD_CircuitGroup/read",
            //添加删除修改所在的页面
            updateurl:"/WD_CircuitGroup/edit/",
            selectstyle:"multi",
            "data":function(){
                return {varCircuitGroupName:$("#varCircuitGroup").val().toString()}
            }
        }).initdata();
        $(window).scroll();
    });
    $("#btnsearch").click(function(){

        table.reloadtable();
    });
    /*  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    /*添加回路*/
    function getFuncUG(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numCircuitGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numCircuitGroupID;
        $.post("/WD_CircuitGroup/addCGCircuit" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlFuncUG(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateFuncUG",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function  updateFuncUG()
    {
        //  alert("updateFunction");
        $.post("/WD_CircuitGroup/updateCGCircuit" , {data:JSON.stringify({numCircuitGroupID:selectdata.numCircuitGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }

    function gethtmlFuncUG(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            //alert(datas[i].numCircuitGroupID);
            treehtml+="<ul><li isselected='"+(datas[i].numCircuitGroupID=="-1"?"false":"true")+"' id='"+datas[i].numCircuitID+"'>"+datas[i].varCircuitName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
    /*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/
    /*2.0添加数据用户组*/
    function addDataGroup(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numCircuitGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numCircuitGroupID;
        alert(data);
        $.post("/WD_CircuitGroup/addDataUGRefCg" , {data:JSON.stringify(data)}, function (result) {/*查询出所有的信息*/
            var treehtml=gethtmlDataGroup(result,null);
            alert(treehtml);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 300px;"></div>',ok:"updateDataGroup",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    /*确定按钮 路径*/
    function  updateDataGroup()
    {
        //  alert("updateFunction");
        $.post("/WD_CircuitGroup/updateDataUGRefCg" , {data:JSON.stringify({numCircuitGroupID:selectdata.numCircuitGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }
    /*显示下拉框*/
    function gethtmlDataGroup(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            //alert(datas[i].numCircuitGroupID);
            treehtml+="<ul><li isselected='"+(datas[i].numCircuitGroupID=="-1"?"false":"true")+"' id='"+datas[i].numDataUserGroupID+"'>"+datas[i].varDataUserGroupName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
    function  lookTopological(obj)
    {
        window.open("/FirstPage/svgpage?numCircuitGroupID="+table.table.DataTable.rows($(obj).closest('tr')).data()[0].numCircuitGroupID)
    }
</script>