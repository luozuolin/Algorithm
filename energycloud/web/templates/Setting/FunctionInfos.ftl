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
                <h2>功能信息管理</h2>
                <div class="space" style="height: calc(100% - 60px);">
                    <div class="row">
                        <div class="small-1 columns">
                            功能代码
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="textvarFunctionCode" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" value="查询" class="button" id="btnsearch">
                        </div>
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
    //SELECT   f1.numFunctionID,f1.varFunctionCode,f1.varFunctionURL,f1.numPFunctionID,f2.varFunctionCode  varPFunctionCode from WD_Function f1 LEFT   join WD_Function  f2 on f1.numPFunctionID=f2.numFunctionID
    $(document).ready(function () {
            $(document).foundation();
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numFunctionID",text:"功能代码" ,visible:false},
                { data: "varFunctionCode",text:"功能代码" ,edit:{
                    type:"text"
                }},
                { data: "varFunctionURL",text:"功能地址" ,edit:{
                    type:"text"
                }},
                { data: "numPFunctionID",text:"父功能名称",visible:false  ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Function/listAll" , null, function (result) {
                            var data = '<option value="0">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numFunctionID + '">' + result[i].varFunctionCode + '</option>';
                            }
                            var id="numPFunctionID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "varPFunctionCode",text:"父功能名称"},
                {
                    data: null,
                    text:'分配功能用户组',
                    orderable:false,
                    defaultContent: '<a   onclick="getFuncUG(this);">分配功能用户组</a>'
                }
            ],
            dataurl:"/WD_Function/read",
            //添加删除修改所在的页面
            updateurl:"/WD_Function/edit/",
            selectstyle:"multi",
            "data":function(){

                return {varFunctionCode:$("#textvarFunctionCode").val()}
            }
        }).initdata();
        $("#btnsearch").click(function(){

            table.reloadtable();
        });
    });
    function getFuncUG(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numFunctionID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numFunctionID;
        $.post("/WD_Function/getFunctionUserGroupBynumFunctionID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlFuncUG(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateFuncUG",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function gethtmlFuncUG(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            treehtml+="<ul><li isselected='"+(datas[i].selected=="-1"?"false":"true")+"' id='"+datas[i].numFunctionUserGroupID+"'>"+datas[i].varFunctionUserGroupName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }

    function  updateFuncUG()
    {
        //  alert("updateFunction");
        $.post("/WD_Function/updateFuncUG" , {data:JSON.stringify({numFunctionID:selectdata.numFunctionID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }

</script>

<script>
    function refresh(){
        window.location.reload();//强制刷新
    }
</script>