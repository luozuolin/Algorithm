//主要功能:个性化用户对时间的选择,日(今天,昨天),周(本周,上周),月(本月,上月),季度(本季度,上季度),年(本年,上一年)
//全部:全部时间区间,自定义;弹出daterangepicker来选择一个时间区间
// 此控件主要用到$.quake.comp.daterangepicker
//obj参数说明{id:"控件id":selecvalue:"默认选中的项":event:"选中触发的事件",type:"控件类型(today,preday,thisweek,preweek,thismonth,premonth,thisquarter,prequarter,thisyear,preyear,allrange,chooserange)"}
$.quake.comp.daterangeselect=function (data) {
    var self=this;
    this.formatDate="yyyy-MM-dd";
    this.data=data;
    this.tipStr="日期范围:";
    var obj=$("#"+data.id);
    this.daterangestart='2016-1-1';
    this.daterangeend='2016-1-7';
    this.idtostr={today:"今天",preday:"昨天",thisweek:"这周",preweek:"上周",thismonth:"本月",premonth:"上月",thisquarter:"本季度",prequarter:"上季度",thisyear:"今年",preyear:"去年",allrange:"全区间",chooserange:""};
    $("#"+data.id).on("click", function () {
       // if($("#daterangeselect"+data.id)
        $("#daterangeselect"+data.id).remove();
        var strVar="";
        strVar += "<div class='daterangeselect' id=\"daterangeselectdate6\" style=\"width:250px;background: white;top:" + (obj.position().top + 39) + "px;left:" + obj.position().left + "px;position:absolute\">";
        strVar += "  <div class=\"row\"><div class=\"large-12 columns\">日期范围<\/div><\/div>";
        strVar += "  <div class=\"row\">";
        strVar += "    <div class=\"large-6 columns\">";
        strVar += "      <input id=\"today\" type=\"radio\" name=\"range\"><label for=\"today\">今天<\/label>";
        strVar += "    <\/div>";
        strVar += "     <div class=\"large-6 columns\">";
        strVar += "      <input id=\"preday\" type=\"radio\" name=\"range\"><label for=\"preday\">昨天<\/label>";
        strVar += "    <\/div>";
        strVar += "  <\/div>";
        strVar += "  <div class=\"row\">";
        strVar += "    <div class=\"large-6 columns\">";
        strVar += "      ";
        strVar += "      <input id=\"thisweek\" type=\"radio\" name=\"range\"><label for=\"thisweek\">本周<\/label>";
        strVar += "    <\/div>";
        strVar += "  <div class=\"large-6 columns\">";
        strVar += "      ";
        strVar += "      <input id=\"preweek\" type=\"radio\" name=\"range\"><label for=\"preweek\">上周<\/label>";
        strVar += "    <\/div>";
        strVar += "  <\/div>";
        strVar += "  <div class=\"row\"><div class=\"large-6 columns\">";
        strVar += "      ";
        strVar += "      <input id=\"thismonth\" type=\"radio\" name=\"range\"><label for=\"thismonth\">本月<\/label>";
        strVar += "    <\/div>";
        strVar += "    <div class=\"large-6 columns\">";
        strVar += "      <input id=\"premonth\" type=\"radio\" name=\"range\"><label for=\"premonth\">上月<\/label>";
        strVar += "    <\/div>";
        strVar += "  <\/div>";
        strVar += "  <div class=\"row\"><div class=\"large-6 columns\">";
        strVar += "      ";
        strVar += "      <input id=\"thisquarter\" type=\"radio\" name=\"range\"><label for=\"thisquarter\">本季度<\/label>";
        strVar += "    <\/div>";
        strVar += "    <div class=\"large-6 columns\">";
        strVar += "      <input id=\"prequarter\" type=\"radio\" name=\"range\"><label for=\"prequarter\">上季度<\/label>";
        strVar += "    <\/div>";
        strVar += "  <\/div>";
        strVar += "  <div class=\"row\">";
        strVar += "  <div class=\"large-6 columns\">";
        strVar += "      <input id=\"thisyear\" type=\"radio\" name=\"range\"><label for=\"thisyear\">本年<\/label>";
        strVar += "    <\/div>";
        strVar += "   <div class=\"large-6 columns\">";
        strVar += "      <input id=\"preyear\" type=\"radio\" name=\"range\"><label for=\"preyear\">上年<\/label>";
        strVar += "    <\/div>";
        strVar += "<\/div>";
        strVar += "  <div class=\"row\"><div class=\"large-12 columns\">";
        strVar += "      ";
        strVar += "      <input id=\"allrange\" type=\"radio\" name=\"range\"><label for=\"allrange\">全部<\/label>";
        strVar += "    <\/div><\/div>";
        strVar += "  <div class=\"row\">";
        strVar += "    <div class=\"large-6 columns\">";
        strVar += "      <input id=\"chooserange\" type=\"radio\" name=\"range\"><label for=\"chooserange\">定制<\/label>";
        strVar += "    <\/div>";
        strVar += "  <\/div>";
        strVar += "  <div id='divdaterange'  style='display: none' class=\"row\">";
        strVar += "  <div id='daterange' class=\"large-12 columns\">";
        strVar += "    <\/div>";
        strVar += "<\/div>";
        strVar += "<\/div>";
        $("body").append(strVar);


       /* $("[name=range]").change(function (s,s1=self,s2=self,s3=self) {
            s1.data.type=$(this).attr("id");
            if($(this).attr("id")=="chooserange") {
                $("#divdaterange").show();
                $.quake.comp.daterangepicker({
                    id:"daterange",
                    type:"daterange",
                    start:"daterangestart",
                    end:"daterangeend"
                }).bind('datepicker-apply',function(event,obj)
                {
                    //This event will be triggered when user clicks on the apply button
                    console.log(obj);
                    s1.daterangestart=moment(obj.date1).format('YYYY-MM-DD');
                    s1.daterangeend=moment(obj.date2).format('YYYY-MM-DD');
                    s1.setselectdaterange("chooserange");
                    if(s1.data.event!=null)
                    {
                        s1.data.event(s1);
                    }
                    $("#daterangeselect"+data.id).remove();

                }).bind('datepicker-opened',function(event,obj)
                {
                    s1.calcPosition();

                });
                $("#daterangestart").val(s1.daterangestart);
                $("#daterangeend").val(s1.daterangeend);
            }
            else
            {
                s1.setselectdaterange($(this).attr("id"));
                $("#"+s1.data.id).val(s1.tipStr+s1.idtostr[$(this).attr("id")]);
              //  $("#"+s1.data.id).val($(this).attr("id"));
                if(s1.data.event!=null)
                {
                    s1.data.event(s1);
                }
                $("#daterangeselect"+data.id).remove();
            }

        });*/
        $("[name=range]").change(function (s) {
            self.data.type=$(this).attr("id");
            if($(this).attr("id")=="chooserange") {
                $("#divdaterange").show();
                $.quake.comp.daterangepicker({
                    id:"daterange",
                    type:"daterange",
                    start:"daterangestart",
                    end:"daterangeend"
                }).bind('datepicker-apply',function(event,obj)
                {
                    console.log(obj);
                    self.daterangestart=moment(obj.date1).format('YYYY-MM-DD');
                    self.daterangeend=moment(obj.date2).format('YYYY-MM-DD');
                    self.setselectdaterange("chooserange");
                    if(self.data.event!=null)
                    {
                        self.data.event(self);
                    }
                    $("#daterangeselect"+data.id).remove();

                }).bind('datepicker-opened',function(event,obj)
                {
                    self.calcPosition();

                });
                $("#daterangestart").val(self.daterangestart);
                $("#daterangeend").val(self.daterangeend);
            }
            else
            {
                self.setselectdaterange($(this).attr("id"));
                $("#"+self.data.id).val(self.tipStr+self.idtostr[$(this).attr("id")]);
                //  $("#"+s1.data.id).val($(this).attr("id"));
                if(self.data.event!=null)
                {
                    self.data.event(self);
                }
                $("#daterangeselect"+data.id).remove();
            }

        });
        $("#"+self.data.type).attr("checked","true");
        if(self.data.type=="chooserange")
        {
            // $("#"+self.data.type).click();
            $("#"+self.data.type).change();
            $("#"+self.data.type).trigger('click');
            console.log("chooserange");
        }

        $(window).resize(function () {
            self.calcPosition();
        });
        $(window).resize();
        });

    this.calcPosition=function () {
        console.log("resizefired");
        $("#daterangeselect"+data.id).css("top",(obj.position().top + 39)+"px");
        $("#daterangeselect"+data.id).css("left",obj.position().left+"px");
    };
    this.setselectdaterange=function (type,fun) {

        if(type=="chooserange") {
            $("#" + data.id).val(this.tipStr + this.daterangestart + " - " + this.daterangeend);
        }
        else {
            this.daterangestart=$.quake.comp.daterange(type).split(',')[0];
            this.daterangeend=$.quake.comp.daterange(type).split(',')[1];
            console.log(type+" "+this.daterangestart+" "+this.daterangeend);
        }
    };
    return self;
};