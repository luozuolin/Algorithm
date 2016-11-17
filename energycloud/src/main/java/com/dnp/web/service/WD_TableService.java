package com.dnp.web.service;

import com.dnp.web.mapper.WD_TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 9/21
 */
@Component
public class WD_TableService {
    @Autowired
    WD_TableMapper wd_tableMapper;



//
//    public List<HashMap> getAll(HashMap<String, Object> data) {
//        return wd_organizeMapper.getAll(data);
//    }
//
//    public int getAllCount(HashMap<String, Object> data) {
//        return wd_organizeMapper.getAllCount(data);
//    }
//
//    public int update(HashMap<String, Object> data, String updateMethod) {
//        if (updateMethod.equals("add")) {
//            return wd_organizeMapper.insert(data);
//        } else if (updateMethod.equals("update")) {
//            return wd_organizeMapper.update(data);
//        } else if (updateMethod.equals("dele")) {
//            return wd_organizeMapper.delete(data);
//        }
//        return 1;
//    }
    public List<HashMap> listAll()
    {
        return wd_tableMapper.listAll();
    }
//    public List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data) {
//        return wd_organizeMapper.getCircuitGroupBynumDataUserGroupID(data);
//    }
}
