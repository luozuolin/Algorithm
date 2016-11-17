$.extend(true, $.fn.dataTable.defaults, {
    "searching": false,
    //"ordering": false,
    language: {
        info: "显示 _START_  到  _END_  项 总共  _TOTAL_ 项",
        "emptyTable":     "查无数据",
        "infoEmpty":      "查无数据",
        "processing":     "加载数据...",
        paginate: {first: "Premier", previous: "上一页", next: "下一页", last: "Dernier"},
        select: {
            rows: {
                _: " "
            }
        }
    }
});
//此控件主要用到的文件:
// jquery.dataTables.min.js
// moment.min.js
// dataTables.select.min.js
$.quake.comp.datatables=function (initsdata) {
    var self = this;
    this.table = {};
    this.table.inits = initsdata;
    this.reloadtable=function()
    {
        this.table.DataTable.ajax.reload(null, false);
    };
    this.initdata = function () {
        this.initHeader(this.table);
        this.initcolumns(this.table);
        this.table.DataTable = $('#' + this.table.inits.id).DataTable({
            searching: false,
            dom: "Bfrtip",
            ajax: {
                url: this.table.inits.dataurl,
               // data: JSON.stringify(this.table.inits.data),
                type: "POST",
                "data": function ( d ) {
                    return $.extend( {}, d, {
                        "data": JSON.stringify(self.table.inits.data())
                    })
                },
                error:function(e)
                {
                    //如果查找数据失败，则查看下是否缓存失效。
                    $.quake.comp.alert({type:'alert',infor: e.statusText});
                    $.quake.comp.refreshstatus();
                }
            },
          //  "scrollY":        "340px",
           // "scrollCollapse": true,
          //  "scrollX": true,
            "fnCreatedRow": function( nRow, aData, iDataIndex ) {
                //文字不换行，添加tooltip
                $(nRow).children("td").css("overflow", "hidden");
                $(nRow).children("td").css("max-width", "250px");
                $(nRow).children("td").css("white-space", "nowrap");
                $(nRow).children("td").css("text-overflow", "ellipsis");
                $(nRow).children("td").mouseover(function(e){
                    if(e.target.children.length==0) {
                        var tValue = e.target.innerText;
                        e.target.setAttribute("title", tValue);
                    }
                });
            },
            "processing": true,
            serverSide: true,
            select: {
                //style: 'multi'
                style:self.table.inits["selectstyle"]==null?"single": 'multi'
            },
            columns: this.table.inits.columns,
            order: this.table.inits.order==null?[]:this.table.inits.order
          //  stateSave: true   //页面reload时保持页面状态
        });
        if(this.table.inits.updateurl!=null)
        {
            $(document).on("click", "#" + this.table.inits.id + " .editor_edit",
                function () {
                   var  t=self;
                    t.tr=$(this).closest('tr');
                    return t.alertupdate(this, t.table, "update");
                });
            $(document).on("click", "#" + this.table.inits.id + " .editor_dele",
                function () {
                    var  t=self;
                    t.tr=$(this).closest('tr');
                    return t.alertupdate(this, t.table, "dele");
                });
            $(document).on("click", "#" + this.table.inits.id + " .checkAll",
                function () {
                    var  t1=self;
                    var t=$("#"+t1.table.inits.id)[0];
                    for(var i=0;i<t.rows.length;i++)
                    {
                        $(t.rows[i].cells[0]).children()[0].checked=$(this).is(":checked");
                        if($(this).is(":checked"))
                            $(t.rows[i]).addClass("selected");
                        else
                            $(t.rows[i]).removeClass("selected");
                    }
                });
            $(document).on("click", "#" + this.table.inits.id + "add",
                function () {
                    var  t=self;
                    return t.alertupdate(this, t.table, "add");
                });
            $(document).on("click", "#" + this.table.inits.id + "deleteSelected",
                function () {
                    var  t1=self;
                    var  t2=self;
                    var deletedata=[],t=$("#"+t1.table.inits.id)[0];
                    for(var i=1;i<t.rows.length-1;i++)
                    {
                        if($(t.rows[i].cells[0]).children().is(":checked"))
                            deletedata.push(t2.table.DataTable.rows(t.rows[i]).data()[0]);
                    }
                    $.post(t2.table.inits.updateurl + "deleteSelected", {data: JSON.stringify(deletedata)}, function () {
                        t2.table.DataTable.ajax.reload(null, false);
                        $("#" + t2.table.inits.id + " .checkAll").attr("checked",false);
                    },'json');

                });
            $(document).on("click", "#" + self.table.inits.id + "update", function () {
                return self.updatedata(this, self);
            });
            $(document).on("click", "#" + self.table.inits.id + "editor_cancle", function () {
                $('#' + self.table.inits.id + "modal").foundation('close');
            });
            // 处理单击事件
            $(document).on("click", "#" + this.table.inits.id + " tr",
                function () {
                    $($(this)[0].cells[0]).children()[0].checked=$(this).hasClass('selected');
                });
            //页面切换事件
            $("#" + this.table.inits.id).on( 'page.dt', function () {
                $("#"+$(this).attr("id")+" .checkAll").attr("checked",false);
            } );
        }
        //添加详细信息下拉框
        $("#" + self.table.inits.id + " tbody").on('click', 'td.details-control', function () {
            var o=self;
          var column=  o.table.inits.columns.filter(function (e){return e.className=="details-control";});
            if(column!=null)
            {

                var tr = $(this).closest('tr');
                var row = o.table.DataTable.row( tr );
                var id="details_control_div"+tr.closest("table").attr("id")+row[0][0];
                if ( row.child.isShown() ) {
                    // This row is already open - close it
                    row.child.hide();
                    tr.removeClass('shown');
                }
                else {
                    row.child("<div id='"+id+"' ></div>").show();
                    if (column[0]!=null && column[0].init != null) {
                        column[0].init(id,row.data());
                    }
                    else {
                        $("#"+id).html("没有init");
                    }
                    tr.addClass('shown');
                }
            }

        } );
        $(document).foundation();
        return self;
    };

    this.format=function format ( d ) {
        // `d` is the original data object for the row
     /*   return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
            '<tr>'+
            '<td>Full name:</td>'+
            '<td>'+"dddd"+'</td>'+
            '</tr>'+
            '<tr>'+
            '<td>Extension number:</td>'+
            '<td>'+"dddd"+'</td>'+
            '</tr>'+
            '<tr>'+
            '<td>Extra info:</td>'+
            '<td>And any further details here (images etc)...</td>'+
            '</tr>'+
            '</table>';*/
        return "<div id='details_control_div' ></div>";
    };
    this.initcolumns = function (table) {
        $(table.inits.columns).each(function (index,data) {
            data.orderable=data.orderable==null?false:data.orderable;
        });
        if (table.inits.updateurl != null) {
            var columns = [];
            columns.push({data: null,orderable:false,defaultContent: '<input name="checkboxid" type="checkbox">'});
            for (var i = 0; i < table.inits.columns.length; i++) {
                {
                    columns.push(table.inits.columns[i]);
                }
            }
            columns.push({
                data: null,
                orderable:false,
                defaultContent: '<a   class="editor_edit">修改</a> / <a  class="editor_dele">删除</a>'
            });
            table.inits.columns = columns;
        }
    };
    this.alertupdate = function (obj, table, type) {
        var html = "";
       self.table.inits.type=type;
        table.inits.type=type;
        for (var i = 0; i < table.inits.columns.length; i++) {
            if (table.inits.columns[i].edit != null) {
                html += '<div class="row"><div class="medium-6 columns"><label style="float:right">' + table.inits.columns[i].text + ':</label></div><div class="medium-6 columns"><input type="text" id="' + table.inits.columns[i].data + '"> </div></div>';
            }
        }
        if (html == "")
            return;
        $("#" + table.inits.id + "modal").html('<div id="edittitle"></div><div>' + html + '<div class="row"><div class="medium-6 columns"><input type="button"  value="取消" style="width: 100px;float: right;" class="secondary button" id="' + table.inits.id + 'editor_cancle"></div><div class="medium-6 columns"><input type="button"  value="确定" style="width: 100px" class="success button" id="' + table.inits.id + 'update"></div></div><button class="close-button" data-close aria-label="Close modal" type="button"><span aria-hidden="true">&times;</span></button></div>');
        $("#" + table.inits.id + "update").attr("tag", type + "," + $(obj).closest('table').attr("id"));
        if (type == "update" || type == "dele") {
//初始化弹出框数据 
            $("#edittitle").html(type == "update"?"编辑":"删除");
            var datas = ( type == "add" ? null : table.DataTable.rows($(obj).closest('tr')).data()[0]);
            for (var k in datas) {
                if ($("#" + k).is("input")) {
                    if (datas != null)
                        $("#" + k).val(datas[k]);
                }
            }
            for (var i = 0; i < table.inits.columns.length; i++) {
                if (table.inits.columns[i].edit != null && table.inits.columns[i].edit.init != null) {
                    table.inits.columns[i].edit.init(table.inits.columns[i], datas[table.inits.columns[i].data]);
                }
            }
        }
        else {
            $("#edittitle").html("新增");
            for (var i = 0; i < table.inits.columns.length; i++) {
                if (table.inits.columns[i].edit != null && table.inits.columns[i].edit.init != null) {
                    table.inits.columns[i].edit.init(table.inits.columns[i], "");
                }
            }
        }
        $('#' + table.inits.id + "modal").foundation('open');
       // $(".chosen-container").css("width","").addClass("text").addClass("medium-12");
    };
    this.updatedata = function (obj, table) {
        var datas = table.table.DataTable.rows(table.tr).data()[0];
        var data = getdata(table);
        $.post(table.table.inits.updateurl + $(obj).attr("tag").split(",")[0], {data: JSON.stringify(data)}, function (result) {
            $('#' + table.table.inits.id + "modal").foundation('close');
            $.quake.comp.alert({type:result.type,infor:result.message});
            table.table.DataTable.ajax.reload(function () {
                if(self.table.inits.type!="dele") {
                    $('#' + table.table.inits.id + ' tr').eq(table.table.DataTable.rows(table.tr)[0][0] + 2).addClass("selected");
                    $($('#' + table.table.inits.id + ' tr').eq(table.table.DataTable.rows(table.tr)[0][0] + 2)[0].cells[0]).children()[0].checked = true;
                    $("#" + table.table.inits.id + " .checkAll").attr("checked", false);
                }
            }, false);
        },'json');
    };
    function getdata(obj) {
        var data = (obj.tr == null ? {} : obj.table.DataTable.row(obj.tr).data());
        var columns = obj.table.inits.columns;
        if (obj.tr != null) {
            for (var i = 0; i < columns.length; i++) {
                if (columns[i].data != null && (columns[i].edit != null || columns.returnval != null)) {
                    data[columns[i].data] = columns.returnval != null ? columns.returnval : $("#" + columns[i].data).val();
                }
            }
        }
        else {
            for (var i = 0; i < columns.length; i++) {
                if (columns[i].data != null) {
                    if (columns[i].returnval != null)
                        data[columns[i].data] = columns[i].returnval;
                    else if (columns[i].edit != null)
                        data[columns[i].data] = $("#" + columns[i].data).length == 0 ? "" : $("#" + columns[i].data).val();
                    else
                        data[columns[i].data] = null;
                }
            }
        }
        return data;
    }
    this.initHeader = function (table) {
        var columns = table.inits.columns;
        if (columns == null) {
            return;
        }
        var header = "";
        for (var i = 0; i < columns.length; i++) {
            header += "<th>" + columns[i].text + "</th>"
        }
        if (table.inits.updateurl != null) {
            $('#' + table.inits.id).before('<button id="' + table.inits.id + 'add" type="button" class="success button" style="width:90px" >新增</button><button id="' + table.inits.id + 'deleteSelected" type="button" class="alert button" style="width:90px" >删除选中</button>');
            $('#' + table.inits.id).html('<thead><tr><th><input type="checkbox" class="checkAll"  ></th>' + header + '<th>操作</th></tr></thead><tfoot><tr><th><input type="checkbox"   class="checkAll" ></th>' + header + '<th>操作</th></tr></tfoot>');
            $('#' + table.inits.id).after('<div  class="reveal" id="' + table.inits.id + 'modal" data-reveal></div>');
        } else {
            $('#' + table.inits.id).html("<thead><tr>" + header + "</tr></thead><tfoot><tr>" + header + "</tr></tfoot>");
        }
    };
    this.close = function () {
        _close();
    };
    return this;
}    
