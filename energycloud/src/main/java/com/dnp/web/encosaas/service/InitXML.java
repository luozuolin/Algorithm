package com.dnp.web.encosaas.service;

import com.dnp.util.JsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by luozl on 2016/10/21.
 */
public class InitXML {
    String data;
    @Autowired
    FirstPageService firstPageService;
    //如果数据库中存在组态串则直接返回，否则按照回路组下面回路的关系生成组态串
    public    String getXML(List<HashMap> Circuits)
    {
         return    getInitXml(Circuits);
    }
      String getInitXml( List<HashMap> dt)
    {
        //SELECT g.numCircuitID,g.numCircuitGroupID,p.numPCircuitID,c.varCircuitName
        circuitnode supernode = new circuitnode("0","-1","超级父节点","父节点");
        Hashtable ht = new Hashtable();
        circuitnode[] circuitnodes = new circuitnode[dt.size()];
        for (int i = 0; i < dt.size(); i++)
        {
            ht.put(String.valueOf(dt.get(i).get("numCircuitID")) , new circuitnode(String.valueOf(dt.get(i).get("numCircuitID")), String.valueOf(dt.get(i).get("numPCircuitID")), String.valueOf(dt.get(i).get("varCircuitName")), String.valueOf(dt.get(i).get("varID"))));
            supernode.children.add((circuitnode)ht.get(String.valueOf(dt.get(i).get("numCircuitID"))));
        }
        for (int i = 0; i < dt.size(); i++)
        {
            if (ht.get(String.valueOf(dt.get(i).get("numPCircuitID"))) != null)
            {
                ((circuitnode)ht.get(String.valueOf(dt.get(i).get("numPCircuitID")))).children.add((circuitnode)ht.get(String.valueOf(dt.get(i).get("numCircuitID"))));
                supernode.children.remove((circuitnode)ht.get(String.valueOf(dt.get(i).get("numCircuitID"))));
            }
        }
        visittree(supernode,0,0,commonInfo.Width);
     //   int i=1;
        //表之间的关系保存好之后，接下来主要是画图
        //每行列平均分配
        List<designeritem> p =FindAll(0);
        int row=0;
        //纵向每个控件占有高度
        if(maxrow>0) {
            int hw = commonInfo.Height / maxrow;
            while (p.size() > 0) {
                row++;
                //向上取整
                int w = (int) (commonInfo.Width / p.size() + 0.5);
                int top = hw * (row - 1) + (hw - commonInfo.imageheigth) / 2;
                int imagewidth = commonInfo.imagewidth;
                if (w < commonInfo.imagewidth)
                    imagewidth = w - 2;

                for (int i = 0; i < p.size(); i++) {
                    p.get(i).Left = i * w + (w - imagewidth) / 2;
                    p.get(i).Top = top;
                    p.get(i).Width = imagewidth;
                    p.get(i).Height = imagewidth;
                    //添加label控件对电表进行说明
                    designeritem label = new designeritem();
                    label.Left = p.get(i).Left;
                    label.Top = p.get(i).Top - p.get(i).Height;
                    label.Height = 33;
                    label.Width = p.get(i).Width;
                    label.ID = java.util.UUID.randomUUID().toString();
                    label.numCircuitID = p.get(i).numCircuitID;
                    label.numPCircuitID = p.get(i).numPCircuitID;
                    label.Content = "&lt;Label BorderBrush=\"#FF000000\" BorderThickness=\"0,0,0,0\" Foreground=\"#FFFFFFFF\" FontSize=\"12\" HorizontalContentAlignment=\"Center\" VerticalContentAlignment=\"Center\" Tag=\"label\" ToolTip=\"label\" IsHitTestVisible=\"False\" xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"&gt;" + p.get(i).varID + "&lt;/Label&gt";
                    designeritems.add(label);

                    //
                    //添加label控件显示电表的数据
                    designeritem labelvalue = new designeritem();
                    labelvalue.Left = p.get(i).Left;
                    labelvalue.Top = p.get(i).Top +p.get(i).Height;
                    labelvalue.Height = 33;
                    labelvalue.Width = p.get(i).Width;
                    labelvalue.ID = java.util.UUID.randomUUID().toString();
                    labelvalue.numCircuitID = p.get(i).numCircuitID;
                    labelvalue.numPCircuitID = p.get(i).numPCircuitID;
                    labelvalue.AddedJson="{\"Column1\":\"\",\"Tag\":\"meter\",\"ID\":\""+p.get(i).numCircuitID+"\",\"Column3\":\"\",\"Column2\":\"\"}";
                    labelvalue.Content = "&lt;Label BorderBrush=\"#FF000000\" BorderThickness=\"0,0,0,0\" Foreground=\"#FFFFFFFF\" FontSize=\"12\" HorizontalContentAlignment=\"Center\" VerticalContentAlignment=\"Center\" Tag=\"label\" ToolTip=\"label\" IsHitTestVisible=\"False\" xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"&gt;&lt;/Label&gt";
                    designeritems.add(labelvalue);
                }
                p = FindAll(row);
            }
        }
        return "<Root>"+getDesignerItems()+getConnections()+CommonInfor()+"</Root>";
    }
    String CommonInfor()
    {
        return  "<CommonInfo>"+"<Height>"+commonInfo.Height+"</Height>"+"<Width>"+commonInfo.Width+"</Width>"+"<Background>"+commonInfo.Background+"</Background>"+"</CommonInfo>";
    }
    //获取所有控件的的xml串
    String  getDesignerItems()
    {
        String ret="<DesignerItems>";
        for(int i=0;i<designeritems.size();i++)
        {
            ret+="<DesignerItem>";

            ret+="<Left>"+designeritems.get(i).Left+"</Left>";
            ret+="<Top>"+designeritems.get(i).Top+"</Top>";
            ret+="<Width>"+designeritems.get(i).Width+"</Width>";
            ret+="<Height>"+designeritems.get(i).Height+"</Height>";
            ret+="<ID>"+designeritems.get(i).ID+"</ID>";
            ret+="<zIndex>"+designeritems.get(i).zIndex+"</zIndex>";
            ret+="<IsGroup>"+designeritems.get(i).IsGroup+"</IsGroup>";
            ret+="<ParentID>"+designeritems.get(i).ParentID+"</ParentID>";
            ret+="<Content>"+designeritems.get(i).Content+"</Content>";
            ret+="<AddedJson>"+designeritems.get(i).AddedJson+"</AddedJson>";
            ret+="</DesignerItem>";
        }
        return   ret+"</DesignerItems>";
    }
    //获取所有连接线的xml串
    String  getConnections()
    {
        Set<String> keys = htdesigneritems.keySet();
        designeritem de=null;
        String  ret="<Connections>";
        for(String key: keys) {
            de = FindOne(((designeritem) htdesigneritems.get(key)).numPCircuitID);
            if (de != null) {
                ret+="<Connection>";
                ret+="<SourceID>"+de.ID+"</SourceID>";
                ret+="<SinkID>"+key+"</SinkID>";
                ret+="<SourceConnectorName>"+(new connection()).SourceConnectorName+"</SourceConnectorName>";
                ret+="<SinkConnectorName>"+(new connection()).SinkConnectorName+"</SinkConnectorName>";
                ret+="<SourceArrowSymbol>"+(new connection()).SourceArrowSymbol+"</SourceArrowSymbol>";
                ret+="<SinkArrowSymbol>"+(new connection()).SinkArrowSymbol+"</SinkArrowSymbol>";
                ret+="<zIndex>"+(new connection()).zIndex+"</zIndex>";
                ret+="<AddedJson>"+(new connection()).AddedJson+"</AddedJson>";
                ret+="</Connection>";
            }
        }
        return ret+"</Connections>";
    }
    designeritem FindOne(String numPCircuitID)
    {
        for(int i=0;i<designeritems.size();i++)
        {
               if (designeritems.get(i).numCircuitID.equals(numPCircuitID))
                   return designeritems.get(i);
        }
        return   null;
    }
    List<designeritem> FindAll(int r)
    {
        List<designeritem> ret=new ArrayList<>();
        for(int i=0;i<designeritems.size();i++)
        {
            if(designeritems.get(i).row==r)
                ret.add((designeritem)designeritems.get(i));
        }
        return  ret;
    }
    int maxrow = 0;
    void visittree(circuitnode node, int row, int left, int right)
    {
        maxrow = maxrow > row ? maxrow : row;
       // Console.WriteLine(node.varCircuitName + "开始" + node.numCircuitID);
        for (int i = 0; i < node.children.size(); i++)
        {
            if (!node.children.get(i).numCircuitID.toString().equals("0"))
            {
                int w = (right - left) / node.children.size();
                designeritem item = new designeritem();
                item.Left = left + i * w + (w - commonInfo.imagewidth) / 2;
                item.Top = commonInfo.marginTop*(row*2+1)+commonInfo.imageheigth*row;
                item.Width = commonInfo.imagewidth;
                item.Height = commonInfo.imageheigth;
                item.ID =java.util.UUID.randomUUID().toString();
                item.numCircuitID = node.children.get(i).numCircuitID;
                item.numPCircuitID = node.children.get(i).numPCircuitID;
                item.varID = node.children.get(i).varID;
                item.row = row;
                item.AddedJson= "{\"Column1\":\"\",\"Tag\":\"meter\",\"ID\":\""+node.children.get(i).numCircuitID+"\",\"Column3\":\"\",\"Column2\":\"\"}";
                htdesigneritems.put(item.ID,item);
                designeritems.add((designeritem)htdesigneritems.get(item.ID));
            }
          //  Console.Write(node.children[i].varCircuitName + "(" + row + ")");
        }
        if (node.children.size()>0)
            row++;
        for (int i = 0; i < node.children.size(); i++)
        {
            int w = (right - left) / node.children.size();
            visittree(node.children.get(i), row, left+w*i,left+w*(i+1));
        }
     //   Console.WriteLine(node.varCircuitName + "结束");
    }
    Hashtable htdesigneritems = new Hashtable();
    List<designeritem> designeritems = new ArrayList<>();
    List<connection> connections = new ArrayList<>();
    CommonInfo commonInfos = new CommonInfo();
    class designeritem
    {
        public int Left;
        public int Top;
        public int Width;
        public int Height;
        public String ID;
        public String zIndex = "5";
        public String IsGroup = "false";
        public String ParentID = "00000000-0000-0000-0000-000000000000";
        public String Content = "<Image Source=\"pack://SiteOfOrigin:,,,/Resources/Images/Nexus1500.png\" Name=\"WD_Images_15\" Tag=\"Tp.png\" ToolTip=\"10kV变0.4kV动力变压器。\" IsHitTestVisible=\"False\" xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\" />";
        public String AddedJson = "";
        public String numCircuitID;
        public String numPCircuitID;
        public String varID;
        public int row;

    }
    public String labelContent = "<Label BorderBrush=\"#FF000000\" BorderThickness=\"0,0,0,0\" Foreground=\"#FFFFFFFF\" FontSize=\"12\" HorizontalContentAlignment=\"Center\" VerticalContentAlignment=\"Center\" Tag=\"label\" ToolTip=\"label\" IsHitTestVisible=\"False\" xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"></Label>";

    class connection
    {
        public String SourceID = "";
        public String SinkID = "";
        public String SourceConnectorName = "Bottom";
        public String SinkConnectorName = "Top";
        public String SourceArrowSymbol = "None";
        public String SinkArrowSymbol = "None";
        public String zIndex = "4";
        public String AddedJson = "";
    }
    CommonInfo  commonInfo=new CommonInfo();
    class CommonInfo
    {

        public  int Height=900;
        public  int Width=1260;
        public  String Background = "#FF030303";
        public  int imageheigth = 40;
        public  int imagewidth = 40;
        public  int labelheigth = 30;
        public  int labelwidth = 60;
        public  int marginTop = 60;
    }


    class circuitnode
    {
        public circuitnode() { }
        public circuitnode(String numCircuitID, String numPCircuitID, String varCircuitName, String varID)
        {
            this.numCircuitID = numCircuitID;
            this.numPCircuitID = numPCircuitID;
            this.varCircuitName = varCircuitName;
            this.varID = varID;
            children = new ArrayList<circuitnode>();
        }

        public  String numCircuitID;
        public String numPCircuitID;
        public String varCircuitName;
        public String varID;

        public List<circuitnode> children;
    }
}
