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
                <h2>错误数据信息管理</h2>
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

                { data: "numErrorID",text:"编号",visible:false},

                { data: "varErrorType",text:"错误类型" ,edit:{
                    type:"text"
                }},
                { data: "varErrorDetail",text:"错误描述" ,edit:{
                    type:"text"
                }},

                { data: "datErrorDate",text:"出生日期",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datErrorDate").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datErrorDate").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },

                { data: "numColID",text:"错误项名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_ColumnInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numColID + '">' + result[i].varCName + '</option>';
                            }
                            var id="numColID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varCName",text:"错误项名称"}


            ],
            dataurl:"/WD_DataError/read",
            //添加删除修改所在的页面
            updateurl:"/WD_DataError/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });

</script>


