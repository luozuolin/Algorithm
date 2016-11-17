/**
 * Created by luozl on 2016/8/10.
 */
//在tooltip中显示仪表的ia,ib,ic,ua,ub,uc
$.quake.comp.commdraw.initmeterdata=function(svgid){
    $("#"+svgid).contextmenu({
        delegate: "image",
        menu: [
            {title: "当日分时数据", cmd: "day", uiIcon: "ui-icon-copy"},
            {title: "当月每日数据", cmd: "month", uiIcon: "ui-icon-copy"}
        ],
        select: function(event, ui) {
            alert("select " + ui.cmd + " on :" + $(ui.target).attr("tag"));
        }
    });
    $("#"+svgid+" image").each(function(key, value) {
        if($(this).attr("tag")!="") {
            var data=jQuery.parseJSON($(this).attr("tag"));
            if(data["Tag"]=="meter" && data["ID"]!="") {
                //添加右键菜单
                $(this).click(function(){
                            $.get("/getdata?url=http://192.168.1.200:5000/ShowTable?name=" + data["ID"], null, function (result) {
                                var str = "电表：" + result["name"] + "(" + result["tableType"] + ")<br>";
                                var len = 0;
                                for (var k in result.registerPools[0]) {
                                    if (len++ > 10)
                                        break;
                                    str += k + ":" + (CheckDateTime(result.registerPools[0][k]) ? result.registerPools[0][k] : parseFloat(result.registerPools[0][k]).toFixed(2)) + "<br>";
                                }
                                $.quake.comp.alert({type: "alert", infor: str});
                            }, "json");

                });
            }}
    });
    refreshmeterdata(svgid);
    setInterval('refreshmeterdata('+svgid+')',60000); //指定1分钟刷新一次
};
function CheckDateTime(str){
    var reg=/^(\d+)-(\d{1,2})-(\d{1,2})(\s+)(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r=str.match(reg);
    if(r==null) return false;
    r[2]=r[2]-1;
    var d= new Date(r[1],r[2],r[3],r[4],r[5],r[6]);
    if(d.getFullYear()!=r[1]) return false;
    if(d.getMonth()!=r[2]) return false;
    if(d.getDate()!=r[3]) return false;
    if(d.getHours()!=r[4]) return false;
    if(d.getMinutes()!=r[5]) return false;
    if(d.getSeconds()!=r[6]) return false;
    return true;
}
function refreshmeterdata(svgid)
{
    //  alert(svgid);
    $("foreignObject[tag!='']").each(function() {
        //刷新仪表数据
        var data=jQuery.parseJSON($(this).attr("tag"));
        if(data["Tag"]=="meter" && data["ID"]!="") {
            var that=this;
            $.get("/getdata?url=http://192.168.1.200:5000/ShowTable?name=" + data["ID"], {obj:$(that).attr("id")}, function (result) {
              //  var str =  result["name"] + "(" + result["tableType"] + ")<br/>";
                if(result.registerPools!=null) {
                    var str = "";
                    str += "ia:" + parseFloat(result.registerPools[0]['ia']).toFixed(2) + "<br/>";
                    str += "va:" + parseFloat(result.registerPools[0]['van']).toFixed(2) + "<br/>";
                    str += "p:" + parseFloat(result.registerPools[0]['p']).toFixed(2) + "<br/>";
                    str += "pw+:" + parseFloat(result.registerPools[0]['pw+']).toFixed(2) + "<br/>";
                    $($("#" + getQueryString(this.url, "obj")).children()).html(str);
                }
            }, "json");
        }
    });
}
function getQueryString(str,name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = str.substr(1).match(reg);
    if (r != null) return unescape(decodeURI(r[2])); return null;
}

$.quake.comp.commdraw.meter={};
$.quake.comp.commdraw.initmeterdatafromdatabase=function(svgid){
    $("#"+svgid).contextmenu({
        delegate: "image",
        menu: [
            {title: "当日分时数据", cmd: "day", uiIcon: "ui-icon-copy"},
            {title: "当月每日数据", cmd: "month", uiIcon: "ui-icon-copy"}
        ],
        select: function(event, ui) {
            //弹出分时分月数据
            alertDatas($(ui.target).attr("tag"),ui.cmd);
        }
    });
    var meterids=[];
    $("#"+svgid+" image").each(function(key, value) {
        if($(this).attr("tag")!="") {
            var data=jQuery.parseJSON($(this).attr("tag"));
            if(data["Tag"]=="meter" && data["ID"]!="") {
                meterids.push(data["ID"]);
                $(this).click(function(){
                    var str = "电表：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]].varName+"<br>";
                    str += "Ia：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ia"]+"<br>";
                    str += "Ib：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ib"]+"<br>";
                    str +="Ic：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ic"]+"<br>";
                    str += "Ua：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ua"]+"<br>";
                    str += "Ub：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ub"]+"<br>";
                    str += "Uc：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Uc"]+"<br>";
                    str += "P：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["P"]+"<br>";
                    str += "PW：" +$.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["PW"]+"<br>";
                    $.quake.comp.alert({type: "alert", infor: str});
                });
            }}
    });
    $.quake.comp.commdraw.meter["meterids"]=meterids;
    refreshmeterdatafromdatabase();
  //  refreshmeterdata(svgid);
    setInterval('refreshmeterdatafromdatabase()',300000); //指定1分钟刷新一次
};
//弹出分时分月数据
function  alertDatas(obj,cmd) {
    var data=jQuery.parseJSON(obj);
    var numCircuitGroupID = data["ID"];
    //获取所有的树节点
    /*
     <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
     <div class="row">
     <div class="small-12 columns" >
     <div>
     <button style="width: 100px"  type="button" id="btnshowcharts" class="button">曲线图</button>
     <button style="width: 100px"  type="button" id="btnshowdata" class="button">全部数据</button>
     <button type="button" style="width: 100px;"  class="button"    id="btnexport">导出</button>
     </div>
     </div>
     </div>
     <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
     <div id="divChart"/>
     </div>
    * */



/*
    $.quake.comp.confirm({
        html: '<table id="tableedit1" data-page-length="10"  cellspacing="0"  class=" datatable hover" ></table>',
        type:"alert"
    });
*/
    $.quake.comp.confirm({
        html: ' <table id="tableedit" data-page-length=\'5\' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table><div id="divChart"/>',
        type:"alert"
    });

    //分时分月数据需要走势图
    if(cmd=="month")
    {
        new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "varCircuitName",text:"回路名称"},
                { data: "datStatistics",text:"统计数据"},
                { data: "numValue",text:"使用电量(千瓦时)"}
            ],
            dataurl:"/GetData/getCircuitDatasByDateRangeAndnumCircuitGroupIDs",
            //reloadtable时需要传递参数
            "data":function(){
             var  d= new Date();
                return {numCircuitID:data["ID"],start: d.getFullYear()+"-"+(d.getMonth()+1)+"-1" ,end:moment().format('YYYY-MM-DD')}
            }
        }).initdata();
        var  d= new Date();
        $.post("/GetData/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll" , {data:JSON.stringify({numCircuitID:data["ID"],start: d.getFullYear()+"-"+(d.getMonth()+1)+"-1" ,end:moment().format('YYYY-MM-DD')})}, function (result) {
            //行列转置
            var data=celltorow(result,"datStatistics","varCircuitName","numValue");
            //使用json数据结构：http://c3js.org/reference.html#data-json
            c3.generate({
                bindto: '#divChart',
                data: {
                    json:data.data,
                    keys: {
                        x: 'datStatistics', // it's possible to specify 'x' when category axis
                        value:data.cell
                    }
                },
                padding: {
                    right: 25
                },
                axis: {
                    x: {
                        type: 'timeseries',
                        tick: {
                            format: '%Y-%m-%d'
                        }
                    }
                }
            });
        },'json');


    }
    else  if(cmd=="day")
    {
        new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "varCircuitName",text:"回路名称"},
                { data: "datBuild",text:"统计时间","render": function ( data, type, full, meta ) {
                    return  moment(data).format('YYYY-MM-DD HH:mm:ss');
                }},
                { data: "numValue",text:"使用电量(千瓦时)"}

            ],
            dataurl:"/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit",
            //reloadtable时需要传递参数
            "data":function(){
                return {numCircuitID:data["ID"],datStart:moment().format('YYYY-MM-DD'),datEnd:moment().format('YYYY-MM-DD HH:mm:ss')}
            }
        }).initdata();
        var  d= new Date();
        $.post("/GetData/getCircuitDatasByPerFiveMinutesAndnumCircuit/listAll" , {data:JSON.stringify({numCircuitID:data["ID"],datStart:moment().format('YYYY-MM-DD'),datEnd:moment().format('YYYY-MM-DD HH:mm:ss')})}, function (result) {
            //行列转置
            var data=celltorow(result,"datBuild","varCircuitName","numValue");
            //使用json数据结构：http://c3js.org/reference.html#data-json
            c3.generate({
                bindto: '#divChart',
                data: {
                    xFormat: '%Y-%m-%d %H:%M:%S',
                    json:data.data,
                    keys: {
                        x: 'datBuild', // it's possible to specify 'x' when category axis
                        value:data.cell
                    }
                },
                padding: {
                    right: 15
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



    }
}
function celltorow(json,row,cell,val) {
    var ret = {};
    for (var i = 0; i < json.length; i++)
    {
        if(ret[json[i][row]]==null)
            ret[json[i][row]]={};
        if( ret[json[i][row]][json[i][cell]]==null)
            ret[json[i][row]][json[i][cell]]={};
        ret[json[i][row]][json[i][cell]]=json[i][val];
    }
    var retArray=[];
    var cells={};
    for(var i in ret)
    {
        var data={};
        data[row]=i;
        for(var j in ret[i])
        {
            cells[j]=j;
            data[j]=ret[i][j];
        }
        retArray.push(data);
    }
    var cell=[];
    for(var i in cells)
    {
        cell.push(i);
    }
    ret={};
    ret["data"]=retArray;
    ret["cell"]=cell;
    return ret;
}
function refreshmeterdatafromdatabase()
{
    $.post("/getrealdatafromdatabase" , {meterids:$.quake.comp.commdraw.meter["meterids"],data:JSON.stringify({datStart:'2016-11-1',datEnd:'2016-11-1 23:59:59'})}, function (result) {
        //对返回的数据进行处理
        $.quake.comp.commdraw.meter["meterdatas"]={};
        for(var i=0;i<result.length;i++)
        {
            if($.quake.comp.commdraw.meter["meterdatas"][result[i].numCircuitID]==null)
            {
                $.quake.comp.commdraw.meter["meterdatas"][result[i].numCircuitID]={};
            }
            $.quake.comp.commdraw.meter["meterdatas"][result[i].numCircuitID][result[i].varCName]=result[i].numValue;
            $.quake.comp.commdraw.meter["meterdatas"][result[i].numCircuitID].varName=result[i].varName;
        }
        //刷新label数据
        $("foreignObject[tag!='']").each(function() {
            //刷新仪表数据
            var data=jQuery.parseJSON($(this).attr("tag"));
            if(data["Tag"]=="meter" && data["ID"]!="") {
                var str="";
                str += "ia:" + parseFloat($.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ia"]).toFixed(2) + "<br/>";
                str += "va:" + parseFloat($.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["Ua"]).toFixed(2) + "<br/>";
                str += "p:" + parseFloat($.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["P"]).toFixed(2) + "<br/>";
                str += "pw+:" + parseFloat($.quake.comp.commdraw.meter["meterdatas"][data["ID"]]["PW"]).toFixed(2) + "<br/>";
                $(this).children().html(str);
            }
        });
    },'json');

}