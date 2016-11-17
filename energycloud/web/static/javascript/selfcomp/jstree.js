//jstree控件是在jstree.min.js的基础上进行的开发
//主要功能是实现下拉树选择框和树形菜单的
//jstree源码的功能参考：https://www.jstree.com/
//initsdata的主要参数包括：id(控件id)，multiple(控件类型：是否复选(true,false,默认复选(true))),treeHtml(控件的初始化数据)

$.quake.comp.jstree=function(initsdata)
{
    var self=this;
    this.data=initsdata;
    this.ids=[];
    this.text=[];
    var obj=$("#"+initsdata["id"]);
    obj.css("cursor","pointer");
    obj.attr("readonly","readonly").attr("placeholder","请选择");
    //$(window).resize(function () {self.calcPosition(); });
    $(window).on('resize',function(){self.calcPosition()});
    this.calcPosition=function () {
        $('#div'+self.data["id"]).css("top",(obj.offset().top + 39)+"px");
        $('#div'+self.data["id"]).css("left",obj.offset().left+"px");
    };
    $("#"+initsdata["id"]).on("click", function () {
        if($('#div'+initsdata["id"]).length==0)
        {
         //   var strHTML= '<div id="div'+initsdata["id"]+'" class="ssss" style="border:1px solid grey;background: white;top:' + (obj.position().top + 39) + "px;left:" + obj.position().left + 'px;position:absolute\"><input type="text" id="data'+initsdata["id"]+'" style="width:'+($(obj).width()+18)+'px"> <div class="row" id="html'+initsdata["id"]+'"> <ul> <li id="di0">根目录 <ul> <li id="di1" >1<ul><li id="di11">1.1</li><li  id="di12">1.2</li> <li id="di13">1.3</li></ul></li> <li id="di2">2<ul><li id="di21">2.1</li><li id="di22">2.2<ul><li id="di221">2.2.1</li><li id="di222">2.2.2</li><li id="di223">2.2.3</li></ul></li><li id="di23">2.3</li><li id="di24">2.4</li></ul></li> </ul> </li> </ul></div></div>';
       var strHTML= '<div id="div'+initsdata["id"]+'" class="ssss" style="height:300px ;overflow:hidden;overflow-y:auto;border:1px solid grey;background: white;top:' + (obj.position().top + 39) + "px;left:" + obj.position().left + 'px;position:absolute\"><input type="text" id="data'+initsdata["id"]+'" style="width:'+($(obj).width()+18)+'px"> <div class="row" id="html'+initsdata["id"]+'"> '+initsdata["treehtml"]+'</div></div>';

            $("body").append(strHTML);


            $("#html"+initsdata["id"]+" li").each(function()
            {
                //如果是叶子节点，则修改图片
                if($(this).children().length==0)
                {
                  //  $(this).attr("data-jstree",'{"icon":"/images/file.png"}');
                }
            });
            $('#html'+initsdata["id"]).bind("loaded.jstree", function(event, data) {
                data.instance.open_all();
                //选中
              //  $('#jstree').jstree(true) .select_node('mn1');

                $("#html"+initsdata["id"]+" .jstree-anchor").each(function(i){
                    var nodeid=$(this).parent().attr("id");
                    if($(this).parent().attr("isselected")=="true")
                        $('#html'+initsdata["id"]).jstree(true).select_node($("#"+nodeid));
                });
            }).on('changed.jstree', function (e, data) {
                var i, j;
                self.text=[];
                self.ids=[];
                for(i = 0, j = data.selected.length; i < j; i++) {
                    self.text.push(data.instance.get_node(data.selected[i]).text);
                    self.ids.push(data.instance.get_node(data.selected[i]).id);
                }
                obj.val(self.text.join(', '));
            });
            //单选复选框：默认是复选框
            if(initsdata["multiple"] !=null)
            {
                $('#html'+initsdata["id"]).jstree({ "core" : {"multiple" : initsdata["multiple"], "animation" : 0}});
            }
            else
            {
                $('#html'+initsdata["id"]).jstree({ "plugins" : [ "wholerow", "checkbox" ]});
            }
            var ids=self.ids;
            for(var id in self.ids)
            {
                $('#html'+initsdata["id"]).jstree('select_node', ids[id]);
            }
            $("#data"+initsdata["id"]).change(function(){
                //  $("#html1").jstree(true).hide_node($(".jstree-anchor").parent());
                $("#html"+initsdata["id"]+" .jstree-anchor").each(function(i){
                    $('#html'+initsdata["id"]).jstree(true).hide_node($(this).parent());
                });
                $("#html"+initsdata["id"]+" .jstree-anchor").each(function(i){

                    if($(this).text().trim().search(new RegExp($("#data"+initsdata["id"]).val(),"ig"))>-1)
                    {
                        var nodeid=$(this).parent().attr("id");
                        while (nodeid!=null && $("#"+nodeid).attr("role")=="treeitem")
                        {
                            $('#html'+initsdata["id"]).jstree(true).show_node($("#"+nodeid));
                            nodeid=$("#"+nodeid).parent().parent().attr("id");
                        }
                    }
                });
            });
            $(window).resize();
        }
        else {
           $('#div'+initsdata["id"]).remove();
        }
        $(window).click(function(e) {
          if($("#"+initsdata["id"])[0]!=$(e.target)[0] && $('#div'+initsdata["id"]).has($(e.target)).length==0 )
              $('#div'+initsdata["id"]).remove();
        });
    });
    return self;
};