package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/18.
 */
@Component
public interface WD_ConfigTypeMapper {
    @Select("SELECT  numConfigTypeID,  numConfigTypeID,varName from WD_ConfigType  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT   count(*) from WD_ConfigType  ")
    int getAllCount(HashMap<String, Object> data);

    @Insert("INSERT INTO WD_ConfigType (varName) VALUES(#{varName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numConfigTypeID", keyColumn = "numConfigTypeID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_ConfigType SET varName=#{varName}   where numConfigTypeID = #{numConfigTypeID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_ConfigType where numConfigTypeID = #{numConfigTypeID}")
    int delete(HashMap<String, Object> data);

    @Select("SELECT   * from   WD_ConfigType")
    List<HashMap> listAll();

}
