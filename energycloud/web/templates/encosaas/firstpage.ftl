<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=afWcmxv4W1GgoNM0y54SPZnGavs2iTyn"></script>
    <script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/data/points-sample-data.js"></script>
<#include "include/include.ftl">
</head>
<body>
<#include "include/header.ftl">
<div class="main">
<#include "include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>仪表盘</h2>
                <div class="space" style="overflow-y:hidden;border-radius:5px;border:1px solid #000;height: calc(50% - 70px)"  >
                        <div id="divtotal"  style="width: 33%;height: 100%;float: left;"></div>
                        <div id="divmonth" style="width: 33%;height: 100%;float: left;border-left:1px solid #000;border-right:1px solid #000 "></div>
                        <div  id="divday" style="width: 34%;height: 100%;float: left;"></div>
                </div>
                <div class="space" style="margin-top:10px;border-radius:5px;border:1px solid #000;height: calc(50% )" id="allmap" >
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    var chartMonth;
    $(document).ready(function () {
        //获取各站的坐标和id
        var  data={};
        data["numPOrganizeID"]="0";
        $.post("/FirstPage/getStation" , {data:JSON.stringify(data)}, function (result) {
            // api接口：http://developer.baidu.com/map/wiki/index.php?title=jspopular/guide/cover
            var points = [];
            var lastindex=-1;
            for (var i = 0; i < result.length; i++) {
                if(result[i].varGlobalXY!=null)
                {
                    lastindex=i;
                    var p = new BMap.Point( result[i].varGlobalXY.split(",")[1],  result[i].varGlobalXY.split(",")[0]);
                    p.data = result[i].numOrganizeID;
                    p.text = result[i].varOrganizeName;
                    points.push(p);
                }
            }
            var map = new BMap.Map("allmap", {});
            var options = {
                size: BMAP_POINT_SIZE_BIGGER,
                shape: BMAP_POINT_SHAPE_CIRCLE,
                color: '#d340c3'
            };
            var pointCollection = new BMap.PointCollection(points, options);  // 初始化PointCollection
            pointCollection.addEventListener('click', function (e) {
                //弹出选择机构的回路
                alertCircuits(e.point.data);
            });
            pointCollection.addEventListener('mouseover', function (e) {
                var opts = {position: e.point, offset: new BMap.Size(15, -15)};
                var label = new BMap.Label(e.point.text, opts);  // 创建文本标注对象
                label.setStyle({
                    color: "white",
                    background: "#d340c3",
                    fontSize: "12px",
                    height: "20px",
                    lineHeight: "20px",
                    fontFamily: "微软雅黑"
                });
                map.addOverlay(label);
                setTimeout(function () {
                    map.removeOverlay(label);
                }, 1000, label);
            });
            //地图初始化
            map.addOverlay(pointCollection);  // 添加Overlay

            if(lastindex>-1)
                map.centerAndZoom(new BMap.Point(result[lastindex].varGlobalXY.split(",")[1],  result[lastindex].varGlobalXY.split(",")[0]), 11);

        },'json');
        initdatas();
        $(".c3-title").css("font-size","16").css("font-weight","bold");
    });
    function  alertCircuits(obj) {
        //获取所有的树节点
        $.quake.comp.confirm({
            html: '<table id="tableedit1" data-page-length="10" style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>',
            type:"alert"
        });
        var table1 = new $.quake.comp.datatables({
            id: "tableedit1",
            columns: [
                {data: "numCircuitID", text: "ID"},

                {
                    data: "varCircuitName", text: "回路名称", edit: {
                    type: "text"
                }
                },
                {
                    data: "varID", text: "varID", edit: {
                    type: "text"
                }
                },
                {data: "varOrganizeName", text: "上级组织名称"}
            ],
            dataurl: "/wd_circuitInfos/readBynumOrganizeID",
            "data": function () {
                return {numOrganizeID: obj}
            }
        }).initdata();
    }
    function initdatas()
    {
        var  data={};
        data["date"]=moment(new Date()).format('YYYY-MM-DD HH:MM:SS');
        var datas={};
        datas["datStart"]=moment().format('YYYY-MM-DD');
        datas["datEnd"]=moment().format('YYYY-MM-DD HH:mm:ss');
        //当前部门每一个最上级回路的耗能量比例
        $.post("/FirstPage/getAll" , {data:JSON.stringify(datas)}, function (result) {
            console.log(result);
            var piedata=generatePieData(result,"varCircuitName","numValue");
            c3.generate({
                bindto: '#divtotal',
                title: {
                    text: '当前能耗'
                },
                data: {
                    json: [ piedata["data"] ],
                    keys: {
                        value: piedata["key"]
                    },
                    type:'pie'
                }
            });

        },'json');
        //当日走势:

        $.post("/FirstPage/getDay" , {data:JSON.stringify(datas)}, function (result) {
            console.log(result);
            c3.generate({
                bindto: '#divday',
                title: {
                    text: '当日走势',
                    fill:'yellow'
                },
                padding: {
                    right: 15
                },
                data: {
                    json: result,
                    xFormat: '%Y-%m-%d %H:%M:%S',
                    keys: {
                        x: 'datBuild', // it's possible to specify 'x' when category axis
                        value: ['numValue']
                    }
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                            format:'%H:%M',
                            count:12,
                            fit: true,
                            culling:{
                                max:5
                            }
                        }
                    }
                }
            });

        },'json');
        $.post("/FirstPage/getMonth" , {data:JSON.stringify(datas)}, function (result) {
            console.log(result);
            c3.generate({
                bindto: '#divmonth',
                title: {
                    text: '当月走势',
                    fill:'yellow'
                },
                padding: {
                    right: 15
                },
                data: {
                    json: result,
                    keys: {
                         x: 'datStatistics', // it's possible to specify 'x' when category axis
                        value: ['total']
                    }
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                            format: '%m/%d',
                            centered: true,
                            fit: true,
                            culling:{
                                max:6
                            }
                        }
                    }
                }
            });
        },'json');
    }


    function generatePieData(jsonData,name,value)
    {
        var piedata={};
        var data = {};
        var sites = [];
        jsonData.forEach(function(e) {
            sites.push(e[name]);
            data[e[name]] = e[value];
        });
        piedata["data"]=data;
        piedata["key"]=sites;
        return   piedata;
    }

</script>
