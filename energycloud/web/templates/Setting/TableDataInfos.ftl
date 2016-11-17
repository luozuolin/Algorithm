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
                <h2>数据列表管理</h2>
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
                { data: "numDataID",text:"ID",visible:false},
                { data: "datBuild",text:"生成时间",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datBuild").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datBuild").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },
                { data: "numValue",text:"数值"  ,edit:{
                    type:"text"
                }},
                { data: "numTableID",text:"IP",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Tale/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numTableID + '">' + result[i].varIP + '</option>';
                            }
                            var id="numTableID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                 {data:"varIP",text:"IP"},

                { data: "numColID",text:"指标名称",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_ColumnInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numColID + '">' + result[i].varEName + '</option>';
                            }
                            var id="numColID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "varEName",text:"指标名称"}

            ],
            dataurl:"/WD_TableDataInfos/read",
            //添加删除修改所在的页面
            updateurl:"/WD_TableDataInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
        $(window).scroll();
    });

</script>


