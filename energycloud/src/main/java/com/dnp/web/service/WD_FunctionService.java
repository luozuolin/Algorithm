package com.dnp.web.service;

import com.dnp.web.mapper.WD_FunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */

@Component
public class WD_FunctionService {
    @Autowired
    WD_FunctionMapper wd_functionMapper;

    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_functionMapper.getAll(data);
    }
    public List<HashMap> listAll() {
        return wd_functionMapper.listAll();
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_functionMapper.getAllCount(data);
    }


    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_functionMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_functionMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_functionMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> getFunctionUserGroupBynumFunctionID(HashMap<String, Object> data) {
        return wd_functionMapper.getFunctionUserGroupBynumFunctionID(data);
    }

}
