package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/27 15:26
*/
@Component
public interface WD_DataUGRefCGInfosMapper {
    @Select("SELECT ducg.numRefID,dug.numDataUserGroupID,dug.varDataUserGroupName,cg.numCircuitGroupID,cg.varCircuitGroupName\n" +
            "            FROM WD_DataUGRefCG ducg\n" +
            "            LEFT JOIN WD_DataUserGroup dug ON ducg.numDataUserGroupID=dug.numDataUserGroupID\n" +
            "            LEFT JOIN WD_CircuitGroup cg ON ducg.numCircuitGroupID=cg.numCircuitGroupID\n" +
            "            ORDER BY ducg.numRefID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_DataUGRefCG")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_DataUGRefCG(numDataUserGroupID,numCircuitGroupID) values(#{numDataUserGroupID},#{numCircuitGroupID})")

    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_DataUGRefCG set numDataUserGroupID=#{numDataUserGroupID},numCircuitGroupID=#{numCircuitGroupID} where numRefID=#{numRefID}")
    int update(HashMap<String, Object> data);

    @Delete("delete from WD_DataUGRefCG where numRefID=#{numRefID}")
    int delete(HashMap<String, Object> data);





}
