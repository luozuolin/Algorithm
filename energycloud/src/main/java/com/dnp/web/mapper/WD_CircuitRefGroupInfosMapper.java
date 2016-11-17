package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/27 14:43
*/
@Component
public interface WD_CircuitRefGroupInfosMapper {
    @Select("select crcg.numC2GID as numC2GID,c.numCircuitID,c.varCircuitName,cg.numCircuitGroupID,cg.varCircuitGroupName \n" +
            "            from WD_CircuitRefGroup as crcg \n" +
            "            left join WD_Circuit as c on crcg.numCircuitID=c.numCircuitID \n" +
            "            left join WD_CircuitGroup as cg on crcg.numCircuitGroupID=cg.numCircuitGroupID \n" +
            "            ORDER BY numC2GID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_CircuitRefGroup")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_CircuitRefGroup(numCircuitID,numCircuitGroupID) values(#{numCircuitID},#{numCircuitGroupID})")
    @Options(useGeneratedKeys = true, keyProperty = "numC2GID", keyColumn = "numC2GID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_CircuitRefGroup set numCircuitID=#{numCircuitID},numCircuitGroupID=#{numCircuitGroupID} where numC2GID=#{numC2GID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_CircuitRefGroup where numC2GID=#{numC2GID}")
    int delete(HashMap<String, Object> data);




  

}
