package com.dnp.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by luozl on 2016/8/30.
 */
public class SessionUtil {

    public static final String SESSION_LOGIN_VALID = "SESSION_LOGIN_VALID";
    public static final String SESSION_USER_ID = "SESSION_USER_ID";
    public static final String SESSION_ISADMIN = "SESSION_ISADMIN";//0，非管理员 1 管理员
    private static final String SESSION_PASSWORD = "SESSION_PASSWORD";

    public static Boolean isLogin(HttpSession session) {
        Object object = session.getAttribute(SESSION_LOGIN_VALID);
        return object != null && (boolean) object;
    }
    public static void login(HttpSession session,  String userId,String password,String isadmin) {
        session.setAttribute(SESSION_USER_ID, userId);
        session.setAttribute(SESSION_PASSWORD, password);
        session.setAttribute(SESSION_ISADMIN, isadmin);
        session.setAttribute(SESSION_LOGIN_VALID, true);
    }

    public static void logout(HttpSession session) {
        destroy(session);
    }
    public static void destroy(HttpSession session) {
        session.invalidate();
    }
    public static void dump(HttpSession session) {
        System.out.println(session.getAttribute(SESSION_LOGIN_VALID));
        System.out.println(session.getAttribute(SESSION_USER_ID));
    }
    public static String password(HttpSession session) {
        return (String) session.getAttribute(SESSION_PASSWORD);
    }
    public static int userId(HttpSession session) {
        return (int) session.getAttribute(SESSION_USER_ID);
    }
    public static String userId(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(SESSION_USER_ID);
    }
    public static String isadmin(HttpServletRequest request) {

        return request.getSession().getAttribute(SESSION_ISADMIN).toString();
    }
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

}
