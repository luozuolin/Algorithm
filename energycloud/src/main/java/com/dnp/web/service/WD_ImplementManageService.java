package com.dnp.web.service;

import com.dnp.web.mapper.WD_ImplementManageMapper;
import com.dnp.web.mapper.WD_UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Component
public class WD_ImplementManageService {
    @Autowired
    WD_ImplementManageMapper wd_implementManageMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {

             return wd_implementManageMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {

        return wd_implementManageMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_implementManageMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_implementManageMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_implementManageMapper.delete(data);
        }
        return 1;
    }
    public String getuploadUrl(int numImplementID) {
        return wd_implementManageMapper.getuploadUrl(numImplementID);
    }

    public int updateUrl(String varplan,int numImplementID) {
        return wd_implementManageMapper.updateUrl(varplan,numImplementID);
    }
    public List<HashMap> listAll(HashMap<String, Object> data) {

        return wd_implementManageMapper.listAll(data);
    }
    public List<HashMap> getImplementByID(HashMap<String, Object> data) {
        return wd_implementManageMapper.getImplementByID(data);
    }
}
