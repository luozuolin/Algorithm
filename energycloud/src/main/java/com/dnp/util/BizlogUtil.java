package com.dnp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 *
 * Remark   : 业务日志记录工具类
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
public class BizlogUtil {

    private static final Logger logger = LoggerFactory.getLogger("bizlog");

    @SuppressWarnings("unchecked")
    public static void log(String type, Map<String, Object> map) {
        Map result = new HashMap<String, Object>();
        result.put("type", type);
        result.put("value", map);
        logger.info("{}",JsonUtil.toJson(result));
    }

}
