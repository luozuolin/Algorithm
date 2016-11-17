package com.dnp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_PositionService {
    @Autowired
    com.dnp.web.mapper.WD_PositionMapper WD_PositionMapper;



    public List<HashMap> getAll(HashMap<String, Object> data) {
        return WD_PositionMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return WD_PositionMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return WD_PositionMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return WD_PositionMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return WD_PositionMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return WD_PositionMapper.listAll();
    }
}
