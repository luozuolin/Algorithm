package com.dnp.web.service;

import com.dnp.web.mapper.WD_DataUGRefCGMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by luozl on 2016/9/12.
 */
@Component
public class WD_DataUGRefCGService {
    @Autowired
    WD_DataUGRefCGMapper wd_dataUGRefCGMapper;

    public void insert(HashMap<String,Object> data)
    {
        wd_dataUGRefCGMapper.insert(data);
    }
    public void deleteBynumDataUserGroupId(HashMap<String,Object> data)
    {
        wd_dataUGRefCGMapper.deleteBynumDataUserGroupId(data);
    }
}
