package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_ColumnInfosMapper {
    @Select("SELECT c.numColID,c.varEName,c.varCName,c.numForCircuit,c.numForGroup,\n" +
            "            c.numColType,e.numEnergyTypeId,e.varEnergyTypeName\n" +
            "               FROM WD_Column c\n" +
            "            LEFT JOIN WD_EnergyType e ON c.numEnergyTypeID=e.numEnergyTypeId ORDER BY c.numColID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_Column")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_Column(varEName,varCName,numForCircuit,numForGroup,numColType,numEnergyTypeID) values(#{varEName},#{varCName},#{numForCircuit},#{numForGroup},#{numColType},#{numEnergyTypeId})")
    @Options(useGeneratedKeys = true, keyProperty = "numColID", keyColumn = "numColID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_Column set varEName=#{varEName},varCName=#{varCName},numForCircuit=#{numForCircuit},numForGroup=#{numForGroup},numColType=#{numColType},numEnergyTypeID=#{numEnergyTypeId} where numColID=#{numColID}")
    int update(HashMap<String, Object> data);

    @Delete("delete from WD_Column where numColID=#{numColID}")
    int delete(HashMap<String, Object> data);

    @Select("select * from WD_Column")
    List<HashMap> listAll();

}
