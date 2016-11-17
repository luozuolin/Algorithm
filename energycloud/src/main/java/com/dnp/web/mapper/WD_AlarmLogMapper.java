package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public interface WD_AlarmLogMapper {

    @Select({"<script>\n" +
            "SELECT  c.varCircuitName,DATE_FORMAT(a.datStart,'%Y-%m-%d') datStart,a.varMessage\n" +
            "FROM WD_AlarmLog a,WD_TableRefCircuit t,WD_Circuit c\n" +
            "WHERE a.numMeterID=t.numT2CID\n" +
            "AND t.numCircuitID =c.numCircuitID\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "  AND c.numCircuitID IN (${numCircuitID})\n" +
            " </if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStart &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStart &lt;= #{end}\n" +
            " </if>  \n" +
            "GROUP BY a.datStart DESC ${sql}"+
            " </script>"})
    List<HashMap> getAll(HashMap<String, Object> data);



    @Select({"<script>\n" +
            "SELECT  count(1)\n" +
            "FROM WD_AlarmLog a,WD_TableRefCircuit t,WD_Circuit c\n" +
            "WHERE a.numMeterID=t.numT2CID\n" +
            "AND t.numCircuitID =c.numCircuitID\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "  AND c.numCircuitID IN (${numCircuitID})\n" +
            " </if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStart &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStart &lt;= #{end}\n" +
            " </if>  \n" +
            " </script>"})
    int getAllCount(HashMap<String, Object> data);


    @Select({"<script>\n" +
            "SELECT  c.varCircuitName,DATE_FORMAT(a.datStart,'%Y-%m-%d') datStart,a.varMessage\n" +
            "FROM WD_AlarmLog a,WD_TableRefCircuit t,WD_Circuit c\n" +
            "WHERE a.numMeterID=t.numT2CID\n" +
            "AND t.numCircuitID =c.numCircuitID\n" +
            "AND t.numCircuitID IN (${numCircuitID})\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStart &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStart &lt;= #{end}\n" +
            " </if>  \n" +
            " </script>"})
    List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);


    @Select("SELECT  c.varCircuitName,DATE_FORMAT(a.datStart,'%Y-%m-%d') datStart,a.varMessage\n" +
            "FROM WD_AlarmLog a,WD_TableRefCircuit t,WD_Circuit c\n" +
            "WHERE a.numMeterID=t.numT2CID\n" +
            "AND t.numCircuitID =c.numCircuitID\n")
    List<HashMap> readAll();

}
