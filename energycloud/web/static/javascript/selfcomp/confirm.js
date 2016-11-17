// confirm弹出窗口,形成遮罩层,有确认和取消按钮
//基本参数:html:需要显示的值,cancle:取消按钮事件,ok:确认按钮事件
$.quake.comp.confirm=function (obj) {
  //  console.log($("#quakecompconfirm")[0]);
    if($("#quakecompconfirm")[0]==null)
    {
        $("body").append('<div class="reveal" id="quakecompconfirmmodal" data-reveal></div>');
    }
    //默认情况有确认取消
    if(obj.type==null)
    {
        $("#quakecompconfirmmodal").html('<div id="edittitle">'+(obj.title!=null?obj.html:'编辑')+'</div><form><div class="row"><span class="columns" style="text-align:center">' +obj.html + '</span></div><div class="row"><div class="medium-6 columns"><input type="button"  value="取消" style="width: 100px;float: right;"  class="secondary button" onclick="'+obj.cancle+'();"></div><div class="medium-6 columns"><input type="button"  value="确定" style="width: 100px" class="success button"  onclick="'+obj.ok+'();"></div></div><button class="close-button" data-close aria-label="Close modal" type="button"><span aria-hidden="true">&times;</span></button></form>');
    }
    else if(obj.type=="alert")//只有确认框
    {
        $("#quakecompconfirmmodal").html('<div id="edittitle">'+(obj.title!=null?obj.html:'查看')+'</div><form><div class="row"><span class="columns" style="text-align:center">' +obj.html + '</span></div><div class="row"><div style=" text-align:center"><input type="button"  value="确定" style="width: 100px;margin-top: 2px" class="success button"  onclick="'+(obj.ok==null?"":obj.ok+"();")+'"></div></div><button class="close-button" data-close aria-label="Close modal" type="button"><span aria-hidden="true">&times;</span></button></form>');
    }
    //$("body").append("<div id='alert' style='position: absolute;top: "+$(document.body).height()/2+"px; left: "+$(document.body).width()/2+"px'><span class='"+config["type"]+" label'>"+config["infor"]+"</span></div>");
    $(document).foundation();
    //$('#' + table.inits.id + "modal").foundation('open');
    $('#quakecompconfirmmodal').foundation('open');
    $("#quakecompconfirmmodal").on("click", ".secondary,  .success",
        function () {
            $('#quakecompconfirmmodal').foundation('close');
        });
    return this;
};