package com.dnp.util;

import com.dnp.inter.SessionUtil;
import com.dnp.web.service.WD_UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by wode on 7/8/16.
 */

/*
   function showalert1()
    {
        $.quake.comp.alert({type:"success",infor:"操作成功"});
        // $.quake.comp.confirm({html:"",ok:"",cancle:""});
    }
    function showalert2()
    {
        $.quake.comp.alert({type:"alert",infor:"操作失败"});
    }
    function showalert3()
    {
        $.quake.comp.alert({type:"info",infor:"提示信息"});
    }
    function showalert4()
    {
        $.quake.comp.alert({type:"warning",infor:"产生告警"});
    }



   class AlertType{
    public final   static String success="success";
}

* */


public class Def {
    public static final String DATE_FORMATER = "yyyy-MM-dd";
    public static final String DATE_FORMATERTime = "yyyy-MM-dd HH:mm:ss";
    public static final class AlertType{
        //success:操作成功,alert:操作失败,info:提示信息, warning:产生告警
        public static String success="success";
        public static String alert="alert";
        public static String info="info";
        public static String warning="warning";
    }

    public  static HashMap<String, Object>  getIsAdminHash(HashMap<String, Object> object,HttpServletRequest request,WD_UserService wd_userService)
    {
        object.put("numUserID", SessionUtil.userId(request));
        String ids=wd_userService.getOrganizeIDBynumUserID(object);
        object.put("numOrganizeIDs",ids.equals("")?"null":ids);
        object.put("isadmin",SessionUtil.isadmin(request));
        return object;
    }
}
