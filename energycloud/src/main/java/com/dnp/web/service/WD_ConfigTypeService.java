package com.dnp.web.service;

import com.dnp.web.mapper.WD_ConfigTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/18.
 */
@Component
public class WD_ConfigTypeService {
    @Autowired
    WD_ConfigTypeMapper wd_configTypeMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_configTypeMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_configTypeMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_configTypeMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_configTypeMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_configTypeMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll() {
        return wd_configTypeMapper.listAll();
    }

}
