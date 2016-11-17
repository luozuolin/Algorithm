package com.dnp.web.encosaas.controller;

import com.dnp.util.Def;
import com.dnp.util.JsonUtil;
import com.dnp.web.encosaas.service.FirstPageService;
import com.dnp.web.encosaas.service.InitXML;
import com.dnp.web.service.WD_UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/13.
 */
@Controller
@RequestMapping("/FirstPage")
public class FirstPageController {

    @Autowired
    FirstPageService firstPageService;
    @Autowired
    WD_UserService wd_userService;
  //  @Autowired
  //  InitXML initXML;
    @RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=firstPageService.getAll(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getMonth", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getMonth(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=firstPageService.getMonth(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getDay", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getDay(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object= Def.getIsAdminHash(object,request,wd_userService);
        List<HashMap> datas=firstPageService.getDay(object);
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/getStation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getStation(HttpServletRequest request, String data) {
        List<HashMap> datas=firstPageService.getStation(JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data)));
        return JsonUtil.toJson(datas);
    }
    @RequestMapping(value = "/svgpage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public ModelAndView  svgpage(HttpServletRequest request, String numCircuitGroupID,Model model) {
        ModelAndView modelAndView = new ModelAndView("/encosaas/svgpage");
        modelAndView.addObject("numCircuitGroupID",numCircuitGroupID);
        return modelAndView;
    }
    @RequestMapping(value = "/getXML", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getXML(HttpServletRequest request, String data) {
        List<HashMap> datas = firstPageService.getXML(JsonUtil.JsonObjectToHashMap(new JSONObject(data == null ? "{}" : data)));

        HashMap<String, Object> ret = new HashMap<>();
        if(datas.size()>0)
        {
            ret.put("xml", datas.get(0).get("varContent"));
        }
        else
        {
            InitXML initXML=new InitXML();
            String s=initXML.getXML(firstPageService.getCircuitsBynumCircuitGroupID(JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data))));
            ret.put("xml", s);
        }
        return JsonUtil.toJson(ret);
    }
}
