package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_TableRefCircuitInfosMapper {
    @Select("select trc.numT2CID,trc.datStart,trc.datEnd,t.numTableID,t.varIP,c.numCircuitID,c.varCircuitName\n" +
            "from WD_TableRefCircuit as trc\n" +
            "left join WD_Table as t on trc.NumTableID=t.NumTableID\n" +
            "LEFT join WD_Circuit as c on trc.numCircuitID=c.numCircuitID\n" +
            "ORDER BY numT2CID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_TableRefCircuit")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_TableRefCircuit(datStart,datEnd,NumTableID,numCircuitID) values(#{datStart},#{datEnd},#{numTableID},#{numCircuitID})")
    @Options(useGeneratedKeys = true, keyProperty = "numOrganizeID", keyColumn = "numOrganizeID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_TableRefCircuit set datStart=#{datStart},datEnd=#{datEnd},NumTableID=#{numTableID},numCircuitID=#{numCircuitID} where numT2CID=#{numT2CID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_TableRefCircuit where numT2CID=#{numT2CID}")
    int delete(HashMap<String, Object> data);


}
