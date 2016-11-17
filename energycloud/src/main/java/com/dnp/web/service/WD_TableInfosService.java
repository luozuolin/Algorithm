package com.dnp.web.service;

import com.dnp.web.mapper.WD_TableInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Component
public class WD_TableInfosService {
    @Autowired
    WD_TableInfosMapper wd_tableInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_tableInfosMapper.getAll(data);
    }
    public List<HashMap> addCircuitByNumTableID(HashMap<String, Object> data) {
        return wd_tableInfosMapper.addCircuitByNumTableID(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_tableInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_tableInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_tableInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_tableInfosMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll(HashMap<String, Object> data)
    {
        return wd_tableInfosMapper.listAll(data);
    }
    public int deleteBynumRefID(HashMap<String,Object> data)
    {
        return  wd_tableInfosMapper.deleteBynumRefID(data);
    }
    public int deleteByNumTableID(HashMap<String,Object> data)
    {
        return  wd_tableInfosMapper.deleteByNumTableID(data);
    }
    public void insertBynumRefID(HashMap<String,Object> data)
    {
        wd_tableInfosMapper.insertBynumRefID(data);
    }
}
