package com.dnp.web.service;

import com.dnp.web.mapper.DataErrorInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class DataErrorInfosService {
    @Autowired
    DataErrorInfosMapper dataErrorInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return dataErrorInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return dataErrorInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return dataErrorInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return dataErrorInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return dataErrorInfosMapper.delete(data);
        }
        return 1;
    }


}
