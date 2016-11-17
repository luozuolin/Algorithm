package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.mapper.WD_UserMapper;
import com.dnp.web.service.WD_CircuitInfosService;
import com.dnp.web.service.WD_UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Controller
@RequestMapping("/wd_circuitInfos")
public class WD_CircuitInfosController {
    @Autowired
    WD_CircuitInfosService wd_circuitInfosService;
    @Autowired
    WD_UserService wd_userService;

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        data= data.replace(" ", "");//去除空格
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object=Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_circuitInfosService.getAllCount(object);
        List<HashMap> datas=wd_circuitInfosService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }

    @RequestMapping(value = "/readBynumCircuitGroupID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String readBynumCircuitGroupID(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=wd_circuitInfosService.getAllCountBynumCircuitGroupID(object);
        List<HashMap> datas=wd_circuitInfosService.getAllBynumCircuitGroupID(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    @RequestMapping(value = "/readBynumOrganizeID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String readBynumOrganizeID(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=wd_circuitInfosService.getAllCountBynumOrganizeID(object);
        List<HashMap> datas=wd_circuitInfosService.getAllBynumOrganizeID(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }

    @RequestMapping(value = "/edit/{editFun}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(HttpServletRequest request, @PathVariable String editFun , String data) {
        HashMap<String, Object> object = new HashMap<>();
        HashMap<String, Object> ret = new HashMap<>();
        try {
            if (editFun.equals("deleteSelected")) {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    object = JsonUtil.JsonObjectToHashMap((JSONObject) jsonArray.get(i));
                    wd_circuitInfosService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_circuitInfosService.update(object, editFun);
            }
            ret.put("type", Def.AlertType.success);
            ret.put("message", "操作成功");
        } catch (Exception ex) {
            ret.put("type", Def.AlertType.alert);
            ret.put("message", "操作失败");
        } finally {
            return JsonUtil.toJson(ret);
        }
    }
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request,String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=wd_circuitInfosService.listAll(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getCircuitNameByLike", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitNameByLike(HttpServletRequest request) {
        List<HashMap> datas=wd_circuitInfosService.getCircuitNameByLike();
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getCircuitsBynumUserID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitsBynumUserID(HttpServletRequest request) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap((new JSONObject("{}")));
        object=Def.getIsAdminHash(object,request,wd_userService);
        return JsonUtil.toJson(wd_circuitInfosService.getCircuitsBynumOrganizeIDs(object));
    }

}
