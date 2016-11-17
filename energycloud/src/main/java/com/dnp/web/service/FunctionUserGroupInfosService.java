package com.dnp.web.service;

import com.dnp.web.mapper.FunctionUserGroupInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/6.
 */
@Component
public class FunctionUserGroupInfosService {

    @Autowired
    FunctionUserGroupInfosMapper functionUserGroupInfosMapper;

    public List<HashMap> getAll(HashMap<String, Object> data) {
        return functionUserGroupInfosMapper.getAll(data);
    }
    public List<HashMap> listAll() {
        return functionUserGroupInfosMapper.listAll();
    }

    public int getAllCount(HashMap<String, Object> data) {
        return functionUserGroupInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return functionUserGroupInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return functionUserGroupInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return functionUserGroupInfosMapper.delete(data);
        }
        return 1;
    }

    public List<HashMap> getFunctionBynumFunctionUserGroupID(HashMap<String, Object> data)
    {
        return functionUserGroupInfosMapper.getFunctionBynumFunctionUserGroupID(data);
    }
    public List<HashMap> getUsersBynumFunctionUserGroupID(HashMap<String, Object> data)
    {
        return functionUserGroupInfosMapper.getUsersBynumFunctionUserGroupID(data);
    }
}
