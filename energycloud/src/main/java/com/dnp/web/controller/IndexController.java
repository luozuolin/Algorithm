package com.dnp.web.controller;

import com.dnp.inter.SessionUtil;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.WD_UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController  {
    @Autowired
    WD_UserService wd_userService;
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String main(HttpServletRequest request, Model model) {
        return "/page/index" ;
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> user= wd_userService.getUserByLoginNameAndPassword(object);
        Map<String,Object> ret=new HashMap<>();
        ret.put("login",false);
        if(!user.isEmpty())
        {
            SessionUtil.login(request.getSession(),user.get(0).get("numUserID").toString(),user.get(0).get("varPassword").toString(),user.get(0).get("isadmin").toString());
            ret.put("login",true);
        }
        return JsonUtil.toJson(ret);
    }
    @RequestMapping(value = "/refreshstatus", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String refreshstatus(HttpServletRequest request, Model model) {
        Map<String,Object> ret=new HashMap<>();
        ret.put("userId",SessionUtil.userId(request));
        return  JsonUtil.toJson(ret);
    }

    @RequestMapping(value = "/rightlist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list(HttpServletRequest request) {
        HashMap<String,Object> object =new HashMap<>();
        object.put("numUserID",request.getSession().getAttribute("SESSION_USER_ID"));
        List<HashMap> functions= wd_userService.getFunctionByUserID(object);
        return JsonUtil.toJson(functions);
    }
    @RequestMapping(value = "/admin/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String logout(HttpServletRequest request, String data) {
        SessionUtil.logout(request.getSession());
        Map<String,Object> object=new HashMap<>();
        object.put("logout",true);
        return JsonUtil.toJson(object);
    }
    @RequestMapping(value = "/page/{pagename}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String pagename(HttpServletRequest request, @PathVariable String pagename) {
        return "/page/"+pagename ;
    }
    @RequestMapping(value = "/Purview/{pagename}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String Purview(HttpServletRequest request, @PathVariable String pagename) {
        if(SessionUtil.isadmin(request).equals("true") || !((pagename.equals("ColumnInfos") || pagename.equals("PositionInfos") || pagename.equals("OrganizeInfos"))))
        {
            return "/Purview/"+pagename ;
        }
        else
        {
            return "/Purview/noright";
        }
    }

    @RequestMapping(value = "/Setting/{pagename}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String Setting(HttpServletRequest request, @PathVariable String pagename) {
        if(SessionUtil.isadmin(request).equals("true") || !((pagename.equals("ColumnInfos") || pagename.equals("PositionInfos") || pagename.equals("OrganizeInfos"))))
        {
            return "/Setting/"+pagename ;
        }
        else
        {
            return "/Purview/noright";
        }

    }
    @RequestMapping(value = "/encosaas/{pagename}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String encosaas(HttpServletRequest request, @PathVariable String pagename) {
        return "/encosaas/"+pagename ;
    }

    @RequestMapping(value = "/module/demo/{pagename}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String module_demo(HttpServletRequest request, @PathVariable String pagename) {
        return "/module/demo/"+pagename ;
    }

}
