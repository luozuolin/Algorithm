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
                <h2>能源实施</h2>
                <div class="space" style="height: calc(100% - 60px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            实施方案名称：
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varImplementName" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" style="width: 100px"  value="查询" class="button defaultbutton" id="btnsearch">
                        </div>


                    </div>
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
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
       // $(document).foundation();

//        $.post("/wd_implementManage/listAll" , null, function (result) {
//            var data = '<option value="-1">-请选择-</option>';
//            for (var i = 0; i < result.length; i++) {
//                data += '<option value="' + result[i].numImplementID + '">' + result[i].varImplementName + '</option>';
//            }
//            var id="numImplementID";
//            $("#"+id).parent().html('<select  id="'+id+'">'+data+'</select>');
//            $("#"+id).val(null);
//            $("#"+id).select2({multiple:"multiple"});
//        },'json');

        table=new $.quake.comp.datatables({

            id:"tableedit",
            columns:[
                { data: "numImplementID",text:"numImplementID",visible:false },

                { data: "varImplementName",text:"实施方案名称"},

                { data: "varImplementUser",text:"实施负责人"},
                { data: "varOrganizeName",text:"机构名称"},

                { data: "datStart",text:"实施开始时间",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                }
                },
                { data: "datEnd",text:"实施结束时间",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                }
                },
                { data: "numEffort",text:"预期节能(千瓦时)"},

                { data: "varPhone",text:"电话号码"},
                {
                    data: null,
                    text:'查看',
                    orderable:false,
                    defaultContent: '<a  href="#" onclick="downloadFile(this);">查看</a>'
                },

                { data: "numStatus",text:"实施状态",edit:{


                    type:"select",
                    init:function(obj,datas)
                    {
                        $("#numStatus").parent().html('<select class="form-control" id="numStatus"><option value="-1">Select...</option><option value="1"  id="1">提交</option><option value="2" id="2">实施</option><option value="3"  id="3">完成</option></select>');
                        if(datas!=null) {$("#numStatus").val(datas);}
                        $("#numStatus").select2();


                    }
                }},
                {
                    data: null,
                    text:'上传',
                    orderable:false,
                    defaultContent:'<form id="jvForm" method="post" enctype="multipart/form-data" ><input type="file" name="photo" onchange="uploadingUrl(this);"/></form>'


                }
            ],
            dataurl:"/wd_implementManage/read",
            //添加删除修改所在的页面
            //updateurl:"/wd_implementManage/edit/",
            selectstyle:"multi",
            "data":function(){
              //  return {numImplementID:$("#numImplementID").val()==null?"":$("#numImplementID").val().toString()};
                return {varImplementName:$("#varImplementName").val()==null?"":$("#varImplementName").val().toString()};

            }
        }).initdata();


        $(window).scroll();
    });

    $("#btnsearch").click(function(){
        table.reloadtable();
    });

    function uploadingUrl(a){
        var options = {
            url:"/WD_Uploading/fileupload",
            data:{numImplementID:table.table.DataTable.rows($(a).closest('tr')).data()[0].numImplementID},
            type:"post",
            dataType:"json",
            success:function(data){

                if (data!=null && data!=""){

                    _success(data.message);

                }
            }

        };
        $(a).parent().ajaxSubmit(options);


    }


    function downloadFile(e)
    {

        window.location.href="/WD_Uploading/download?numImplementID="+table.table.DataTable.rows($(e).closest('tr')).data()[0].numImplementID;
    }
    function _success(e) {
        zeroModal.success({
            title: '提示信息',
            content:e,
            width: '20ps',
            height: '15ps'


        });
    }

</script>