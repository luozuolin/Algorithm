package com.dnp.web.service;

import com.dnp.web.mapper.WD_OrganizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_OrganizeService {
    @Autowired
    WD_OrganizeMapper wd_organizeMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_organizeMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_organizeMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_organizeMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_organizeMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_organizeMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll(HashMap<String, Object> data)
    {
        return wd_organizeMapper.listAll(data);
    }
    public List<HashMap> OrganizelistAll(HashMap<String, Object> data)
    {
        return wd_organizeMapper.OrganizelistAll(data);
    }
    public List<HashMap> getBynumUserID(HashMap<String,Object> data)
    {
        return wd_organizeMapper.getBynumUserID(data);
    }



    public List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data) {
        return wd_organizeMapper.getCircuitGroupBynumDataUserGroupID(data);
    }
}
