package com.dnp.web.service;

import com.dnp.web.mapper.WD_UserRefOrganizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/26.
 */
@Component
public class WD_UserRefOrganizeService {
    @Autowired
    WD_UserRefOrganizeMapper wd_userRefOrganizeMapper;
    public List<HashMap> getOrganizeBynumUserID(HashMap<String, Object> data)
    {
        return wd_userRefOrganizeMapper.getOrganizeBynumUserID(data);
    }
    public void insert(HashMap<String,Object> data)
    {
        wd_userRefOrganizeMapper.insert(data);
    }
    public int deleteBynumUserID(HashMap<String,Object> data)
    {
        return  wd_userRefOrganizeMapper.deleteBynumUserID(data);
    }
}
