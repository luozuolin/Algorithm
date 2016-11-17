package com.dnp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright 2016 DONOPO Ltd. All rights reserved.
 *
 * Remark   : Json解析工具类
 * <p/>
 * Author   : Tim Mars
 * Project  : Quake
 * Date     : 6/22/2016
 */
public class JsonUtil {
    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static  HashMap<String,Object> JsonObjectToHashMap(JSONObject jsonObject)
    {
        Iterator it=new JSONObject(jsonObject.toString()).keys();
        HashMap<String,Object> result =new HashMap<>();
        while (it.hasNext())
        {
            String key=(String) it.next();
            result.put(key,jsonObject.get(key));
        }
        return   result;
    }
    public static <T> T toObject(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        String json = "[" +
                "  {" +
                "    \"coin\": 100," +
                "    \"role\": [" +
                "      65," +
                "      71" +
                "    ]" +
                "  }," +
                "  {" +
                "    \"coin\": 100," +
                "    \"prop\": [" +
                "      {" +
                "        \"id\": 11," +
                "        \"count\": 1" +
                "      }" +
                "    ]" +
                "  }" +
                "]";
        List<HashMap<String, Object>> result = JsonUtil.toObject(json, new TypeReference<List<HashMap<String, Object>>>() {
        });
        assert result != null;
        for (HashMap<String, Object> hashMap : result) {
            if (hashMap.get("coin") != null) {
                System.out.println(hashMap.get("coin"));
            }
            if (hashMap.get("role") != null) {
                @SuppressWarnings("unchecked")
                List<Integer> roleList = (List<Integer>) hashMap.get("role");
                for (Integer role : roleList) {
                    System.out.println(role);
                }
            }
            if (hashMap.get("prop") != null) {
                @SuppressWarnings("unchecked")
                List<HashMap<String, Object>> propMapList = (List<HashMap<String, Object>>) hashMap.get("prop");
                for (HashMap<String, Object> propMap : propMapList) {
                    System.out.println(propMap.get("id"));
                    System.out.println(propMap.get("count"));
                }
            }
        }
        System.out.println(result);
    }
}
