package com.dnp.web.service;

import com.dnp.inter.SessionUtil;
import com.dnp.web.mapper.WD_CircuitInfosMapper;
import com.dnp.web.mapper.WD_UserMapper;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/23 11:42
*/
@Component
public class WD_CircuitInfosService {
    @Autowired
    WD_CircuitInfosMapper wd_circuitInfosMapper;
    @Autowired
    WD_UserMapper wd_userMapper;





    public List<HashMap> getAll(HashMap<String, Object> data) {

        return wd_circuitInfosMapper.getAll(data);
    }



    public int getAllCount(HashMap<String, Object> data) {


            return wd_circuitInfosMapper.getAllCount(data);
    }
    public List<HashMap> getAllBynumCircuitGroupID(HashMap<String, Object> data) {
        return wd_circuitInfosMapper.getAllBynumCircuitGroupID(data);
    }

    public int getAllCountBynumCircuitGroupID(HashMap<String, Object> data) {
        return wd_circuitInfosMapper.getAllCountBynumCircuitGroupID(data);
    }
    public List<HashMap> getAllBynumOrganizeID(HashMap<String, Object> data) {
        return wd_circuitInfosMapper.getAllBynumOrganizeID(data);
    }

    public int getAllCountBynumOrganizeID(HashMap<String, Object> data) {
        return wd_circuitInfosMapper.getAllCountBynumOrganizeID(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_circuitInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_circuitInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            wd_circuitInfosMapper.deleteCircuitParents(data);
            wd_circuitInfosMapper.deleteCircuitRefGroup(data);
            wd_circuitInfosMapper.deleteStatisticsCircuit(data);
            wd_circuitInfosMapper.deleteTableRefCircuit(data);
            wd_circuitInfosMapper.deleteTransRefCircuit(data);
            return wd_circuitInfosMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll(HashMap<String, Object> data)
    {
        return wd_circuitInfosMapper.listAll(data);
    }
    public List<HashMap> getCircuitNameByLike()
    {
        return wd_circuitInfosMapper.getCircuitNameByLike();
    }
    public List<HashMap> listOriginal()
    {
        return wd_circuitInfosMapper.listOriginal();
    }
    public List<HashMap> getCircuitsBynumOrganizeIDs(HashMap<String, Object> data)
    {
        return wd_circuitInfosMapper.getCircuitsBynumOrganizeIDs(data);
    }

}
