// Quake Web 配置文件
$.quake = {
    // 延后执行列表
    listfn : [],
    // 注册延后函数
    regReady : function (func) {
        this.listfn.push(func);
    },
    // 页面文档加载
    readyfn : function () {},
    // Vue组件注册容器
    vuecomp : {},
    //添加新插件
    comp : {},
    // 工具类, 通过两个特殊的class标记  
    postvue : function () {
        $("[vue] input").each(function () {
            if ($(this).attr("vdata") != "") {
                $(this).attr("v-model", $(this).attr("vdata"));
            }
        });
        $("[vue]").each(function () {
                var vuedata = {};
                $(this).children().filter("[vdata]").each(function () {
                    classArray = $(this).attr("vdata").split(" ");
                    for (var i = 0; i < classArray.length; i++) {
                        vuedata[classArray[i]] = "";
                    }
                });
                new Vue({
                    el: "#" + $(this).attr("id"),
                    data: vuedata
                });
            }
        );
    }
};

// 注册所有文档的加载完成后的回调
$(document).ready(function () {
    $(document).foundation();
    $.quake.postvue();
    $.quake.readyfn();
    $.quake.listfn.forEach(function (el, idx, array) {
        el();
    })
});


