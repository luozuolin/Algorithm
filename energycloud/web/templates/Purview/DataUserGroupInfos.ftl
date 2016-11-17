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
                <h2>数据用户组信息管理</h2>
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
    //SELECT   numDataUserGroupID,varDataUserGroupName from WD_DataUserGroup
    $(document).ready(function () {
        $(document).foundation();
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numDataUserGroupID",text:"numDataUserGroupID" ,visible:false},
                { data: "varDataUserGroupName",text:"数据用户组名称" ,edit:{
                    type:"text"
                }},
                {
                    data: null,
                    text:'分配回路组',
                    orderable:false,
                    defaultContent: '<a   onclick="getCircuitGroup(this);">分配回路组</a>'
                },
                {
                    data: null,
                    text:'分配用户',
                    orderable:false,
                    defaultContent: '<a   onclick="getUser(this);">分配用户</a>'
                }
            ],
            dataurl:"/WD_DataUserGroup/read",
            //添加删除修改所在的页面
            updateurl:"/WD_DataUserGroup/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });
    function getCircuitGroup(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numDataUserGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numDataUserGroupID;
        $.post("/WD_DataUserGroup/getCircuitGroupBynumDataUserGroupID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlCircuitGroup(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateCircuitGroup",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }

    function gethtmlCircuitGroup(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            treehtml+="<ul><li isselected='"+(datas[i].selected=="-1"?"false":"true")+"' id='"+datas[i].numCircuitGroupID+"'>"+datas[i].varCircuitGroupName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
    function  updateCircuitGroup()
    {
        //  alert("updateFunction");
        $.post("/WD_DataUserGroup/updateDataUGRefCG" , {data:JSON.stringify({numDataUserGroupID:selectdata.numDataUserGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }
    function getUser(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numDataUserGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numDataUserGroupID;
        $.post("/WD_DataUserGroup/getUserBynumDataUserGroupID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlUser(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateuser",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function gethtmlUser(result,parent)
    {
        var treehtml="<ul><li isselected='false' id='-1'>总目录";
        var datas=result;
        for(var i=0;i<datas.length;i++)
        {
            treehtml+="<ul><li isselected='"+(datas[i].selected=="-1"?"false":"true")+"' id='"+datas[i].numUserID+"'>"+datas[i].varUserName+"</ul>";
        }
        treehtml+="</ul>";
        return treehtml;
    }
    function  updateuser()
    {
        //  alert("updateFunction");
        $.post("/WD_DataUserGroup/updateUserRefDataUG" , {data:JSON.stringify({numDataUserGroupID:selectdata.numDataUserGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }
</script>

