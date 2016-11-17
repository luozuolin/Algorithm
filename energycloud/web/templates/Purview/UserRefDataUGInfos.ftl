<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Main Page</title>
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
    //SELECT   r.numRefID,r.numUserID,u.varUserName,r.numDataUserGroupID,d.varDataUserGroupName from WD_UserRefDataUG
    $(document).ready(function () {
        $(document).foundation();
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numRefID",text:"numRefID" ,visible:false},
                { data: "numUserID",text:"用户编号" ,visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_User/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numUserID + '">' + result[i].varUserName + '</option>';
                            }
                            var id="numUserID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "varUserName",text:"用户编号"},
                { data: "numDataUserGroupID",text:"数据用户组编号" ,visible:false ,edit:{
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
                { data: "varDataUserGroupName",text:"数据用户组编号"}
            ],
            dataurl:"/WD_UserRefDataUG/read",
            //添加删除修改所在的页面
            updateurl:"/WD_UserRefDataUG/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });
</script>

