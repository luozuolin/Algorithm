package com.dnp.web.service;

import com.dnp.web.mapper.WD_UserRefDataUGMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */
@Component
public class WD_UserRefDataUGService {
    @Autowired
    WD_UserRefDataUGMapper wd_userRefDataUGMapper;
    public int deleteBynumDataUserGroupId(HashMap<String,Object> data)
    {
        return  wd_userRefDataUGMapper.deleteBynumDataUserGroupId(data);
    }
    public void insert(HashMap<String,Object> data)
    {
        wd_userRefDataUGMapper.insert(data);
    }
    public int deleteBynumUserID(HashMap<String,Object> data)
    {
        return  wd_userRefDataUGMapper.deleteBynumUserID(data);
    }
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_userRefDataUGMapper.getAll(data);
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_userRefDataUGMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_userRefDataUGMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_userRefDataUGMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_userRefDataUGMapper.delete(data);
        }
        return 1;
    }
}
