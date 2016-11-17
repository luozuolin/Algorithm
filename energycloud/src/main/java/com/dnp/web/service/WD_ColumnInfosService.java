package com.dnp.web.service;

import com.dnp.web.mapper.WD_ColumnInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_ColumnInfosService {
    @Autowired
    WD_ColumnInfosMapper wd_columnInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_columnInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_columnInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_columnInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_columnInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_columnInfosMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return wd_columnInfosMapper.listAll();
    }
  
}
