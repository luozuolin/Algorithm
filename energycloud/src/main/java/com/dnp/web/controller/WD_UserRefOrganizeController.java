package com.dnp.web.controller;

import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_UserRefOrganizeService;
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
 * Created by luozl on 2016/10/26.
 */
@Controller
@RequestMapping("/WD_UserRefOrganize")
public class WD_UserRefOrganizeController {
    @Autowired
    WD_UserRefOrganizeService wd_userRefOrganizeService;
    @RequestMapping(value = "/getFuncUG", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFuncUG(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> ret=wd_userRefOrganizeService.getOrganizeBynumUserID(object);
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/updateOrganize", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateOrganize(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        //首先删除WD_FuncUGRefFunc中 numFunctionUserGroupID的数据，接着插入data中的功能数据
        wd_userRefOrganizeService.deleteBynumUserID(object);
        String[] ids=object.get("ids").toString().split(",");
        for(int i=0;i<ids.length;i++)
        {
            object.put("numOrganizeID",ids[i]);
            wd_userRefOrganizeService.insert(object);
        }
        return   null;
    }

}
