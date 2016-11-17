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
 * Created by wode on 7/8/16.
 */
@Controller
@RequestMapping("/sport")
public class sportController {
    @Autowired
    com.dnp.web.service.sportService sportService;
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getAll(HttpServletRequest request) {
        List<HashMap> datas=sportService.getAll();
        return JsonUtil.toJson(datas);
    }



}
