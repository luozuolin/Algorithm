package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_UserDeviceInfosMapper {

    @Select({"<script>\n" +
            "select ud.numDeviceID as numDeviceID,ud.varDeviceName as varDeviceName,\n" +
            "ci.numCircuitID as numInCircuitID,ci.varCircuitName as varInCircuitName,\n" +
            "co.numCircuitID as numOutCircuitID,co.varCircuitName as varOutCircuitName\n" +
            "from WD_UserDevice as ud\n" +
            "left join WD_Circuit as ci on ud.numInCircuitID=ci.numCircuitID\n" +
            "left join WD_Circuit as co on ud.numOutCircuitID=co.numCircuitID\n" +
            "<if test='varDeviceName!= null and varDeviceName!=\"\"'>\n" +
            "    WHERE varDeviceName like '%${varDeviceName}%'\n" +
            "</if>\n" +
            "ORDER BY numDeviceID DESC ${sql}\n" +
            "</script>"})
    List<HashMap> getAll(HashMap<String, Object> data);




    @Select({"<script>\n" +
            "SELECT  count(1) from WD_UserDevice\n" +
            "<if test='varDeviceName!= null and varDeviceName!=\"\"'>\n" +
            "WHERE varDeviceName like '%${varDeviceName}%'\n" +
            "</if>\n" +
            "</script>"})
    int getAllCount(HashMap<String, Object> data);




    @Insert("insert WD_UserDevice(varDeviceName,numInCircuitID,numOutCircuitID) values(#{varDeviceName},#{numInCircuitID},#{numOutCircuitID})")
    @Options(useGeneratedKeys = true, keyProperty = "numDeviceID", keyColumn = "numDeviceID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_UserDevice set varDeviceName=#{varDeviceName},numInCircuitID=#{numInCircuitID},numOutCircuitID=#{numOutCircuitID} where numDeviceID=#{numDeviceID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_UserDevice where numDeviceID=#{numDeviceID}")
    int delete(HashMap<String, Object> data);


    @Select("SELECT u.numDeviceID,u.varDeviceName,u.numInCircuitID,u.numOutCircuitID\n" +
            "FROM WD_UserDevice u")
   List<HashMap> listAll();



}
