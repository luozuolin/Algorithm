package com.dnp.web.service;

import com.dnp.web.mapper.WD_FuncUGRefFuncMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_FuncUGRefFuncService {
    @Autowired
    WD_FuncUGRefFuncMapper wd_funcUGRefFuncMapper;
    public int deleteBynumFunctionUserGroupID(HashMap<String,Object> data)
    {
        return  wd_funcUGRefFuncMapper.deleteBynumFunctionUserGroupID(data);
    }
    public void insert(HashMap<String,Object> data)
    {
        wd_funcUGRefFuncMapper.insert(data);
    }
    public void deleteBynnumFunctionID(HashMap<String,Object> data)
    {
        wd_funcUGRefFuncMapper.deleteBynnumFunctionID(data);
    }
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_funcUGRefFuncMapper.getAll(data);
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_funcUGRefFuncMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_funcUGRefFuncMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_funcUGRefFuncMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_funcUGRefFuncMapper.delete(data);
        }
        return 1;
    }
}
