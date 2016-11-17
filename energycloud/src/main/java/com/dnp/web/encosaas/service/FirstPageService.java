package com.dnp.web.encosaas.service;

import com.dnp.web.encosaas.mapper.FirstPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/13.
 */
@Component
public class FirstPageService {
    @Autowired
    FirstPageMapper firstPageMapper;
    public List<HashMap> getAll(HashMap<String,Object> data)
    {
        return firstPageMapper.getAll(data);
    }
    public List<HashMap> getMonth(HashMap<String,Object> data)
    {
        return firstPageMapper.getMonth(data);
    }
    public List<HashMap> getDay(HashMap<String,Object> data)
    {
        return firstPageMapper.getDay(data);
    }
    public List<HashMap> getStation(HashMap<String,Object> data)
    {
        return firstPageMapper.getStation(data);
    }
    public List<HashMap> getXML(HashMap<String,Object> data)
    {
        return firstPageMapper.getXML(data);
    }
    public List<HashMap> getCircuitsBynumCircuitGroupID(HashMap<String,Object> data)
    {
        return firstPageMapper.getCircuitsBynumCircuitGroupID(data);
    }

}
