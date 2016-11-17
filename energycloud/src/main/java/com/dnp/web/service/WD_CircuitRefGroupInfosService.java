package com.dnp.web.service;

import com.dnp.web.mapper.WD_CircuitRefGroupInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/27 14:43
*/
@Component
public class WD_CircuitRefGroupInfosService {
    @Autowired
    WD_CircuitRefGroupInfosMapper wd_circuitRefGroupInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_circuitRefGroupInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_circuitRefGroupInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_circuitRefGroupInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_circuitRefGroupInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_circuitRefGroupInfosMapper.delete(data);
        }
        return 1;
    }

}
