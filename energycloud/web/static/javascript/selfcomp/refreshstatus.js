//刷新缓存状态，缓存失效时跳到登录页
$.quake.comp.refreshstatus=function (obj) {
    $.ajax({
        url: "/refreshstatus",
        dataType: 'json',
        type:'POST',
        success: function (e) {
            if(e.userID!=null)
            {
                $.quake.comp.confirm({title:"缓存失效",html:"缓存失效，返回登录页面?",ok:"ok",cancle:"cancle"});
            }
        },
        error: function (e) {
            $.quake.comp.confirm({title:"缓存失效",html:"缓存失效，返回登录页面?",ok:"ok",cancle:"cancle"});
        }
    });
    function  ok() {
        window.location.href="/";
    }
    function cancle() {
        return true;
    }
};