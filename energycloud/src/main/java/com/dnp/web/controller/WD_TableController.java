package com.dnp.web.controller;

import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/WD_Tale")
public class WD_TableController {
    @Autowired
    WD_TableService wd_tableService;


//    @RequestMapping(value = "/read", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String read(HttpServletRequest request, String data) {
//        DataTableService s=new DataTableService(request);
//        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
//        object.put("sql",s.getorderandlimitsql());
//        int count=WD_OrganizeService.getAllCount(object);
//        List<HashMap> datas=WD_OrganizeService.getAll(object);
//        return JsonUtil.toJson(s.getDatas(datas,count));
//    }
//    @RequestMapping(value = "/edit/{editFun}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String edit(HttpServletRequest request, @PathVariable("editFun") String editFun , String data) {
//        HashMap<String, Object> object = new HashMap<>();
//        HashMap<String, Object> ret = new HashMap<>();
//        try {
//            if (editFun.equals("deleteSelected")) {
//                JSONArray jsonArray = new JSONArray(data);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    object = JsonUtil.JsonObjectToHashMap((JSONObject) jsonArray.get(i));
//                    WD_OrganizeService.update(object, "dele");
//                }
//            } else {
//                object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
//                WD_OrganizeService.update(object, editFun);
//            }
//            ret.put("type", Def.AlertType.success);
//            ret.put("message", "操作成功");
//        } catch (Exception ex) {
//            ret.put("type", Def.AlertType.alert);
//            ret.put("message", "操作失败");
//        } finally {
//            return JsonUtil.toJson(ret);
//        }
//    }
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request) {
        List<HashMap> datas=wd_tableService.listAll();
        return JsonUtil.toJson(datas);
    }

}
