package com.dnp.web.service;

import com.dnp.web.mapper.WD_ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/18.
 */
@Component
public class WD_ConfigService {
    @Autowired
    WD_ConfigMapper wd_configMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_configMapper.getAll(data);
    }
//
    public int getAllCount(HashMap<String, Object> data) {
        return wd_configMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {

            return wd_configMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_configMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_configMapper.delete(data);
        }
        return 1;
    }
}
