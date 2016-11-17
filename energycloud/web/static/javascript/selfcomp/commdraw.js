/**
 * Created by luozl on 2016/8/10.
 */
//画图
$.quake.comp.commdraw={};
var hashIDAddedJson={};
$.quake.comp.commdraw.draw=function(data)
{
    var svgNS="http://www.w3.org/2000/svg";
    var svgObj=document.createElementNS(svgNS,"svg");
    svgObj.setAttributeNS(null,"id","svgID");
    svgObj.setAttributeNS(null,"version","1.1");
    //首先设置页面的基本属性，接着画对象，最后画连线
    var CommonInfo=$(data).find("CommonInfo")[0];
    svgObj.setAttributeNS(null,"height",$(CommonInfo).find("Height").text());
    svgObj.setAttributeNS(null,"width",$(CommonInfo).find("Width").text());
    svgObj.setAttributeNS(null,"style","background:#"+$(CommonInfo).find("Background").text().substring(3,9));
    $("body").attr("style","text-align:center;background:#"+$(CommonInfo).find("Background").text().substring(3,9));
    //画控件
    if($("#svgid").height()<$(CommonInfo).find("Height").text())
        $("#svgid").css("height",$(CommonInfo).find("Height").text());
    if($("#svgid").width()<$(CommonInfo).find("Width").text())
        $("#svgid").css("width",$(CommonInfo).find("Width").text());
    $("#svgid")[0].appendChild(svgObj);

    var DesignerItem=$(data).find("DesignerItem");
    for(var i=0;i<DesignerItem.length;i++)
    {
        var item=$(DesignerItem[i]);
        var obj;
        var content=$(item.find("Content")[0].innerHTML.replace('&lt;','<').replace('&gt;','>'));
        hashIDAddedJson["id"+item.find("ID").text().replace(new RegExp("-","gm"),"")]=item.find("AddedJson").text();
        if(content[0].localName=="img")//画图片
        {
            obj=document.createElementNS(svgNS,"image");
            obj.setAttributeNS(null,"id","id"+item.find("ID").text().replace(new RegExp("-","gm"),""));
            obj.setAttributeNS(null,"x",item.find("Left").text());
            obj.setAttributeNS(null,"y",item.find("Top").text());
            obj.setAttributeNS(null,"width",item.find("Width").text());
            obj.setAttributeNS(null,"height",item.find("Height").text());
            obj.setAttributeNS(null,"cursor","pointer");
            var image=$(content)[0].attributes["source"].value;
            obj.setAttributeNS("http://www.w3.org/1999/xlink","href","/images/"+image.substring(41,image.length));
            obj.setAttribute("tag",item.find("AddedJson").text());
            var    obj1=document.createElementNS(svgNS,"a");
            obj1.appendChild(obj);
            svgObj.appendChild(obj1);
        }
        else if(content[0].localName=="label")//画文本
        {
            obj=document.createElementNS(svgNS,"foreignObject");
            obj.setAttribute("id","id"+item.find("ID").text().replace(new RegExp("-","gm"),""));
            obj.setAttribute("x",item.find("Left").text());
            obj.setAttribute("y",item.find("Top").text());
            obj.setAttribute("width",item.find("Width").text());
            obj.setAttribute("height",item.find("Height").text());
            obj.setAttribute("tag",item.find("AddedJson").text());
            $('<p align="center" style="color:white;font-size:'+$(item.find("Content").text()).attr("FontSize")+'px">'+$(item.find("Content").text()).text()+'</p>').appendTo(obj);
            svgObj.appendChild(obj);
        }
        else if(content[0].localName=="rectangle")//画长方形
        {
            obj=document.createElementNS(svgNS,"rect");
            obj.setAttributeNS(null,"id","id"+item.find("ID").text().replace(new RegExp("-","gm"),""));
            obj.setAttributeNS(null,"x",item.find("Left").text());
            obj.setAttributeNS(null,"y",item.find("Top").text());
            obj.setAttributeNS(null,"width",item.find("Width").text());
            obj.setAttributeNS(null,"height",item.find("Height").text());
            obj.setAttributeNS(null,"fill","#"+$(content[0]).attr("fill").substring(3,9));
            obj.setAttributeNS(null,"cursor","pointer");
            //  obj.textContent=(($(content)[0].attributes["tooltip"].value).substring(0,9));
            //    obj.setAttributeNS(null,"font-size","12");
            svgObj.appendChild(obj);
        }
    }
    //画连线
    var Connections=$(data).find("Connection");
    //按照两个点确定连线
    var step=0;
    var p1,p2;
    //两个点相对于控件的位置
    var StartName,EndName;
    //总共六个点
    var p1x,p1y,p2x,p2y,p3x,p3y,p4x,p4y,p5x,p5y,p6x,p6y;
    for(var i=0;i<Connections.length;i++) {
        var item = $(Connections[i]);
        var obj = document.createElementNS(svgNS, "polyline");
        var startX,startY,startHeight,startWidth,endX,endY,endHeight,endWidth;
        p1 = $("#id" + item.find("SourceID").text().replace(new RegExp("-", "gm"), ""));
        p2 = $("#id" + item.find("SinkID").text().replace(new RegExp("-", "gm"), ""));
        StartName = item.find("SourceConnectorName").text();
        EndName = item.find("SinkConnectorName").text();
        startX=p1[0].x.animVal.value!=null?p1[0].x.animVal.value:p1[0].x.animVal[0].value;
        startY=p1[0].y.animVal.value!=null?p1[0].y.animVal.value:p1[0].y.animVal[0].value;
        startHeight=p1[0].height!=null?p1[0].height.animVal.value:parseFloat(p1.attr("height"));
        startWidth=p1[0].width!=null?p1[0].width.animVal.value:parseFloat(p1.attr("width"));



        if(p1[0].tagName.toLowerCase()=="text")
        {
            startX=parseFloat(p1.attr("x"));
            startY=parseFloat(p1.attr("y"));
            startHeight=p1.height();
            startWidth=p1.width();
        }else {
            startX=p1[0].x.animVal.value!=null?p1[0].x.animVal.value:p1[0].x.animVal[0].value;
            startY=p1[0].y.animVal.value!=null?p1[0].y.animVal.value:p1[0].y.animVal[0].value;
            startHeight=p1[0].height!=null?p1[0].height.animVal.value:parseFloat(p1.attr("height"));
            startWidth=p1[0].width!=null?p1[0].width.animVal.value:parseFloat(p1.attr("width"));
        }


        if(p2[0].tagName.toLowerCase()=="text")
        {
            endX=parseFloat(p2.attr("x"));
            endY=parseFloat(p2.attr("y"));
            endHeight=p2.height();
            endWidth=p2.width();
        }else {
            endX=p2[0].x.animVal.value!=null?p2[0].x.animVal.value:p2[0].x.animVal[0].value;
            endY=p2[0].y.animVal.value!=null?p2[0].y.animVal.value:p2[0].y.animVal[0].value;
            endHeight=p2[0].height!=null?p2[0].height.animVal.value:parseFloat(p2.attr("height"));
            endWidth=p2[0].width!=null?p2[0].width.animVal.value:parseFloat(p2.attr("width"));
        }

        if(isNaN(startX) || isNaN(startY) || isNaN(startHeight) || isNaN(startWidth) || isNaN(endX) || isNaN(endY)  || isNaN(endHeight) || isNaN(endWidth))
        {
        }
        switch (StartName.toUpperCase()) {
            case "LEFT":
                p1x = startX;
                p1y = startY + startHeight / 2;
                p2x = p1x - step;
                p2y = p1y;
                break;
            case "RIGHT":
                p1x = startX + startWidth;
                p1y = startY + startHeight / 2;
                p2x = p1x + step;
                p2y = p1y;
                break;
            case "TOP":
                p1x = startX + startWidth / 2;
                p1y = startY;
                p2x = p1x;
                p2y = p1y - step;
                break;
            case "BOTTOM":
                p1x = startX + startWidth / 2;
                p1y = startY + startHeight ;
                p2x = p1x;
                p2y = p1y + step;
                break;
        }
        switch (EndName.toUpperCase()) {
            case "LEFT":
                p6x = endX;
                p6y = endY + endHeight / 2;
                p5x = p6x - step;
                p5y = p6y;
                break;
            case "RIGHT":
                p6x = endX + endWidth;
                p6y = endY + endHeight / 2;
                p5x = p6x + step;
                p5y = p6y;
                break;
            case "TOP":
                p6x = endX + endWidth / 2;
                p6y = endY;
                p5x = p6x;
                p5y = p6y - step;
                break;
            case "BOTTOM":
                p6x = endX + endWidth / 2;
                p6y = endY + endHeight ;
                p5x = p6x;
                p5y = p6y + step;
                break;
        }

        //接着画第三第四个点
        if(p2y>p5y)//第一个点和第二个带点交换，第五个点和第六个点交换，p1,p2交换
        {
            var tempx=p1x;
            var tempy=p1y;
            p1x=p6x;
            p1y=p6y;
            p6x=tempx;
            p6y=tempy;
            tempx=p2x;
            tempy=p2y;
            p2x=p5x;
            p2y=p5y;
            p5x=tempx;
            p5y=tempy;
            p1=$("#id" + item.find("SinkID").text().replace(new RegExp("-", "gm"), ""));
            p2=$("#id" + item.find("SourceID").text().replace(new RegExp("-", "gm"), ""));
            StartName = item.find("SinkConnectorName").text();
            EndName = item.find("SourceConnectorName").text();
            //start(x,y,height,width)和end(x,y,height,width)交换
            tempx=startX;
            tempy=startY;
            startX=endX;
            startY=endY;
            endX=tempx;
            endY=tempy;
            tempx=startWidth;
            tempy=startHeight;
            startWidth=endWidth;
            startHeight=endHeight;
            endWidth=tempx;
            endHeight=tempy;
        }
        switch (StartName.toUpperCase())
        {
            case "LEFT":
                switch (EndName.toUpperCase()) {
                    case "LEFT":
                        p3x = p2x > p5x ? p5x : p2x;
                        p3y = p2y > p5y ? p5y : p2y;
                        p4x = p5x;
                        p4y = p5y;
                        break;
                    case "RIGHT":
                        if(p2x>p5x)
                        {
                            p3x=(p6x+startX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        else
                        {
                            p3x=p2x;
                            p3y=(p1y+endY)/2;
                            p4x=p5x;
                            p4y=p3y;
                        }
                        break;
                    case "TOP":
                        if(p2x>p5x)
                        {
                            p3x=p5x;
                            p3y=p2y;
                            p4x=p5x;
                            p4y=p5y;

                        }
                        else
                        {
                            p3x=p2x;
                            p3y=(startX+startHeight+endY)/2;
                            p4x=p5x;
                            p4y=p3y
                        }
                        break;
                    case "BOTTOM":
                        if(p2x>p5x)
                        {
                            p3x=(endX+endWidth+startX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        else
                        {
                            p3x=p2x;
                            p3y=p5y;
                            p4x=p5x;
                            p4y=p5y;
                        }
                        break;

                }
                break;
            case "RIGHT":
                switch (EndName.toUpperCase())
                {
                    case "LEFT":
                        if(p2x>p5x)
                        {
                            p3x=p2x;
                            p3y=(startY+startHeight+endY)/2;
                            p4x=p5x;
                            p4y=p3y;
                        }
                        else
                        {
                            p3x=(startX+startWidth+endX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        break;
                    case "RIGHT":
                        if(p2x>p5x)
                        {
                            p3x=p2x;
                            p3y=p5y;
                            p4x=p3y;
                            p4y=p3y;

                        }
                        else
                        {
                            p3x=p5x;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p3y;

                        }
                        break;
                    case "TOP":
                        if(p2x>p5x)
                        {
                            p3x=p2x;
                            p3y=(startY+startHeight+endY)/2;
                            p4x=p5x;
                            p4y=p3y;


                        }
                        else
                        {
                            p3x=p5x;
                            p3y=p2y;
                            p4x=p5x;
                            p4y=p5y;
                        }
                        break;
                    case "BOTTOM":
                        if(p2x>p5x)
                        {
                            p3x=p2x;
                            p3y=p5y;
                            p4x=p3x;
                            p4y=p3y;
                        }
                        else
                        {
                            p3x=(startX+startWidth+endX)/2;
                            p3y=p2y;
                            p4x=p3y;
                            p4y=p5y;
                        }
                        break;

                }
                break;
            case "TOP":
                switch (EndName.toUpperCase())
                {
                    case "LEFT":
                        if(p2x>p5x)
                        {
                            p3x=p5x;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p3y;
                        }
                        else
                        {
                            p3x=(startX+startWidth+endX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        break;
                    case "RIGHT":
                        if(p2x>p5x)
                        {
                            p3x=(endX+endWidth+startX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        else
                        {
                            p3x=p5x;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p3y;
                        }
                        break;
                    case "TOP":
                        p3x=p5x;
                        p3y=p2y;
                        p4x=p3x;
                        p4y=p3y;
                        break;
                    case "BOTTOM":
                        if(p2x>p5x)
                        {
                            p3x=(endX+endWidth+startX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        else
                        {
                            p3x=(startX+startWidth+endX)/2;
                            p3y=p2y;
                            p4x=p3x;
                            p4y=p5y;
                        }
                        break;

                }
                break;
            case "BOTTOM":
                switch (EndName.toUpperCase())
                {
                    case "LEFT":
                        if(p2x<p5x)
                        {
                            p3x=p2x;
                            p3y=p5y;
                            p4x=p5x;
                            p4y=p3y;
                        }
                        else
                        {
                            p3x=p2x;
                            p3y=(p1y+endY)/2;
                            p4x=p5x;
                            p4y=p3y;

                        }
                        break;
                    case "RIGHT":
                        if(p2x>p5x)
                        {
                            p3x=p2x;
                            p3y=p5y;
                            p4x=p5x;
                            p4y=p5y;
                        }
                        else
                        {
                            p3x=p2x;
                            p3y=(p1y+endY)/2;
                            p4x=p5x;
                            p4y=p3y;
                        }
                        break;
                    case "TOP":
                        p3x=p2x;
                        p3y=(p1y+endY)/2;
                        p4x=p5x;
                        p4y=p3y;
                        break;
                    case "BOTTOM":
                        p3x=p2x;
                        p3y=p5y;
                        p4x=p5x;
                        p4y=p5y;
                        break;

                }
                break;

        }
        obj.setAttributeNS(null, "points", p1x + "," + p1y + " " + p2x + "," + p2y + " " + p3x + "," + p3y + " " + p4x + "," + p4y + " " + p5x + "," + p5y + " " + p6x + "," + p6y + " ");
        if(item.find("AddedJson").text()!="") {

            var varstlye = $.parseJSON(item.find("AddedJson").text());

            obj.setAttributeNS(null, "style", "fill:none;stroke:#" + varstlye.Stroke.substring(3, 9) + ";stroke-width:" + varstlye.StrokeThickness);
        }
        else
            obj.setAttributeNS(null, "style", "fill:none;stroke:#" + ($(item).find("Stroke").text()==""?"FFFFFF":$(item).find("Stroke").text().substring(3,9)) + ";stroke-width:" + $(item).find("StrokeThickness").text());
        svgObj.appendChild(obj);
    }
    //设置长方形在页面最上层
    $("rect").each(function() {
        svgObj.getElementById($(this).attr("id")).parentNode.appendChild(svgObj.getElementById($(this).attr("id")));
    });
};
