package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_UserRefDataUGService;
import com.dnp.web.service.WD_UserRefFuncUGService;
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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Controller
@RequestMapping("/WD_User")
public class WD_UserController {
    @Autowired
    WD_UserService wd_userService;
    @Autowired
    WD_UserRefFuncUGService wd_userRefFuncUGService;
    @Autowired
    WD_UserRefDataUGService wd_userRefDataUGService;
    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data,HttpSession session) {
        DataTableService s=new DataTableService(request);
        data= data.replace(" ", "");//去除空格

        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        object=Def.getIsAdminHash(object,request,wd_userService);
        int count=wd_userService.getAllCount(object);
        List<HashMap> datas=wd_userService.getAll(object);
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
                    wd_userService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_userService.update(object, editFun);
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


    @RequestMapping(value = "/getFuncUG", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> ret=wd_userService.getFuncUGBynumUserID(object);
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/getDataUG", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDataUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> ret=wd_userService.getDataUGBynumUserID(object);
        return JsonUtil.toJson(ret);
    }
    //给功能用户组添加功能

    @RequestMapping(value = "/updateFuncUG", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_userRefFuncUGService.deleteBynumUserID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numFunctionUserGroupID",ids[i]);
            wd_userRefFuncUGService.insert(object);
        }
        return   null;
    }

    @RequestMapping(value = "/updateDataUG", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateDataUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_userRefDataUGService.deleteBynumUserID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numDataUserGroupID",ids[i]);
            wd_userRefDataUGService.insert(object);
        }
        return   null;
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request) {
        List<HashMap> datas=wd_userService.listAll();
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/updateOrganize", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateOrganize(HttpServletRequest request,String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        wd_userService.updateOrganize(object);
        return null;
    }

}