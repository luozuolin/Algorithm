package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_AlarmLogService;
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
@RequestMapping("/wd_alarmLog")
public class WD_AlarmLogController {
    @Autowired
    WD_AlarmLogService wd_alarmLogService;
    @Autowired
    WD_UserService wd_userService;
    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object= Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_alarmLogService.getAllCount(object);
        List<HashMap> datas=wd_alarmLogService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }

    @RequestMapping(value = "/listAllByDateRangeAndnumCircuitGroupIDs", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));

        List<HashMap> datas=wd_alarmLogService.listAllByDateRangeAndnumCircuitGroupIDs(object);
        return JsonUtil.toJson(datas);
    }

    @RequestMapping(value = "/getCircuitDatasByDateRangeAndnumCircuitGroupIDs/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByDateRangeAndnumCircuitGroupIDslistAll(HttpServletRequest request, String data) {
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=wd_alarmLogService.getAll(object);
        return JsonUtil.toJson(datas);
    }
}
