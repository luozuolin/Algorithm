<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>首页</title>
    <link rel="shortcut icon" href="favicon.ico" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
</head>

<body style="background:#fefaf9; width:100%;">
<div class="contentwrap">
    <div class="contentBox">
        <!-- up info start-->
        <h2>虚拟主机列表</h2>
        <div class="contentBoxUp">
            <!-- pannel start -->
            <div class="panelBox">
                <div class="btnPart"><a href="#">添加主机</a> <span class="sp"></span> <a href="#">启动</a>  <a href="#">关闭</a> <a href="#">离线</a> <a href="#">上线</a></div>
                <div class="conditionPart">
<span>
过滤：<select>
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
                        <td class="panelCol"><a href=""><img src="img/ma_jt.gif"></a> <a href=""><img src="img/ma_jt2.gif"></a> <a href=""><img src="img/ma_jt3.gif"></a> <a href=""><img src="img/ma_jt4.gif"></a></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr class="row bgcolor">
                        <td  align="center"><input type="checkbox"class="input_check" id="check2"><label for="check2"></label></td>
                        <td class="commCol">topmanager</td>
                        <td class="commCol">192.168.0.0</td>
                        <td class="commCol">在线</td>
                        <td class="commCol">topmanager</td>
                        <td class="panelCol"><a href=""><img src="img/ma_jt.gif"></a> <a href=""><img src="img/ma_jt2.gif"></a> <a href=""><img src="img/ma_jt3.gif"></a> <a href=""><img src="img/ma_jt4.gif"></a></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr class="row">
                        <td  align="center"><input type="checkbox"class="input_check" id="check3"><label for="check3"></label></td>
                        <td class="commCol">topmanager</td>
                        <td class="commCol">192.168.0.0</td>
                        <td class="commCol">在线</td>
                        <td class="commCol">topmanager</td>
                        <td class="panelCol"><a href=""><img src="img/ma_jt.gif"></a> <a href=""><img src="img/ma_jt2.gif"></a> <a href=""><img src="img/ma_jt3.gif"></a> <a href=""><img src="img/ma_jt4.gif"></a></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr class="row bgcolor">
                        <td  align="center"><input type="checkbox"class="input_check" id="check4"><label for="check4"></label></td>
                        <td class="commCol">topmanager</td>
                        <td class="commCol">192.168.0.0</td>
                        <td class="commCol">在线</td>
                        <td class="commCol">topmanager</td>
                        <td class="panelCol"><a href=""><img src="img/ma_jt.gif"></a> <a href=""><img src="img/ma_jt2.gif"></a> <a href=""><img src="img/ma_jt3.gif"></a> <a href=""><img src="img/ma_jt4.gif"></a></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr class="row">
                        <td align="center"><input type="checkbox"class="input_check" id="check5"><label for="check5"></label></td>
                        <td class="commCol">topmanager</td>
                        <td class="commCol">192.168.0.0</td>
                        <td class="commCol">在线</td>
                        <td class="commCol">topmanager</td>
                        <td class="panelCol"><a href=""><img src="img/ma_jt.gif"></a> <a href=""><img src="img/ma_jt2.gif"></a> <a href=""><img src="img/ma_jt3.gif"></a> <a href=""><img src="img/ma_jt4.gif"></a></td>
                        <td>&nbsp;</td>
                    </tr>
                </table>



            </div>
            <!-- table end -->
        </div>
        <!-- space  start -->
        <div class="space">
            <div class="spacecoll"><img src="img/tableSpace.gif" width="90" height="11"></div><div class="spacecolr"><img src="img/tableSpace.gif" width="90" height="11"></div>
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
                        <li><span class="spanlabel">vMware Tools:</span><span style="width:140px;" class="spanvalue">正在运行（当前版本）</span><img src="img/main_tu3.gif" width="38" height="36"></li>
                        <li><span class="spanlabel">IP地址:</span><span class="spanvalue">192.168.1.125</span><span class="viewall"><a href="#">查看全部</a></span></li>
                        <li><span class="spanlabel">DNS 名称:</span><span class="spanvalue">SaaSSecure</span></li>
                        <li><span class="spanlabel">EVC 模式:</span><span class="spanvalue">不可用</span></li>
                        <li><span class="spanlabel">状况:</span><span class="spanvalue">已打开电源</span></li>
                        <li><span class="spanlabel">主机:</span><span class="spanvalue">192.168.1.159</span></li>
                        <li><span class="spanlabel">活动任务:</span><span class="spanvalue"></span></li>
                        <li><span class="spanlabel">VSphere HA保护:</span><span  style="width:50px;" class="spanvalue">不可用</span><img src="img/main_tu4.gif" width="35" height="34" ></li>
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
                                <td class="col1">正常 <img src="img/main_tu3.gif" width="38" height="36"></td>
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
</body>
</html>
