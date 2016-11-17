package com.dnp.web.service;

import com.dnp.web.mapper.WD_DataUGRefCGInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_DataUGRefCGInfosService {
    @Autowired
    WD_DataUGRefCGInfosMapper wd_dataUGRefCGInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_dataUGRefCGInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_dataUGRefCGInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_dataUGRefCGInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_dataUGRefCGInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_dataUGRefCGInfosMapper.delete(data);
        }
        return 1;
    }

}
