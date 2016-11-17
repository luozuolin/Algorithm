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
                <h2>节能实施管理</h2>
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
                { data: "numImplementID",text:"numImplementID",visible:false },

                { data: "varImplementName",text:"实施方案名称" ,edit:{
                    type:"text"
                }},

                { data: "varImplementUser",text:"实施负责人",edit:{
                    type:"text"
                }},
                { data: "numOrganizeID",text:"机构名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Organize/OrganizelistAll" , null, function (result) {
                            var data = '<option value="0">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numOrganizeID + '">' + result[i].varOrganizeName + '</option>';
                            }
                            var id="numOrganizeID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varOrganizeName",text:"机构名称"},

                { data: "datStart",text:"实施开始时间",render: function ( data, type, full, meta ) {
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
                { data: "datEnd",text:"实施结束时间",render: function ( data, type, full, meta ) {
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
                { data: "numEffort",text:"预期节能(千瓦时)",edit:{
                    type:"text"
                }},

                { data: "varPhone",text:"电话号码",edit:{
                    type:"text"
                }},
                {
                    data: null,
                    text:'查看',
                    orderable:false,
                    // defaultContent: '<a href="/WD_Uploading/download?numImplementID="+table.table.DataTable.rows($(e).closest('tr')).data()[0].numImplementID">查看</a>'
                    //defaultContent: '<a href="#" onclick="downloadFile(this);return false;">查看</a>'
                    //defaultContent: '<a id="uploadD" href="#"; onclick="downloadFile(this);">查看</a>'
                    //<a id="aid" href="#" onclick="getData();return false;" >点我调用</a>
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
                    //data: null,
                    text:'上传',
                    orderable:false,
                    defaultContent:'<form id="jvForm" method="post" enctype="multipart/form-data" ><input type="file" name="photo" onchange="uploadingUrl(this);"/></form>'


                }
            ],
            dataurl:"/wd_implementManage/read",
            //添加删除修改所在的页面
            updateurl:"/wd_implementManage/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
        $(window).scroll();
    });
    //


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

