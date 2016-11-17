package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_UserRefDataUGService;
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
 * Created by luozl on 2016/9/9.
 */
@Controller
@RequestMapping("/ WD_UserRefDataUG")
public class WD_UserRefDataUGController {
    @Autowired
    WD_UserRefDataUGService wd_userRefDataUGService;

    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s = new DataTableService(request);
        HashMap<String, Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
        object.put("sql", s.getorderandlimitsql());
        int count = wd_userRefDataUGService.getAllCount(object);
        List<HashMap> datas = wd_userRefDataUGService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas, count));
    }

    @RequestMapping(value = "/edit/{editFun}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit(HttpServletRequest request, @PathVariable String editFun, String data) {
        HashMap<String, Object> object = new HashMap<>();
        HashMap<String, Object> ret = new HashMap<>();
        try {
            if (editFun.equals("deleteSelected")) {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    object = JsonUtil.JsonObjectToHashMap((JSONObject) jsonArray.get(i));
                    wd_userRefDataUGService.update(object, "dele");
                }
            } else {
                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
                wd_userRefDataUGService.update(object, editFun);
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
}
