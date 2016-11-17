<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8"/>
    <title>首页</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="/commpage/css/style.css"/>

    <script src="/commpage/js/jquery1.42.min.js" type="text/javascript"></script>
    <script src="/commpage/js/frameLayout.js" type="text/javascript"></script>

</head>
<body>
<!-- header s -->
<div class="header">
    <div class="auto">
        <a href="#"><div class="logo"></div></a>
        <div class="fr">
            <a href="#"><img src="/commpage/img/t1.png" alt="" />关于</a>
            <a href="#"><img src="/commpage/img/t2.png" alt="" />退出</a>
        </div>
    </div>
</div>
<!-- header e -->
<!-- main s -->
<script src="/commpage/js/sdmenu.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/commpage/css/base.css"/>

<div class="main">
    <div class="main_left" id="frmTitle" name="fmTitle" >

        <div id="my_menu" class="sdmenu">
            <div>
                <span class="i1"><i></i>概览</span>
                <p>
                    <a href="#"><i></i>Alerts</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>

            <div>
                <span class="i2"><i ></i>资源概览</span>
                <p>
                    <a href="#"><i></i>Alerts2</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>


            <div>
                <span class="i3"><i ></i>虚拟主机管理</span>
                <p>
                    <a href="#"><i></i>Alerts3</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>
            <div>
                <span class="i4"><i ></i>桌面管理</span>
                <p>
                    <a href="#"><i></i>Alerts4</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>

            <div >
                <span class="i5"><i></i>群集管理</span>
                <p>
                    <a href="#"><i></i>Alerts5</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>


            <div>
                <span class="i6"><i ></i>运维管理</span>
                <p>
                    <a href="#"><i></i>Alerts6</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>
            <div>
                <span class="i7"><i ></i>应用中心</span>
                <p>
                    <a href="#"><i></i>Alerts7</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>

            <div>
                <span class="i8"><i ></i>用户管理</span>
                <p>
                    <a href="#"><i></i>Alerts8</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>
            <div>
                <span class="i9"><i></i>系统配置</span>
                <p>
                    <a href="#"><i></i>Alerts9</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>


            <div>
                <span class="i10"><i></i>业务管理</span>
                <p>
                    <a href="#"><i></i>Alerts10</a>
                    <a href="#"><i></i>Power</a>
                    <a href="#"><i></i>Alerts</a>
                </p>
            </div>
        </div><!--my_menu end-->
    </div>
    <div class="picBox"  id="frameLeftToggle" ><img src="/commpage/img/right.gif" alt="" onclick="hideNavi()"/></div>
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <!-- up info start-->
                <h2>虚拟主机列表</h2>
                <div class="space" style="height: calc(100% - 100px)">

                </div>
            </div>
        </div>
    </div>
</div>
<!-- main e -->
</body>
</html>
<script type="text/javascript">
    var myMenu;
    window.onload = function(){
        myMenu = new SDMenu("my_menu");
        myMenu.init();
        var firstSubmenu = myMenu.submenus[0];
        myMenu.expandMenu(firstSubmenu);
    };
</script>