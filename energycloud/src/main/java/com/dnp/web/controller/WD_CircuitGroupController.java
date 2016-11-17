package com.dnp.web.controller;


import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;

import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_CircuitGroupService;

import com.dnp.web.service.WD_UserService;
import org.json.JSONObject;

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
* @Auther Tao 
* @date:2016/9/28 15:39
 * 回路组信息管理
*/
@Controller
@RequestMapping("/WD_CircuitGroup")
public class WD_CircuitGroupController {
    @Autowired
    WD_CircuitGroupService wd_CircuitGroupService;
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
        int count=wd_CircuitGroupService.getAllCount(object);
        List<HashMap> datas=wd_CircuitGroupService.getAll(object);
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
                    wd_CircuitGroupService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_CircuitGroupService.update(object, editFun);
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
        List<HashMap> datas=wd_CircuitGroupService.listAll();
        return JsonUtil.toJson(datas);
    }

    @RequestMapping(value = "/readByName", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
   public String readByName(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object=Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_CircuitGroupService.getAllCountByName(object);
        List<HashMap> datas=wd_CircuitGroupService.getAllByName(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }

    /////////////添加回路//////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/addCGCircuit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> ret=wd_CircuitGroupService.addCGCircuitByCircuitGroupID(object);
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/updateCGCircuit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        //先删除关联表中的数据,再插入关联表中要添加的数据,并关联
       // getCircuitRefGroupBynumCircuitGroupI

            wd_CircuitGroupService.deleteBynumRefID(object);



        String[] ids=object.get("ids").toString().split(",");

        for(int i=0;i<ids.length;i++)
        {

            object.put("numCircuitID",ids[i]);
            wd_CircuitGroupService.insertBynumRefID(object);
        }
        return   null;
    }
    /////////////////添加数据用户组/////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/addDataUGRefCg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDataUGRefCg(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object=Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> ret=wd_CircuitGroupService.addDataUGRefCgByCircuitGroupID(object);
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/updateDataUGRefCg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateDataUGRefCg(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        //先删除关联表中的数据,再插入关联表中要添加的数据,并关联

            wd_CircuitGroupService.deleteDataUGRefCGByNumCircuitGroupID(object);



        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numDataUserGroupID",ids[i]);
            wd_CircuitGroupService.insertDataUGRefCGByNumCircuitGroupID(object);
        }
        return   null;
    }

}
