package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.GetDataService;
import com.dnp.web.service.WD_StatisticsCircuitService;
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
 * 能源预测
 * @Auther Tao
 * @date:2016/10/18 15:13
 */
@Controller
@RequestMapping("/statisticsCircuit")
public class WD_StatisticsCircuitController {
    @Autowired
    WD_StatisticsCircuitService wd_statisticsCircuitService;
    @Autowired
    WD_UserService wd_userService;
    @RequestMapping(value = "/getCircuitDatas", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {

        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object= Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_statisticsCircuitService.getAllCount(object);
        List<HashMap> datas=wd_statisticsCircuitService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("numUserID", SessionUtil.userId(request));
        String ids=wd_userService.getOrganizeIDBynumUserID(object);
        object.put("numOrganizeIDs",ids.equals("")?"null":ids);
        object.put("isadmin",SessionUtil.isadmin(request));
        List<HashMap> datas=wd_statisticsCircuitService.listAll(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getchartdata", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getchartdata(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("numUserID", SessionUtil.userId(request));
        String ids=wd_userService.getOrganizeIDBynumUserID(object);
        object.put("numOrganizeIDs",ids.equals("")?"null":ids);
        object.put("isadmin",SessionUtil.isadmin(request));
        List<HashMap> datas=wd_statisticsCircuitService.getchartdata(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/listByBeginningOfMonth", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listByBeginningOfMonth(HttpServletRequest request, String data) {

        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        List<HashMap> datas=wd_statisticsCircuitService.listByBeginningOfMonth(object);
        return JsonUtil.toJson(s.getDatas(datas,1));
    }
    @RequestMapping(value = "/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByDateRangeAndnumCircuitGroupIDslistAll(HttpServletRequest request, String data) {
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=wd_statisticsCircuitService.getAll(object);
        return JsonUtil.toJson(datas);
    }
}
