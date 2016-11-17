package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_TableInfosService;
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

/**
 *
 */
@Controller
@RequestMapping("/WD_TableInfos")
public class WD_TableInfosController {
    @Autowired
    WD_TableInfosService wd_tableInfosService;
    @Autowired
    WD_UserService wd_userService;

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object=Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_tableInfosService.getAllCount(object);
        List<HashMap> datas=wd_tableInfosService.getAll(object);
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
                    wd_tableInfosService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_tableInfosService.update(object, editFun);
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
    public String listAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=wd_tableInfosService.listAll(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/updateCGCircuit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        //先删除关联表中的数据,再插入关联表中要添加的数据,并关联
        // getCircuitRefGroupBynumCircuitGroupI
        wd_tableInfosService.deleteBynumRefID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numTableID",ids[i]);
            wd_tableInfosService.insertBynumRefID(object);
        }
        return   null;
    }
    @RequestMapping(value = "/updateByNumTableID", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateByNumTableID(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        //先删除关联表中的数据,再插入关联表中要添加的数据,并关联
        // getCircuitRefGroupBynumCircuitGroupI
        wd_tableInfosService.deleteByNumTableID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numCircuitID",ids[i]);
            wd_tableInfosService.insertBynumRefID(object);
        }
        return   null;
    }
    @RequestMapping(value = "/addCircuitByNumTableID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addCircuitByNumTableID(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> ret=wd_tableInfosService.addCircuitByNumTableID(object);
        return JsonUtil.toJson(ret);
    }
}
