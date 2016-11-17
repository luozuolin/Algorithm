package com.dnp.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/8/16.
 */
@Component
public class sportService {
    @Autowired
    com.dnp.web.mapper.sportMapper sportMapper;
    public List<HashMap> getAll()
    {
        return  sportMapper.getAll();
    }

}
