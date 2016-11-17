//此控件主要用到的文件:
// moment.min.js
//daterangepicker.scss
// jquery.daterangepicker.js
//主要工作:包装jquery.daterangepicker,初始化出几个日常工作中常用的日期控件.
//type是日期控件的类型:日期选择,区间选择,日期选择带上时间,区间选择带上时间.config可以传递jquery.daterangepicker的基本配置出来,这样可以丰富默认的配置或者覆盖默认配置.
// 详细使用参见:http://longbill.github.io/jquery-date-range-picker/
//传递参数:id,type,config,start,end:控件id,类型,附加配置,开始时间控件id,结束时间控件id
$.quake.comp.daterangepicker=function (obj) {
   // this.obj=obj;
    //type:date,daterange,datetime,datetimerange,
    var configs={};
    var confdefault={language:'cn',beforeShow: function (input, inst) {
        setTimeout(function () {
            inst.dpDiv.css({
                top: 100,
                left: 200
            });
        }, 0);
    }};
    // 处理默认情况
    if(obj.type==null)
        obj.type="date";
    //没有传递id就直接返回null,或者是时间区间但是没有传递开始和和结束控件也直接返回null
    if(obj.id==null || ((obj.type=="daterange" || obj.type=="datetimerange" ) && (obj.start==null || obj.end==null)))
    {
        return null;
    }
   configs["date"]=function () {
        confdefault["autoClose"] = true;
        confdefault["singleDate"] = true;
        confdefault["singleMonth"] = true;
       confdefault["format"] = "YYYY-MM-DD";
        return confdefault;
    };
    configs["daterange"]=function () {
        //设置html
        var o=$("#"+obj.id);
        $("#"+obj.id).css("height","39px").css("width","100%");
        $("#"+obj.id).html('<input id="'+obj.start+'" type="text" style="width: '+((o.width()-16)/2)+'px;float: left" value=""> <label class="text-right middle" style="float: left;width: 15px;">to</label> <input id="'+obj.end+'"  size="20" type="text" style="width: '+((o.width()-16)/2)+'px;float: left" value="">');
        confdefault["separator"] = ' to ';
        confdefault["format"] = "YYYY-MM-DD";
        confdefault["getValue"] = function()
        {
            if ($('#'+obj.start).val() && $('#'+obj.end).val() )
                return $('#'+obj.start).val() + ' to ' + $('#'+obj.end).val();
            else
                return '';
        };
        confdefault["setValue"] =function(s,s1,s2)
        {
            $('#'+obj.start).val(s1);
            $('#'+obj.end).val(s2);
        };
        return confdefault;
    };
    configs["datetime"]=function () {
        confdefault["format"] = "YYYY-MM-DD HH:mm";
        confdefault["time"] = {
            enabled: true
        };
        confdefault["autoClose"] = true;
        confdefault["singleDate"] = true;
        confdefault["singleMonth"] = true;
        return confdefault;
    };
    configs["datetimerange"]=function () {
        var o=$("#"+obj.id);
        $("#"+obj.id).css("height","39px").css("width","100%");
        $("#"+obj.id).html('<input id="'+obj.start+'" type="text" style="width: '+((o.width()-16)/2)+'px;float: left" value=""> <label class="text-right middle" style="float: left;width: 15px;">to</label> <input id="'+obj.end+'"  size="20" type="text" style="width: '+((o.width()-16)/2)+'px;float: left" value="">');

        confdefault["format"] = "YYYY-MM-DD HH:mm";
        confdefault["time"] = {
            enabled: true
        };
        confdefault["separator"] = ' to ';
        confdefault["getValue"] = function()
        {
            if ($('#'+obj.start).val() && $('#'+obj.end).val() )
                return $('#'+obj.start).val() + ' to ' + $('#'+obj.end).val();
            else
                return '';
        };
        confdefault["setValue"] =function(s,s1,s2)
        {
            $('#'+obj.start).val(s1);
            $('#'+obj.end).val(s2);
        };
        return confdefault;
    };
    $(window).resize(function () {
       var  s=obj;
        if(s.type=="daterange" || s.type=="datetimerange")
        {
            var o=$("#"+s.id);
           $("#"+s.start).css("width",((o.width()-16)/2)+'px');
            $("#"+s.end).css("width",((o.width()-16)/2)+'px');
        }
    });
    return    $("#"+obj.id).dateRangePicker(Object.assign(configs[obj.type](),obj));

};