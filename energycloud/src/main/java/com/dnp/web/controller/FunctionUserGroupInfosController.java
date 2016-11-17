package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.FunctionUserGroupInfosService;
import com.dnp.web.service.WD_FuncUGRefFuncService;
import com.dnp.web.service.WD_UserRefFuncUGService;
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
 * Created by luozl on 2016/9/6.
 */
@Controller
@RequestMapping("/FunctionUserGroupInfos")
public class FunctionUserGroupInfosController {
    @Autowired
    FunctionUserGroupInfosService functionUserGroupInfosService;
    @Autowired
    WD_FuncUGRefFuncService wd_funcUGRefFuncService;
    @Autowired
    WD_UserRefFuncUGService wd_userRefFuncUGService;
    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
       DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=functionUserGroupInfosService.getAllCount(object);
        List<HashMap> datas=functionUserGroupInfosService.getAll(object);
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
                    functionUserGroupInfosService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                functionUserGroupInfosService.update(object, editFun);
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
    @RequestMapping(value = "/getFunctionBynumFunctionUserGroupID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFunctionBynumFunctionUserGroupID(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> ret=functionUserGroupInfosService.getFunctionBynumFunctionUserGroupID(object);
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/getUsersBynumFunctionUserGroupID", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUsersBynumFunctionUserGroupID(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> ret=functionUserGroupInfosService.getUsersBynumFunctionUserGroupID(object);
        return JsonUtil.toJson(ret);
    }
    //给功能用户组添加功能

    @RequestMapping(value = "/setFunc", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setFunc(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_funcUGRefFuncService.deleteBynumFunctionUserGroupID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numFunctionID",ids[i]);
            wd_funcUGRefFuncService.insert(object);
        }
      return   null;
    }

    @RequestMapping(value = "/setUsers", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String setUsers(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_userRefFuncUGService.deleteBynumFunctionUserGroupID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numUserID",ids[i]);
            wd_userRefFuncUGService.insert(object);
        }
        return   null;
    }


    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request, String data) {
        List<HashMap> datas=functionUserGroupInfosService.listAll();
        return JsonUtil.toJson(datas);
    }

}
