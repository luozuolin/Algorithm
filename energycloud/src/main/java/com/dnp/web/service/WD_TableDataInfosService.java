package com.dnp.web.service;

import com.dnp.web.mapper.WD_TableDataInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Component
public class WD_TableDataInfosService {
    @Autowired
    WD_TableDataInfosMapper wd_tableDataInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_tableDataInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_tableDataInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_tableDataInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_tableDataInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_tableDataInfosMapper.delete(data);
        }
        return 1;
    }

    public List<HashMap> getrealdatafromdatabase(HashMap<String, Object> data) {
        return wd_tableDataInfosMapper.getrealdatafromdatabase(data);
    }

}
