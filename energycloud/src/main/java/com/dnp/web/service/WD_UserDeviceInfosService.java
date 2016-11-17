package com.dnp.web.service;

import com.dnp.web.mapper.WD_UserDeviceInfosMapper;
import com.dnp.web.mapper.WD_UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/10/28 15:01
*/
@Component
public class WD_UserDeviceInfosService {
    @Autowired
    WD_UserDeviceInfosMapper wd_userDeviceInfosMapper;
    @Autowired
    WD_UserMapper wd_userMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
//        data.put("OrganizeID",wd_userMapper.getUserOrganizeID(data));//查询出来的组织ID  numOrganizeID
        return wd_userDeviceInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
//        data.put("OrganizeID",wd_userMapper.getUserOrganizeID(data));
        return wd_userDeviceInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_userDeviceInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_userDeviceInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_userDeviceInfosMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return wd_userDeviceInfosMapper.listAll();
    }

}
