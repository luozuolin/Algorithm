<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Vue Demo</title>
</head>
<#-- ========================================== -->
<#-- # Body Begin -->
<#-- ========================================== -->
<body id="app">
<div id="main-view" class="callout">
    <div class="float-center">
        <h3 class="text-center">能源云平台!! </h3>
        <br/>
        <div class="row">
            <div class="small-1 columns">
                运动项目
            </div>
            <div class="small-3 columns">
                <input type="text" id="sport" >
            </div>
            <div class="small-1 columns">
                姓名
            </div>
            <div class="small-3 columns">
                <input type="text" id="playername" >
            </div>
            <div class="small-4 columns">
                <input type="button" value="查询" class="button" id="btnsearch">
            </div>

        </div>
        <table id="player" data-page-length='3'  cellspacing="0" width="100%" class="datatable hover"></table>
        <table id="playeredit" data-page-length='3'  cellspacing="0" width="100%" class="datatable hover" ></table>
    </div>
</div>
</div>
</body>
</html>
<#-- ========================================== -->
<#-- # Style Begin -->
<#-- ========================================== -->
<style>
    .date-picker-wrapper{z-index:2500 !important;}
    .chosen-with-drop{z-index:2600 !important;}
    .chosen-drop{z-index:2700 !important;}
    #main-view {
        border: 0;
    }
    #conent-view {
        width: 400px;

    }
    .datatable td{
        text-align:center;
    }
    .datatable th{
        text-align:center;
    }

</style>
<#-- ========================================== -->
<#-- # Script Begin -->
<#-- ========================================== -->
<script>
    var table1,table2;
    $().ready(function() {
        $.post("/sport/listAll", null, function (result) {
            var data = '<option value="-1">-请选择-</option>';
            for (var i = 0; i < result.length; i++) {
                data += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
            }
            $("#sport").parent().html('<select class="form-control medium-12" id="sport">'+data+'</select>');
            $("#sport").select2();
        });
        $("#btnsearch").click(function(){
            table1.reloadtable();
            table2.reloadtable();
        });
    table1=new $.quake.comp.datatables({
            id:"player",
            columns:[
                {
                    "text":"详细信息",
                    "className": 'details-control',
                    "orderable": false,
                    "data":  null,
                    "defaultContent": '',
                    "init":function (obj,row) {// 没有init的话显示所有data数据,否则按照init显示数据
                        console.log(row);
                        c3.generate({
                            bindto: '#'+obj,
                            data: {
                                columns: [
                                    ['data1', 30, 200, 100, 400, 150, 250],
                                    ['data2', 50, 20, 10, 40, 15, 25]
                                ]
                            }
                        });
                    }
                },
                { data: "name",text:"姓名" ,"orderable": true},
                { data: "sex",text:"性别",render: function ( data, type, full, meta ) {
                    return  data==1?"男":"女";
                } },
                { data: "born",text:"出生日期","render": function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                }},
                { data: "sportname" ,text:"运动项目","orderable": true},
                {data:"coachname",text:"教练"},
                {data:"orgname",text:"部门"},
                {data:"isimportant",text:"重要","render": function ( data, type, full, meta ) {
                    return  data==1?"重要":"一般";
                }},
                {data:"status",text:"状态","render": function ( data, type, full, meta ) {
                    return  data==1?"正常":"销户";
                }}

            ],
            "order": [[1, 'desc']],
            dataurl:"/player/getAll",
           "data":function(){
               return {name:$("#playername").val(),sportid:$("#sport").val()}
           }
        });
        table1.initdata();
      table2=new $.quake.comp.datatables({
            id:"playeredit",
            columns:[
                { data: "id",text:"id" ,visible:false},
                { data: "name",text:"姓名" ,edit:{
                    type:"text"
                }},
                { data: "sex",text:"性别",render: function ( data, type, full, meta ) {
                    return  data==1?"男":"女";
                } ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $("#sex").parent().html('<select class="form-control" id="sex"><option value="-1">Select...</option><option value="1"  id="1">男</option><option value="2" id="2">女</option></select>');
                        if(datas!=null) {$("#sex").val(datas);}
                        $("#sex").select2();


                    }
                }},
                { data: "born",text:"出生日期",render: function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD');
                },edit:{
                        type:"datetime",
                        init:function(obj,datas)
                        {
                            $("#born").dateRangePicker({
                                autoClose: true,
                                singleDate : true,
                                showShortcuts: true,
                                singleMonth: true,
                                language:'cn'
                            });

                        }
                    }
                },
                { data: "sportname" ,text:"运动项目"},
                { data: "sportid" ,text:"运动项目",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/sport/listAll", null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                            }
                            $("#sportid").parent().html('<select class="form-control medium-12" id="sportid">'+data+'</select>');
                            if(datas!=null) {$("#sportid").val(datas);}
                            $("#sportid").select2();
                        });

                    }
                }},
                {data:"coachname",text:"教练"},
                {data:"coachid",text:"教练",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/coach/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].id + '">' + result[i].name + '</option>';
                            }
                            $("#coachid").parent().html('<select class="form-control" id="coachid">'+data+'</select>');
                            if(datas!=null) {$("#coachid").val(datas);}
                            // $("#coachid").chosen();
                            $("#coachid").select2();
                            // $(".select2-container").css("width","100%");
                        });
                    }
                }},
                {data:"orgname",text:"部门",returnval:"1"},
                {data:"orgid",text:"部门",visible:false,returnval:"1"},
                {data:"isimportant",text:"重要","render": function ( data, type, full, meta ) {
                    return  data==1?"重要":"一般";
                },edit:{
                    type:"text",
                    init:function(obj,datas)
                    {
                        $("#isimportant").parent().html('<select class="form-control" id="isimportant"><option value="-1">Select...</option><option value="1"  id="1">重要</option><option value="0" id="0">一般</option></select>');
                        if(datas!=null) {$("#isimportant").val(datas);}
                        $("#isimportant").select2();
                    }
                }},
                {data:"status",text:"状态","render": function ( data, type, full, meta ) {
                    return  data==1?"正常":"销户";
                }},{
                    data: null,
                    text:'个性化操作',
                    orderable:false,
                    defaultContent: '<a   onclick="getinfo(this);">获取信息</a>'
                }
            ],
            dataurl:"/player/getAll",
            //添加删除修改所在的页面
            updateurl:"/player/edit1/",
          selectstyle:"multi",
            "data":function(){
               // return {name:$("#name").val(),sportid:""}
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });
    function  getinfo(e) {
        console.log(table1.table.DataTable.rows($(e).closest('tr')).data()[0]);
       // var  t=self;
     //   t.tr=$(this).closest('tr');
      //  return t.alertupdate(this, t.table, "update");
    }
    $.quake.regReady(function () {
//        console.log("==========================");

    });

</script>
