//Alert控件
//主要有四种样式:success,warning,info,alert(成功,警告,提示信息,错误)
// 传递参数:type:(success,warning,info,alert),info;infor:传递信息();position:显示位置(默认为屏幕最中间);interval:提示信息停留时间(默认success,info为2s,warning,alert为4s)
$.quake.comp.alert=function (obj) {
    //position: absolute; pointer-events: none; display: block; top: 123.5px; left: 560.5px;
    var configs={};
    var confdefault={};
    configs["success"]=function () {
        confdefault["type"] = "success";
        confdefault["infor"] = "";
        confdefault["interval"] = 2000;
        return confdefault;
    };
    configs["warning"]=function () {
        confdefault["type"] = "warning";
        confdefault["infor"] = "";
        confdefault["interval"] = 4000;
        return confdefault;
    };
    configs["info"]=function () {
        confdefault["type"] = "info";
        confdefault["infor"] = "";
        confdefault["interval"] = 2000;
        return confdefault;
    };
    configs["alert"]=function () {
        confdefault["type"] = "alert";
        confdefault["infor"] = "";
        confdefault["interval"] = 4000;
        return confdefault;
    };
    var config=configs[obj.type]();
    for(var k in obj)
    {
        config[k]=obj[k];
    }
 var id="calloutdiv"+generateUUID();
    if($("#calloutdiv")[0]==null)
        $("body").append('<div id="calloutdiv" style="position: absolute;right:0px;width:400px;top:0px"></div>');
    $("#calloutdiv").append('<div id="'+id+'" class="'+config["type"]+' callout " data-closable="slide-out-right"><p>'+config["infor"]+'</p><button class="close-button" aria-label="Dismiss alert" type="button" data-close><span aria-hidden="true">&times;</span></button></div>');
 setTimeout(function () {
      console.log(config["interval"]+id);
        $("#"+id).trigger('close');
    }, config["interval"],id);

};
function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
}