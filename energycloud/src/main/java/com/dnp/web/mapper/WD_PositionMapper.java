package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_PositionMapper {
    @Select("SELECT   numPositionID,varPositionName from WD_Position  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT  count(*) from WD_Position")
    int getAllCount(HashMap<String, Object> data);


    @Insert("INSERT INTO WD_Position (varPositionName) VALUES(#{varPositionName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numPositionID", keyColumn = "numPositionID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_Position SET varPositionName=#{varPositionName}  where numPositionID = #{numPositionID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_Position where numPositionID = #{numPositionID}")
    int delete(HashMap<String, Object> data);

    @Select("select * from WD_Position ")
    List<HashMap> listAll();


}
