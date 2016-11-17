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
                <h2>用户信息管理</h2>
                <div class="space" style="height: calc(100% - 60px);">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            用户名
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="textvarUserName" >
                        </div>
                        <div class="small-1 columns">
                            部门
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="textnumOrganizeID" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" value="查询" class="button defaultbutton" id="btnsearch">
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
    $(document).ready(function () {
        initOrgnaze();
         //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numUserID",text:"numUserID" ,visible:false},
                { data: "varUserName",text:"姓名" ,edit:{
                    type:"text"
                }},

                { data: "varLoginName",text:"登录名" ,edit:{
                    type:"text"
                }},
                { data: "varPassword",text:"密码" ,visible:false,edit:{
                    type:"text",
                    init:function(obj,datas)
                    {
                        $("#varPassword").parent().html('<input type="password"  id="varPassword"></input>');
                    }
                }},
                { data: "datCreate",text:"出生日期",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datCreate").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datCreate").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },
                { data: "numPositionID",text:"岗位编号" ,visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Position/getAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numPositionID + '">' + result[i].varPositionName + '</option>';
                            }
                            var id="numPositionID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "isadmin",text:"管理员",render: function ( data, type, full, meta ) {
                    return  data==1?"是":"否";
                } ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        var id="isadmin";
                        $("#"+id).parent().html('<select class="form-control" id="'+id+'"><option value="-1">Select...</option><option value="1"  id="1">是</option><option value="0" id="2">否</option></select>');
                        if(datas!=null) {$("#"+id).val(datas==1?1:0);}
                        $("#"+id).select2();
                    }
                }},
                { data: "varPositionName",text:"岗位名称"},
                {
                    data: null,
                    text:'功能用户组',
                    orderable:false,
                    defaultContent: '<a   onclick="getFuncUG(this);">分配功能用户组</a>'
                },
                {
                    data: null,
                    text:'部门',
                    orderable:false,
                    defaultContent: '<a   onclick="getOrganize(this);">分配部门</a>'
                },
                { data: "varOrganizeName",text:"部门" },
                { data: "numOrganizeID",text:"部门", visible:false}
            ],
            dataurl:"/WD_User/read",
            //添加删除修改所在的页面
            updateurl:"/WD_User/edit/",
            selectstyle:"multi",
            "data":function(){
              //  debugger
                console.log("queryjstree:"+(queryjstree==null?"":queryjstree.ids.toString()));
                return {varUserName:$("#textvarUserName").val(),numOrganizeID:queryjstree==null?"":queryjstree.ids.toString()}
            }
        }).initdata();
        $("#btnsearch").click(function(){
            table.reloadtable();
        });

    });
    var selectnumOrganizeID="";
    function  initOrgnaze()
    {
       // var  data={};
      //  data["numUserID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numUserID;
        $.post("/WD_Organize/getBynumUserID" , {data:JSON.stringify({})}, function (result) {
            var treehtml=gethtmlOrganize(result,null);
            //获取所有的树节点
         //   $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateOrganize",cancle:"cancle"});
            queryjstree= $.quake.comp.jstree({id:"textnumOrganizeID",treehtml:treehtml,multiple:false});
        },'json');
    }
    var queryjstree;
    //更新用户所在部门
    function getOrganize(e)
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numUserID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numUserID;
        $.post("/WD_Organize/getBynumUserID" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlOrganize(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateOrganize",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml,multiple:false});
        },'json');
    }
    //SELECT o.numOrganizeID, o.varOrganizeName,o.numPOrganizeID,IFNULL(u.numUserID,-1)  selected
    function gethtmlOrganize(result,parent)
    {
        if(parent==null)
        {
            parent={numOrganizeID:"0",varOrganizeName:"总目录",selected:"-1"};
        }
        var isselected=parent.selected=="-1"?"false":"true";
        var treehtml="<ul><li isselected='"+isselected+"' id='"+parent.numOrganizeID+"'>"+parent.varOrganizeName;
        var datas= result.filter(function (i,n){return i.numPOrganizeID==parent.numOrganizeID;});
        for(var i=0;i<datas.length;i++)
        {
            treehtml+=gethtmlOrganize(result,datas[i]);
        }
        treehtml+="</ul>";
        return treehtml;
    }
    function updateOrganize()
    {
        $.post("/WD_User/updateOrganize" , {data:JSON.stringify({numUserID:selectdata.numUserID,numOrganizeID:jstree.ids.toString()})}, function (result) {
        },'json');
    }
    function getFuncUG(e)   /*1*/
    {
        var  data={};
        selectdata=table.table.DataTable.rows($(e).closest('tr')).data()[0];
        data["numUserID"]=table.table.DataTable.rows($(e).closest('tr')).data()[0].numUserID;
        $.post("/WD_User/getFuncUG" , {data:JSON.stringify(data)}, function (result) {
            var treehtml=gethtmlFuncUG(result,null);
            //获取所有的树节点
            $.quake.comp.confirm({html:'<div id="divcontent"><input type="text" id="data4" style="width: 200px;"></div>',ok:"updateFuncUG",cancle:"cancle"});
            jstree= $.quake.comp.jstree({id:"data4",treehtml:treehtml});
        },'json');
    }
    function  updateFuncUG()/*1*/
    {
        //  alert("updateFunction");
        $.post("/WD_User/updateFuncUG" , {data:JSON.stringify({numUserID:selectdata.numUserID,ids:jstree.ids.toString()})}, function (result) {
        },'json');
    }

    function gethtmlFuncUG(result,parent)/*1*/
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
</script>


