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
                <h2>功能用户组信息管理</h2>
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
        $(document).foundation();
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numFunctionUserGroupID",text:"numFunctionUserGroupID" ,visible:false},
                { data: "varFunctionUserGroupName",text:"功能用户组名称" ,edit:{
                    type:"text"
                }},
              {
                    data: null,
                    text:'分配功能',
                    orderable:false,
                    defaultContent: '<a   onclick="chooseFunction(this);">分配功能</a>'
                },
                {
                    data: null,
                    text:'选择用户',
                    orderable:false,
                    defaultContent: '<a   onclick="chooseUsers(this);">选择用户</a>'
                }
            ],
            dataurl:"/FunctionUserGroupInfos/read",
            //添加删除修改所在的页面
            updateurl:"/FunctionUserGroupInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });
    function  getinfo(e) {
        console.log(table1.table.DataTable.rows($(e).closest('tr')).data()[0]);
    }
    //分配功能
    var jstree,selectdata;
    function chooseFunction(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numFunctionUserGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numFunctionUserGroupID;
        $.post("/FunctionUserGroupInfos/getFunctionBynumFunctionUserGroupID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlFunc(result,null);
            //获取所有的树节点
             $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateFunction",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }

    function chooseUsers(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numFunctionUserGroupID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numFunctionUserGroupID;
        $.post("/FunctionUserGroupInfos/getUsersBynumFunctionUserGroupID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlUsers(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateUsers",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function  updateUsers()
    {
        //  alert("updateFunction");
        $.post("/FunctionUserGroupInfos/setUsers" , {data:JSON.stringify({numFunctionUserGroupID:selectdata.numFunctionUserGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }
    function  updateFunction()
    {
        $.post("/FunctionUserGroupInfos/setFunc" , {data:JSON.stringify({numFunctionUserGroupID:selectdata.numFunctionUserGroupID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }
    function gethtmlFunc(result,parent)
    {
        if(parent==null)
        {
            parent={numFunctionID:"0",varFunctionCode:"总目录",selected:"-1"};
        }
        var isselected=parent.selected=="-1"?"false":"true";
        var treehtml="<ul><li isselected='"+isselected+"' id='"+parent.numFunctionID+"'>"+parent.varFunctionCode;
       var datas= result.filter(function (i,n){return i.numPFunctionID==parent.numFunctionID;});
        for(var i=0;i<datas.length;i++)
        {
            treehtml+=gethtmlFunc(result,datas[i]);
        }
        treehtml+="</ul>";
        return treehtml;
    }
    function gethtmlUsers(result,parent)
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

</script>

