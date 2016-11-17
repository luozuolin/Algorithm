package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.DateUtil;
import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.pojo.player;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/6/16.
 */
@Controller
@RequestMapping("/player")
public class playerController {
    @Autowired
    com.dnp.web.service.playerService playerService;

    @RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAll(HttpServletRequest request, String data) {
        DataTableService s = new DataTableService(request);
        HashMap<String, Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data));
        object.put("sql", s.getorderandlimitsql());
        int count = playerService.getAllCount(object);
        List<player> players = playerService.getAll(object);
        return JsonUtil.toJson(s.getDatas(players, count));
    }

    @RequestMapping(value = "/edit1/{editFun}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String edit1(HttpServletRequest request, @PathVariable String editFun, String data) {
        String a = data;
        HashMap<String, Object> ret = new HashMap<>();
        try {
            if (editFun.equals("deleteSelected")) {
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject o = (JSONObject) jsonArray.get(i);
                    Date bornDate;
                    try {
                        bornDate = DateUtil.strToDate(o.get("born").toString(), Def.DATE_FORMATER);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return null;
                    }
                    player player = new player(o.get("id").equals(null) ? -1 : Integer.parseInt(o.get("id").toString()), o.get("name").toString(), o.get("sex").equals(null) ? -1 : Integer.parseInt(o.get("sex").toString()), bornDate, o.get("sportid").equals(null) ? -1 : Integer.parseInt(o.get("sportid").toString()), o.get("coachid").equals(null) ? -1 : Integer.parseInt(o.get("coachid").toString()), "", o.get("orgid").equals(null) ? -1 : Integer.parseInt(o.get("orgid").toString()), 1, o.get("isimportant").equals(null) ? -1 : Integer.parseInt(o.get("isimportant").toString()), "", "", "");
                    playerService.delete(player);
                }
            } else {
                JSONObject o = new JSONObject(data);
                Date bornDate;
                try {
                    bornDate = DateUtil.strToDate(o.get("born").toString(), Def.DATE_FORMATER);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
                player player = new player(o.get("id").equals(null) ? -1 : Integer.parseInt(o.get("id").toString()), o.get("name").toString(), o.get("sex").equals(null) ? -1 : Integer.parseInt(o.get("sex").toString()), bornDate, o.get("sportid").equals(null) ? -1 : Integer.parseInt(o.get("sportid").toString()), o.get("coachid").equals(null) ? -1 : Integer.parseInt(o.get("coachid").toString()), "", o.get("orgid").equals(null) ? -1 : Integer.parseInt(o.get("orgid").toString()), 1, o.get("isimportant").equals(null) ? -1 : Integer.parseInt(o.get("isimportant").toString()), "", "", "");
                if (editFun.equals("add")) {
                    playerService.insert(player);
                } else if (editFun.equals("update")) {
                    playerService.update(player);
                } else if (editFun.equals("dele")) {
                    playerService.delete(player);
                }
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
