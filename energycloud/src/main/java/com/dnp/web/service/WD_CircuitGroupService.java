package com.dnp.web.service;

import com.dnp.web.mapper.WD_CircuitGroupMapper;
import com.dnp.web.mapper.WD_UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_CircuitGroupService {
    @Autowired
    WD_CircuitGroupMapper wd_circuitGroupMapper;
    @Autowired
    WD_UserMapper wd_userMapper;

   public List<HashMap> getAllByName(HashMap<String, Object> data) {
       return wd_circuitGroupMapper.getAll(data);
   }

    public int getAllCountByName(HashMap<String, Object> data) {
        return wd_circuitGroupMapper.getAllCount(data);
    }


    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_circuitGroupMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
            return wd_circuitGroupMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_circuitGroupMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_circuitGroupMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            wd_circuitGroupMapper.deleteCircuitRefGroup(data);
            wd_circuitGroupMapper.deleteDataUGRefCG(data);
            return wd_circuitGroupMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return wd_circuitGroupMapper.listAll();
    }
    /////////////////////////////////////添加回路//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<HashMap> addCGCircuitByCircuitGroupID(HashMap<String, Object> data)
    {
        return wd_circuitGroupMapper.addCGCircuitByCircuitGroupID(data);
    }
    public int deleteBynumRefID(HashMap<String,Object> data)
    {
        return  wd_circuitGroupMapper.deleteBynumRefID(data);
    }
    public void insertBynumRefID(HashMap<String,Object> data)
    {
        wd_circuitGroupMapper.insertBynumRefID(data);
    }
    public int getCircuitRefGroupBynumCircuitGroupID(HashMap<String,Object> data)
    {
        return wd_circuitGroupMapper.getCircuitRefGroupBynumCircuitGroupID(data);
    }
    /////////////////////////////添加数据组/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<HashMap> addDataUGRefCgByCircuitGroupID(HashMap<String, Object> data)
    {

        return wd_circuitGroupMapper.addDataUGRefCgByCircuitGroupID(data);
    }
    public int deleteDataUGRefCGByNumCircuitGroupID(HashMap<String,Object> data)
    {
        return  wd_circuitGroupMapper.deleteDataUGRefCGByNumCircuitGroupID(data);
    }
    public void insertDataUGRefCGByNumCircuitGroupID(HashMap<String,Object> data)
    {
        wd_circuitGroupMapper.insertDataUGRefCGByNumCircuitGroupID(data);
    }
//    public List<HashMap> addDataUGRefCgByNumCircuitGroupID(HashMap<String,Object> data)
//    {
//        return wd_circuitGroupMapper.addCGCircuitByCircuitGroupID(data);
//
//    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

