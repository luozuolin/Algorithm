package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_TableDataInfosMapper {
    @Select("select td.numDataID as numDataID,\n" +
            "td.datBuild as datBuild,\n" +
            "td.numValue,\n" +
            "t.NumTableID as numTableID,t.varIP as varIP,\n" +
            "c.numColID as numColID,c.varEName as varEName\n" +
            "from WD_TableData as td \n" +
            "LEFT JOIN WD_Column as c on td.numColID=c.numColID \n" +
            "left JOIN WD_Table as t on td.numTableID=t.numTableID\n" +
            "ORDER BY td.numDataID ASC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_TableData")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_TableData(datBuild,numValue,numColID,NumTableID) values(#{datBuild},#{numValue},#{numColID},#{numTableID})")
    @Options(useGeneratedKeys = true, keyProperty = "numDataID", keyColumn = "numDataID")
    int insert(HashMap<String, Object> data);





    @Update("update WD_TableData set datBuild=#{datBuild},numValue=#{numValue},numColID=#{numColID},NumTableID=#{numTableID} where numDataID=#{numDataID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_TableData where numDataID=#{numDataID}")
    int delete(HashMap<String, Object> data);


    @Select("SELECT  td.datBuild,td.numValue,td.numColID,td.NumTableID ,r.numCircuitID,t.varName ,c.varCName from  WD_TableData td,(\n" +
            "SELECT   NumTableID,numColID,max(datBuild)  datBuild  from WD_TableData  WHERE datBuild BETWEEN #{datStart} and #{datEnd} and numColID \n" +
            "in(SELECT  numColID from WD_Column WHERE varCName in('Ua','Ub','Uc','Ia','Ib','Ic','P','PW')) GROUP BY NumTableID,numColID) d,WD_Table t ,WD_TableRefCircuit r ,WD_Column c  WHERE td.NumTableID=d.NumTableID\n" +
            "and td.numColID=d.numColID and td.datBuild=d.datBuild and td.datBuild BETWEEN #{datStart} and #{datEnd} and td.NumTableID=t.numTableID and td.NumTableID=r.NumTableID and td.numColID=c.numColID;")
    List<HashMap> getrealdatafromdatabase(HashMap<String, Object> data);


}
