package com.dnp.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wode on 7/20/16.
 */
public class DataTableService {
    public List<Columns> columns=new ArrayList<>();
    public int start;
    public int length;
    public int draw;
    public Search search=new Search();
    public  List<Order> orders=new ArrayList<>();
    public DataTableService(HttpServletRequest request)
    {
        start=Integer.parseInt(request.getParameter("start"));
        length=Integer.parseInt(request.getParameter("length"));
        draw=Integer.parseInt(request.getParameter("draw"));
        search.regex=String.valueOf(request.getParameter("search[regex]"));
        search.value=String.valueOf(request.getParameter("search[value]"));
        for (int i=0;i<request.getParameterMap().size();i++)
        {
            if(request.getParameter("columns["+i+"][data]")!=null)
            {
                Columns c=new Columns();
                c.search=new Search();
                c.data=request.getParameter("columns["+i+"][data]");
                c.name=request.getParameter("columns["+i+"][name]");
                c.searchable=request.getParameter("columns["+i+"][searchable]");
                c.orderable=request.getParameter("columns["+i+"][orderable]");
                c.search.value=request.getParameter("columns["+i+"][search][value]");
                c.search.regex=request.getParameter("columns["+i+"][search][regex]");
                columns.add(c);
            }
            else
                continue;
        }
        for (int i=0;i<request.getParameterMap().size();i++)
        {
            if(request.getParameter("order["+i+"][column]")!=null)
            {
                Order o=new Order();
                o.column=columns.get(Integer.parseInt(request.getParameter("order["+i+"][column]"))).data;
                o.dir=request.getParameter("order["+i+"][dir]");
                orders.add(o);
            }
            else
                continue;
        }
    }
    public  String getorderandlimitsql()
    {
        String sql="";
        for(int  i=0;i<orders.size();i++)
        {
            sql+=(i==0?"Order by ":",")+orders.get(i).column+" "+orders.get(i).dir;
        }
        return sql+" Limit "+start +" ,"+length;
    }
    public Map<String, Object> getDatas(List<?> datas,int count)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("data", datas);
        map.put("draw", draw);
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        return map;
    }
    class Search
    {
        public String value;
        public  String regex;
    }
    class Order{
        public String dir;
        public  String column;
    }
    class Columns{
        public String searchable;
        public String orderable;
        public  String data;
        public String name;
        public  Search  search;
    }
}
