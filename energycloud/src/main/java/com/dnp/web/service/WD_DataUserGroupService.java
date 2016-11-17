package com.dnp.web.service;

import com.dnp.web.mapper.WD_DataUserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */
@Component
public class WD_DataUserGroupService {
    @Autowired
    WD_DataUserGroupMapper wd_dataUserGroupMapper;

    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_dataUserGroupMapper.getAll(data);
    }
    public List<HashMap> listAll() {
        return wd_dataUserGroupMapper.listAll();
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_dataUserGroupMapper.getAllCount(data);
    }


    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_dataUserGroupMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_dataUserGroupMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_dataUserGroupMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> getUserBynumDataUserGroupID(HashMap<String, Object> data) {
        return wd_dataUserGroupMapper.getUserBynumDataUserGroupID(data);
    }
    public List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data) {
        return wd_dataUserGroupMapper.getCircuitGroupBynumDataUserGroupID(data);
    }
}
