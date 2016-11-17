package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.GetDataService;
import com.dnp.web.service.WD_UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Controller
@RequestMapping("/GetData")
public class GetDataController {
    @Autowired
    GetDataService getDataService;
    @Autowired
    WD_UserService wd_userService;
    @RequestMapping(value = "/getCircuitDatasByDateRange", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object= Def.getIsAdminHash(object,request,wd_userService);
        int count=getDataService.getAllCount(object);
        List<HashMap> datas=getDataService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    @RequestMapping(value = "/getCircuitDatasByDateRange/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> datas=getDataService.listAll(object);//曲线图
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getCircuitDatasByDateRangeAndnumCircuitGroupIDs", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByDateRangeAndnumCircuitGroupIDs(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object= Def.getIsAdminHash(object,request,wd_userService);
        int count=getDataService.getAllCountByDateRangeAndnumCircuitGroupIDs(object);
        List<HashMap> datas=getDataService.getAllByDateRangeAndnumCircuitGroupIDs(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    @RequestMapping(value = "/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByDateRangeAndnumCircuitGroupIDslistAll(HttpServletRequest request, String data) {
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=getDataService.listAllByDateRangeAndnumCircuitGroupIDs(object);//曲线图
        return JsonUtil.toJson(datas);
    }

    //获取回路当天每五分钟的使用电量
    @RequestMapping(value = "/getCircuitDatasByPerFiveMinutesAndnumCircuit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByPerFiveMinutesAndnumCircuit(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=getDataService.getCircuitDatasAllCountByPerFiveMinutesAndnumCircuit(object);
        List<HashMap> datas=getDataService.getCircuitDatasAllByPerFiveMinutesAndnumCircuit(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    //获取回路当天每五分钟的使用电量
    @RequestMapping(value = "/getCircuitDatasByPerFiveMinutesAndnumCircuit/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByPerFiveMinutesAndnumCircuitlistAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> datas=getDataService.getCircuitDatasByPerFiveMinutesAndnumCircuitlistAll(object);
        return JsonUtil.toJson(datas);

    }
}
