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
                <div class="contentBoxUp">
                    <!-- pannel start -->
                    <div class="panelBox">
                        <div class="btnPart"><a href="#">添加主机</a> <span class="sp"></span> <a href="#">启动</a>  <a href="#">关闭</a> <a href="#">离线</a> <a href="#">上线</a></div>
                        <div class="conditionPart">
                            <span>过滤：<select>
                                <option value ="s1">123456</option>
                                <option value ="s2">654321</option>
                            </select>
                            </span>
                            <span class="sp2"></span><span><input  type="text"/ ></span></div>
                    </div>
                    <!-- pannel end -->
                    <!-- table start -->
                    <div class="tableBox">
                        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <th  class="col1">选择</th>
                                <th class="col1">名称</th>
                                <th class="col1">IP</th>
                                <th class="col1">状态</th>
                                <th class="col1">群集</th>
                                <th class="col2 panelCol" >快速操作</th>
                            </tr>
                            <tr class="row">
                                <td  align="center"><input type="checkbox"class="input_check" id="check1"><label for="check1"></label></td>
                                <td class="commCol">topmanager</td>
                                <td class="commCol">192.168.0.0</td>
                                <td class="commCol">在线</td>
                                <td class="commCol">topmanager</td>
                                <td class="panelCol"><a href=""><img src="/commpage/img/ma_jt.gif"></a> <a href=""><img src="/commpage/img/ma_jt2.gif"></a> <a href=""><img src="/commpage/img/ma_jt3.gif"></a> <a href=""><img src="/commpage/img/ma_jt4.gif"></a></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr class="row bgcolor">
                                <td  align="center"><input type="checkbox"class="input_check" id="check2"><label for="check2"></label></td>
                                <td class="commCol">topmanager</td>
                                <td class="commCol">192.168.0.0</td>
                                <td class="commCol">在线</td>
                                <td class="commCol">topmanager</td>
                                <td class="panelCol"><a href=""><img src="/commpage/img/ma_jt.gif"></a> <a href=""><img src="/commpage/img/ma_jt2.gif"></a> <a href=""><img src="/commpage/img/ma_jt3.gif"></a> <a href=""><img src="/commpage/img/ma_jt4.gif"></a></td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr class="row">
                                <td  align="center"><input type="checkbox"class="input_check" id="check3"><label for="check3"></label></td>
                                <td class="commCol">topmanager</td>
                                <td class="commCol">192.168.0.0</td>
                                <td class="commCol">在线</td>
                                <td class="commCol">topmanager</td>
                                <td class="panelCol"><a href=""><img src="/commpage/img/ma_jt.gif"></a> <a href=""><img src="/commpage/img/ma_jt2.gif"></a> <a href=""><img src="/commpage/img/ma_jt3.gif"></a> <a href=""><img src="/commpage/img/ma_jt4.gif"></a></td>                                <td>&nbsp;</td>
                            </tr>
                            <tr class="row bgcolor">
                                <td  align="center"><input type="checkbox"class="input_check" id="check4"><label for="check4"></label></td>
                                <td class="commCol">topmanager</td>
                                <td class="commCol">192.168.0.0</td>
                                <td class="commCol">在线</td>
                                <td class="commCol">topmanager</td>
                                <td class="panelCol"><a href=""><img src="/commpage/img/ma_jt.gif"></a> <a href=""><img src="/commpage/img/ma_jt2.gif"></a> <a href=""><img src="/commpage/img/ma_jt3.gif"></a> <a href=""><img src="/commpage/img/ma_jt4.gif"></a></td>                                <td>&nbsp;</td>
                            </tr>
                            <tr class="row">
                                <td align="center"><input type="checkbox"class="input_check" id="check5"><label for="check5"></label></td>
                                <td class="commCol">topmanager</td>
                                <td class="commCol">192.168.0.0</td>
                                <td class="commCol">在线</td>
                                <td class="commCol">topmanager</td>
                                <td class="panelCol"><a href=""><img src="/commpage/img/ma_jt.gif"></a> <a href=""><img src="/commpage/img/ma_jt2.gif"></a> <a href=""><img src="/commpage/img/ma_jt3.gif"></a> <a href=""><img src="/commpage/img/ma_jt4.gif"></a></td>                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                    <!-- table end -->
                </div>
                <!-- space  start -->
                <div class="space">
                    <div class="spacecoll"><img src="/commpage/img/tableSpace.gif" width="90" height="11"></div><div class="spacecolr"><img src="/commpage/img/tableSpace.gif" width="90" height="11"></div>
                </div>
                <!-- space end -->
                <!-- up info end-->
                <!-- down info start-->
                <div class="tabGroup">
                    <ul>
                        <li><a href="#">摘要</a></li>
                        <li><a href="#">网卡</a></li>
                        <li><a href="#">组件</a></li>
                        <li class="active"><a href="#">性能</a></li>
                    </ul>
                </div>
                <div class="contentBoxDown">
                    <!-- left info -->
                    <div class="contentinfol">
                        <h3>主机性能信息</h3>
                        <div class="headline"></div>
                        <div class="contentlistBox">
                            <ul>
                                <li><span class="spanlabel">客户机操作系统:</span><span class="spanvalue">CentOS 4/5/6 (64位）</span></li>
                                <li><span class="spanlabel">虚拟机版本:</span><span class="spanvalue">8</span></li>
                                <li><span class="spanlabel">CPU:</span><span class="spanvalue">2vCPU</span></li>
                                <li><span class="spanlabel">内存:</span><span class="spanvalue">2048MB</span></li>
                                <li><span class="spanlabel">内存开销:</span><span class="spanvalue">37.18MB</span></li>
                                <li><span class="spanlabel">vMware Tools:</span><span style="width:140px;" class="spanvalue">正在运行（当前版本）</span><img src="/commpage/img/main_tu3.gif" width="38" height="36"></li>
                                <li><span class="spanlabel">IP地址:</span><span class="spanvalue">192.168.1.125</span><span class="viewall"><a href="#">查看全部</a></span></li>
                                <li><span class="spanlabel">DNS 名称:</span><span class="spanvalue">SaaSSecure</span></li>
                                <li><span class="spanlabel">EVC 模式:</span><span class="spanvalue">不可用</span></li>
                                <li><span class="spanlabel">状况:</span><span class="spanvalue">已打开电源</span></li>
                                <li><span class="spanlabel">主机:</span><span class="spanvalue">192.168.1.159</span></li>
                                <li><span class="spanlabel">活动任务:</span><span class="spanvalue"></span></li>
                                <li><span class="spanlabel">VSphere HA保护:</span><span  style="width:50px;" class="spanvalue">不可用</span><img src="/commpage/img/main_tu4.gif" width="35" height="34" ></li>
                            </ul>
                        </div>
                    </div>
                    <!-- right info -->
                    <div class="contentinfor">
                        <h3>资源</h3>
                        <div class="headline"></div>
                        <div class="contentlistBox">
                            <ul>
                                <li><span class="spanlabel">已消耗的主机:</span><span class="spanvalue">CentOS 4/5/6 (64位）</span></li>
                                <li><span class="spanlabel">已消耗的主机内存:</span><span class="spanvalue">8</span></li>
                                <li><span class="spanlabel">活动客户机内存:</span><span class="spanvalue">2vCPU</span></li>
                                <li><span class="spanlabel">&nbsp;</span><span class="spanvalue"><a href="#">刷新存储使用情况</a></span></li>
                                <li><span class="spanlabel">置备的存储:</span><span class="spanvalue">37.18MB</span></li>
                                <li><span class="spanlabel">未共享的存储:</span><span class="spanvalue">正在运行（当前版本）</span></li>
                                <li><span class="spanlabel">已使用的存储:</span><span class="spanvalue">192.168.1.125</span></li>
                            </ul>
                            <!-- right list2 info start  -->
                            <div class="list2">
                                <table width="100%" height="100%" border="0" cellpadding="0" >
                                    <tr>
                                        <th class="col1">存储器</th>
                                        <th class="col1">状态</th>
                                        <th class="col1">驱动器类型</th>
                                        <th class="col2" >&nbsp;</th>
                                    </tr>
                                    <tr>
                                        <td class="col1">datastore1</td>
                                        <td class="col1">正常 <img src="/commpage/img/main_tu3.gif" width="38" height="36"></td>
                                        <td class="col1">非 SSD</td>
                                        <td class="col2" >&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                            <!-- right list2 info end  -->
                        </div>
                    </div>
                </div>
                <!-- down info end-->
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