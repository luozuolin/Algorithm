<!DOCTYPE html>
<html>
<head>
<#include "include/include.ftl">
</head>
<body>
<#include "include/header.ftl">
<link href="/commpage/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="/commpage/js/jquery-ui.min.js"></script>
<script src="/commpage/js/jquery.ui-contextmenu.js"></script>

    fsgsgdgdghs
    <div id="container">
        <div class="hasmenu">AAA</div>
        <div class="hasmenu">BBB</div>
        <div class="hasmenu">CCC</div>

</body>
</html>
<script>
    var   table;
    $(document).ready(function () {
        $(document).foundation();
        $("#container").contextmenu({
            delegate: ".hasmenu",
            menu: [
                {title: "Copy", cmd: "copy", uiIcon: "ui-icon-copy"},
                {title: "----"},
                {title: "More", children: [
                    {title: "Sub 1", cmd: "sub1"},
                    {title: "Sub 2", cmd: "sub1"}
                ]}
            ],
            select: function(event, ui) {
                alert("select " + ui.cmd + " on " + ui.target.text());
            }
        });
    });

    function uploadingUrl(a){
        var options = {
            url:"/fileupload/fileupload1",
            data:{},
            type:"post",
            dataType:"json",
            success:function(data){
                // alert("ok");
                if (data!= null){
                    alert(data.message);

                }
            }
        };

        $(a).parent().ajaxSubmit(options);


    }
    function downloadFile(e)
    {

      //  window.location.href="/WD_Uploading/download?numImplementID="+table.table.DataTable.rows($(e).closest('tr')).data()[0].numImplementID;
    }

</script>



