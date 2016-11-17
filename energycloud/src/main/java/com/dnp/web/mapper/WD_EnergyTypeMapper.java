package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_EnergyTypeMapper {
    @Select("SELECT e.numEnergyTypeId,e.varEnergyTypeName\n" +
            "FROM WD_EnergyType e\n" +
            "ORDER BY e.numEnergyTypeId desc ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_EnergyType")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_EnergyType(varEnergyTypeName) values(#{varEnergyTypeName})")
    @Options(useGeneratedKeys = true, keyProperty = "numEnergyTypeId", keyColumn = "numEnergyTypeId")
    int insert(HashMap<String, Object> data);

    @Update("update WD_EnergyType set varEnergyTypeName=#{varEnergyTypeName} where numEnergyTypeId=#{numEnergyTypeId}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_EnergyType where numEnergyTypeId=#{numEnergyTypeId}")
    int delete(HashMap<String, Object> data);




    @Select("SELECT  e.numEnergyTypeId,e.varEnergyTypeName\n" +
            "FROM WD_EnergyType e ORDER BY e.numEnergyTypeId ASC")
   List<HashMap> listAll();




}
