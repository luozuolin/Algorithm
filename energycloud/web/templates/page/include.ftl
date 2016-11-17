<!--添加样式-->
<link href="/scss/quake/c3.css" rel="stylesheet" type="text/css"/>

<link href="/scss/quake/clockpicker.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/pickmeup.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/select2.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/jstree.css" rel="stylesheet" type="text/css"/>
<link href="/scss/quake/foundation.min.css" rel="stylesheet" type="text/css"/>
<!--添加基本方法-->
<script src="/vendor/jquery/jquery.js"></script>
<script src="/js/comm.js"></script>
<script src="/vendor/motion-ui/motion-ui.js"></script>
<script src="/vendor/what-input/what-input.js"></script>
<script src="/vendor/foundation/foundation.js"></script>
<script src="/vendor/vue/vue.js"></script>
<script src="/vendor/vuex/vuex.js"></script>
<script src="/vendor/dynamics/dynamics.js"></script>
<script src="/vendor/d3/d3.js"></script>
<script src="/vendor/c3/c3.js"></script>
<script src="/vendor/datatables/moment.min.js"></script>
<script src="/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="/vendor/datatables/dataTables.select.min.js"></script>

<script src="/vendor/3rd/clockpicker/clockpicker.js"></script>
<script src="/vendor/3rd/daterangepicker/jquery.daterangepicker.js"></script>
<script src="/vendor/3rd/jstree/jstree.min.js"></script>
<script src="/vendor/3rd/pickmeup/jquery.pickmeup.js"></script>
<script src="/vendor/3rd/select2/select2.js"></script>



<script src="/javascript/selfcomp/alert.js"></script>
<script src="/javascript/selfcomp/chosen.js"></script>
<script src="/javascript/selfcomp/CommDraw.js"></script>
<script src="/javascript/selfcomp/confirm.js"></script>
<script src="/javascript/selfcomp/datatables.js"></script>
<!--
<script src="/javascript/selfcomp/date.js"></script>-->
<script src="/javascript/selfcomp/daterangepicker.js"></script>
<script src="/javascript/selfcomp/daterange.js"></script>
<script src="/javascript/selfcomp/daterangeselect.js"></script>
<script src="/javascript/selfcomp/commdraw.js"></script>
<script src="/javascript/selfcomp/initmeterdata.js"></script>
<script src="/javascript/selfcomp/jstree.js"></script>
<script src="/javascript/md5.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
<style>

    .title-bar {
        background: #333;
        padding: 0.9rem; }

    .top-bar {
        background: #333; }
    .top-bar ul {
        background: #333; }
    .top-bar ul li {
        background: #333; }
    .top-bar ul li a {
        color: #fff; }

    .menu-text {
        color: #fff; }
    @media only screen and (max-width: 40em) {
        .menu-text {
            display: none !important; } }

    @media only screen and (min-width: 40em) {
        .menu:last-child {
            border-left: 1px solid #4e4e4e; }

        .menu:first-child {
            border-left: none; }

        .menu li:not(:last-child) {
            border-right: 1px solid #4e4e4e; } }
    .dropdown.menu .submenu {
        border: none; }

    .dropdown.menu .is-dropdown-submenu-parent.is-right-arrow > a::after {
        border-color: #fff transparent transparent; }

    .is-drilldown-submenu-parent > a::after {
        border-color: transparent transparent transparent #fff; }

    .js-drilldown-back::before {
        border-color: transparent #fff transparent transparent; }




    .is-accordion-submenu-parent a {
        background: #4d5158; }

    .is-accordion-submenu a {
        background: #35383d; }

    [data-accordion-menu] .is-accordion-submenu-parent[aria-expanded="true"] a.subitem::before {
        content: "\f016";
        font-family: FontAwesome;
        margin-right: 1rem; }

    [data-accordion-menu] .is-accordion-submenu-parent[aria-expanded="true"] a::before {
        content: "\f07c";
        font-family: FontAwesome;
        margin-right: 1rem; }

    [data-accordion-menu] .is-accordion-submenu-parent[aria-expanded="false"] a::before {
        content: "\f07b";
        font-family: FontAwesome;
        margin-right: 1rem; }

    .sublevel-1 {
        text-indent: 1rem; }

    .sublevel-2 {
        text-indent: 2rem; }

    .sublevel-3 {
        text-indent: 3rem; }

    .sublevel-4 {
        text-indent: 4rem; }

    .sublevel-5 {
        text-indent: 5rem; }

    .sublevel-6 {
        text-indent: 6rem; }

    [data-accordion-menu] a {
        color: #fff;
        box-shadow: inset 0 -1px #41444a; }
    [data-accordion-menu] a::after {
        border-color: #fff transparent transparent; }

    .menu > li:not(.menu-text) > a {
        padding: 1.2rem 1rem; }


    .datatable td{
        text-align:center;
    }
    .datatable th{
        text-align:center;
    }
</style>
<script>
    $(document).ready(function () {
        //初始化用户权限
        $.ajax({
            type: "GET",
            async: false,
            url: "/rightlist",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            cache: false,
            success: function (data) {
                for(var i=0;i<data.length;i++){
                    if(data[i].varFunctionURL==""){
                        $("#ulmenu").append('<li id="func' + i + '"><a  href="#">'+data[i].varFunctionCode+'</a></li>');
                        $("#func"+i+"").append("<ul class='menu vertical sublevel-1' id='funcl"+i+"'></ul>");
                        for (var j = 0;j < data.length; j++){
                            if (data[j].numPFunctionID == data[i].numFunctionID) {

                                if(data[j].numFunctionType=="2")
                                {
                                    // 自动生成的页面, <label onclick="goto('39')" >按回路统计</label>
                                    var pageID = data[j].numPageID;
                                    $("#funcl" + i + "").append(" <li ><a  class='subitem' href='/pages?numPageID="+pageID+"' target='workbench'>" + data[j].varFunctionCode + "</li>");
                                }
                                else if(data[j].numFunctionType=="1"){
                                    var right = data[j].varFunctionURL.split('_');
                                    $("#funcl" + i + "").append(" <li ><a  class='subitem' href='/" + right[0] + "/" + right[1] + "' target='workbench'>" + data[j].varFunctionCode + "</a></li>");
                                }
                                else if(data[j].numFunctionType=="3")
                                {
                                    // 自动生成的页面, <label onclick="goto('39')" >按回路统计</label>
                                    var pageID = data[j].numPageID;
                                    $("#funcl" + i + "").append(" <li ><a class='subitem'  href='/configs?numPageID="+pageID+"' target='workbench'>"+ data[j].varFunctionCode + "</a></li>");
                                }
                            }
                        }
                    }
                }
                $("#ulmenu").css("height",$(window).height()-$(".title-bar").height()-45);
                $("#ulmenu").foundation();
            }
        });

        $(window).resize(function () {
            $("#ulmenu").css("height",$(window).height()-$(".title-bar").height()-45);
        });
    });
</script>
