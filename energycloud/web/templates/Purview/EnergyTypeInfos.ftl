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
                <h2>能源类型信息管理</h2>
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
                { data: "numEnergyTypeId",text:"ID",visible:false},

                { data: "varEnergyTypeName",text:"能源类型" ,edit:{
                    type:"text"
                }},

//                {
//                    data: null,
//                    text:'能源类型',
//                    orderable:false,
//                    defaultContent: '<a   onclick="getCircuitGroup(this);">能源类型</a>'
//                }
            ],





            dataurl:"/WD_EnergyType/read",
            //添加删除修改所在的页面
            updateurl:"/WD_EnergyType/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
        $(window).scroll();

    });
//    function getCircuitGroup(e)
//    {
//        var  data={};
//        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
//        data["numEnergyTypeId"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numEnergyTypeId;
//        $.post("/WD_EnergyType/listAll" , {data:JSON.stringify(data)}, function (result) {
//            var treehtml=gethtmlCircuitGroup(result,null);
//            //获取所有的树节点
//            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateCircuitGroup",cancle:"cancle"});
//            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
//        },'json');
//    }
//    function gethtmlCircuitGroup(result,parent)
//    {
//        var treehtml="<ul><li isselected='false' id='-1'>总目录";
//        var datas=result;
//        for(var i=0;i<datas.length;i++)
//        {
//            treehtml+="<ul><li isselected='"+(datas[i].selected=="-1"?"false":"true")+"' id='"+datas[i].numEnergyTypeId+"'>"+datas[i].varEnergyTypeName+"</ul>";
//        }
//        treehtml+="</ul>";
//        return treehtml;
//    }
//    function  updateCircuitGroup()
//    {
//        //  alert("updateFunction");
//        $.post("/WD_EnergyType/updateDataUGRefCG" , {data:JSON.stringify({numEnergyTypeId:selectdata.numEnergyTypeId,ids:jstree.ids.toString()})}, function (result) {
//        },'json');
//    }

</script>


