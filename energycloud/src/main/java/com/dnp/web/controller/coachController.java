package com.dnp.web.controller;

import com.dnp.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/21 17:32
*/
@Controller
@RequestMapping("/coach")
public class coachController {
    @Autowired
    com.dnp.web.service.coachService coachService;
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAll(HttpServletRequest request) {
        List<HashMap> datas= coachService.getAll();
        return JsonUtil.toJson(datas);
    }
}
