package com.dnp.web.service;

import com.dnp.web.mapper.WD_TableRefCircuitInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_TableRefCircuitInfosService {
    @Autowired
    WD_TableRefCircuitInfosMapper wd_tableRefCircuitInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_tableRefCircuitInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_tableRefCircuitInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_tableRefCircuitInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_tableRefCircuitInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_tableRefCircuitInfosMapper.delete(data);
        }
        return 1;
    }

}
