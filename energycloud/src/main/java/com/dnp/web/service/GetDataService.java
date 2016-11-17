package com.dnp.web.service;

import com.dnp.web.mapper.GetDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public class GetDataService {
    @Autowired
    GetDataMapper getDataMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return getDataMapper.getAll(data);
    }
    public List<HashMap> listAll(HashMap<String, Object> data) {


        String circuitID = (String) data.get("numCircuitGroupID");
        if (!"".equals(circuitID) && circuitID !=null){
            return getDataMapper.listAll(data);//曲线图
        }else {
            return null;
        }
    }

    public int getAllCount(HashMap<String, Object> data) {
        return getDataMapper.getAllCount(data);
    }



    public List<HashMap> getAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return getDataMapper.getAllByDateRangeAndnumCircuitGroupIDs(data);
    }
    public List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {


        String circuitID = (String) data.get("numCircuitID");
        if (!"".equals(circuitID) && circuitID !=null){
            return getDataMapper.listAllByDateRangeAndnumCircuitGroupIDs(data);//曲线图
        }else {
            return null;
        }
    }

    public int getAllCountByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return getDataMapper.getAllCountByDateRangeAndnumCircuitGroupIDs(data);
    }

    public List<HashMap> getCircuitDatasAllByPerFiveMinutesAndnumCircuit(HashMap<String, Object> data) {
        return getDataMapper.getCircuitDatasAllByPerFiveMinutesAndnumCircuit(data);
    }
    public int getCircuitDatasAllCountByPerFiveMinutesAndnumCircuit(HashMap<String, Object> data) {
        return getDataMapper.getCircuitDatasAllCountByPerFiveMinutesAndnumCircuit(data);
    }
    public List<HashMap> getCircuitDatasByPerFiveMinutesAndnumCircuitlistAll(HashMap<String, Object> data) {
        return getDataMapper.getCircuitDatasByPerFiveMinutesAndnumCircuitlistAll(data);
    }

}
