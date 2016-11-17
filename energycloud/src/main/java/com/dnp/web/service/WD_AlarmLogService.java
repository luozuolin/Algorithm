package com.dnp.web.service;

import com.dnp.web.mapper.GetDataMapper;
import com.dnp.web.mapper.WD_AlarmLogMapper;
import com.dnp.web.mapper.WD_CircuitGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public class WD_AlarmLogService {
    @Autowired
    WD_AlarmLogMapper wd_alarmLogMapper;





    public List<HashMap> getAll(HashMap<String, Object> data) {

        return wd_alarmLogMapper.getAll(data);
    }
    public List<HashMap> readAll() {

        return wd_alarmLogMapper.readAll();
    }
    public int getAllCount(HashMap<String, Object> data) {

        return wd_alarmLogMapper.getAllCount(data);
    }
    public List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return wd_alarmLogMapper.listAllByDateRangeAndnumCircuitGroupIDs(data);

    }


}
