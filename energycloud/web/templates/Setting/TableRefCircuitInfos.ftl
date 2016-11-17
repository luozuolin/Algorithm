<!DOCTYPE html>
<html lang="en">
<head>
<#include "../Purview/include.ftl">
    <title>表和回路管理</title>
</head>
<body id="app"  >
<#include "../Purview/menu.ftl">
<div style="float:left;padding: 10px"  id="divtable">
    <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
</div>
</body>
</html>
<script>
    var   table;
    $(document).ready(function () {
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numT2CID",text:"ID",visible:false},

                { data: "datStart",text:"生成时间",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datStart").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datStart").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },
                { data: "datEnd",text:"结束时间",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                    type:"datetime",
                    init:function(obj,datas)
                    {
                        $("#datEnd").dateRangePicker({
                            autoClose: true,
                            singleDate : true,
                            showShortcuts: true,
                            singleMonth: true,
                            language:'cn'
                        });
                        if(datas!=null) {$("#datEnd").val(moment(datas).format('YYYY-MM-DD'));}
                    }
                }
                },

                { data: "numTableID",text:"numTableID",visible:false ,edit:{
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

                { data: "numCircuitID",text:"回路名称",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/wd_circuitInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numCircuitID + '">' + result[i].varCircuitName + '</option>';
                            }
                            var id="numCircuitID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varCircuitName",text:"回路名称"}


            ],
            dataurl:"/wd_tableRefCircuit/read",
            //添加删除修改所在的页面
            updateurl:"/wd_tableRefCircuit/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });


</script>


