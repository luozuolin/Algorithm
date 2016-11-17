package com.dnp.inter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 *
 * Remark   : Web 前置管理器,一般用來檢查登錄狀況
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
@Component
public class AllInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(AllInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        try {
            HttpSession session = request.getSession();

            if(SessionUtil.isLogin(session)) {
                return true;
            }
            else  if ((request.getRequestURI().equals("/page/index") || request.getRequestURI().equals("/") ||
                    request.getRequestURI().equals("/login")
            )) {
                return true;
            }
            else
            {
                response.sendRedirect("/");
                return   true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.append("no permissions");
        printWriter.flush();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception e) throws Exception {
    }
}