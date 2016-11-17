package com.dnp.web.service;

import com.dnp.web.mapper.WD_UserRefFuncUGMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_UserRefFuncUGService {
    @Autowired
    WD_UserRefFuncUGMapper wd_userRefFuncUGMapper;
    public int deleteBynumFunctionUserGroupID(HashMap<String,Object> data)
    {
        return  wd_userRefFuncUGMapper.deleteBynumFunctionUserGroupID(data);
    }
    public void insert(HashMap<String,Object> data)
    {
        wd_userRefFuncUGMapper.insert(data);
    }
    public int deleteBynumUserID(HashMap<String,Object> data)
    {
        return  wd_userRefFuncUGMapper.deleteBynumUserID(data);
    }
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_userRefFuncUGMapper.getAll(data);
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_userRefFuncUGMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_userRefFuncUGMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_userRefFuncUGMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_userRefFuncUGMapper.delete(data);
        }
        return 1;
    }

}
