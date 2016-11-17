
//页面加载框架自适应高度
$(function(){	
	frameAutoHeight();
	//firstMenu();
	$(window).resize();
	});

//框架自适应高度
function frameAutoHeight(){	
	//框架内容高度
	var winHeight = $(window).height();
	var frameTopHeight= $("#frameTop").height();
	var frmaeMiddleHeight = $("#frameMiddel").height();
	var frameBottomHeight = $("#frameBottom").height();
	//框架充满屏幕高度
	var  frameMiddleRealHeight = winHeight-frameTopHeight-frameBottomHeight;
	var  frameRightRealHeight = winHeight-frameTopHeight-frameBottomHeight;
  // $("#frameMiddelRight").css("height",frameRightRealHeight);
  // $("#frameMiddelLeft, #frameLeftToggle").css("height",frameMiddleRealHeight);
   $("#frameLeftToggle").css("position","relative");
   $("#frameLeftToggle img").css("left","0%");
   $("#frameLeftToggle img").css("top","50%");
   $("#frameLeftToggle img").css("position","absolute");
	}
//框架导航显示隐藏
function hideNavi(){
	$("#frmTitle").hide();
	$(".main_right").css("width",$(document).width()-$("#frameLeftToggle").width());
	 $("#frameLeftToggle").html("<img src='/commpage/img/right.gif'  onclick='showNavi()' title='展开导航菜单'/>");
     frameAutoHeight();
	$(window).resize();
	}
function showNavi(){
	$("#frmTitle").show();
	$('.main_right').css("width",$(document).width()-$("#frmTitle").width()-$("#frameLeftToggle").width());
		$("#frameLeftToggle").html("<img src='/commpage/img/left.gif'  onclick='hideNavi()' title='隐藏导航菜单'/>");
		frameAutoHeight();
	$(window).resize();
	}
$(window).resize(function () {
	$('.main_right').css("width",$(document).width()-($(".main_left").css("display")=="none"?0:$("#frmTitle").width())-$("#frameLeftToggle").width());
});
$(window).scroll(function() {
	$(window).resize();
});

	

