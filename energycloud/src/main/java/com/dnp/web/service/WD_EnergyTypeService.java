package com.dnp.web.service;

import com.dnp.web.mapper.WD_EnergyTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_EnergyTypeService {
    @Autowired
    WD_EnergyTypeMapper wd_EnergyTypeMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_EnergyTypeMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_EnergyTypeMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_EnergyTypeMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_EnergyTypeMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_EnergyTypeMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return wd_EnergyTypeMapper.listAll();
    }

    public int deleteBynumDataUserGroupId(HashMap<String,Object> data)
    {
        return  wd_EnergyTypeMapper.delete(data);
    }
    public void insert(HashMap<String,Object> data)
    {
        wd_EnergyTypeMapper.insert(data);
    }

}
