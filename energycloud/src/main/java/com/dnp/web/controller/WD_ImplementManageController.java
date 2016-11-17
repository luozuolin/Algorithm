package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_ImplementManageService;
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
import java.util.List;


@Controller
@RequestMapping("/wd_implementManage")
public class WD_ImplementManageController {
    @Autowired
    WD_ImplementManageService wd_implementManageService;
    @Autowired
    WD_UserService wd_userService;

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object=Def.getIsAdminHash(object,request,wd_userService);
        int count= wd_implementManageService.getAllCount(object);
        List<HashMap> datas=wd_implementManageService.getAll(object);
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
                    wd_implementManageService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_implementManageService.update(object, editFun);
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
    public String read(HttpServletRequest request) {
        HashMap<String, Object> object = new HashMap<String, Object>();
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=wd_implementManageService.listAll(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getImplementByID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCircuitDatasByDateRangeAndnumCircuitGroupIDslistAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> datas=wd_implementManageService.getImplementByID(object);
        return JsonUtil.toJson(datas);
    }
}
