package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_EnergyTypeService;
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
 * Created by luozl on 2016/9/8.
 */
@Controller
@RequestMapping("/WD_EnergyType")
public class WD_EnergyTypeController {
    @Autowired
    WD_EnergyTypeService wd_EnergyTypeService;


    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=wd_EnergyTypeService.getAllCount(object);
        List<HashMap> datas=wd_EnergyTypeService.getAll(object);
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
                    wd_EnergyTypeService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_EnergyTypeService.update(object, editFun);
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
        List<HashMap> datas=wd_EnergyTypeService.listAll();
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/updateUserRefDataUG", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateDataUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_EnergyTypeService.deleteBynumDataUserGroupId(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numUserID",ids[i]);
            wd_EnergyTypeService.insert(object);
        }
        return   null;
    }

}
