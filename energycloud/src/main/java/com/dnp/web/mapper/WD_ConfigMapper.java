package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/18.
 */
@Component
public interface WD_ConfigMapper {
    @Select("SELECT c.numConfigID,c.numCircuitGroupID,g.varCircuitGroupName,c.varContent,c.numConfigTypeID,t.varName  from WD_Config c LEFT  join WD_CircuitGroup g on c.numCircuitGroupID=g.numCircuitGroupID left join WD_ConfigType t on c.numConfigTypeID=t.numConfigTypeID ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT   count(*)  from WD_Config c LEFT  join WD_CircuitGroup g on c.numCircuitGroupID=g.numCircuitGroupID left join WD_ConfigType t on c.numConfigTypeID=t.numConfigTypeID;  ")
    int getAllCount(HashMap<String, Object> data);


    //sfsf
    @Insert("INSERT INTO WD_Config (numCircuitGroupID,numConfigTypeID) VALUES(#{numCircuitGroupID},#{numConfigTypeID}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numConfigID", keyColumn = "numConfigID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_Config SET numCircuitGroupID=#{numCircuitGroupID},numConfigTypeID=#{numConfigTypeID}  where numConfigID = #{numConfigID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_Config where numConfigID = #{numConfigID}")
    int delete(HashMap<String, Object> data);
}
