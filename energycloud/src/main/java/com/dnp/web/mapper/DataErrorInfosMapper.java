package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface DataErrorInfosMapper {
    @Select("SELECT d.numErrorID,d.varErrorType,d.varErrorDetail,d.datErrorDate,d.numColID,c.varCName\n" +
            "FROM\n" +
            "WD_DataError AS d ,\n" +
            "WD_Column AS c\n" +
            "WHERE d.numColID=c.numColID\n" +
            "ORDER BY d.numErrorID ASC\n ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_DataError")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_DataError(varErrorType,varErrorDetail,datErrorDate,numColID) values(#{varErrorType},#{varErrorDetail},#{datErrorDate},#{numColID})")
    @Options(useGeneratedKeys = true, keyProperty = "numErrorID", keyColumn = "numErrorID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_DataError set varErrorType=#{varErrorType},varErrorDetail=#{varErrorDetail},datErrorDate=#{datErrorDate},numColID=#{numColID}" +
            " where numErrorID=#{numErrorID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_DataError where numErrorID=#{numErrorID}")
    int delete(HashMap<String, Object> data);

}
