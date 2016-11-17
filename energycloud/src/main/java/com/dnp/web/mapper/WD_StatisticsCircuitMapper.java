package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/8/16.
 */
@Component
public interface WD_StatisticsCircuitMapper {
    @Select({"<script>\n" +
            "SELECT s1.varCircuitName, s1.numCircuitID,DATE_FORMAT(s1.`currentDate`,'%Y-%m-%d') AS currentDate,s1.numValue,s2.numValue AS 'chainValue',s3.numValue AS 'onYearValue',s4.numValue AS 'onYearChainValue' from (\n" +
            "            SELECT c.varCircuitName,s.datStatistics AS 'currentDate',s.numValue,s.numCircuitID,c.numOrganizeID,\n" +
            "            DATE_SUB(s.datStatistics,INTERVAL 1 MONTH) AS '环比时间',\n" +
            "            DATE_SUB(s.datStatistics,INTERVAL 1 YEAR) AS '同比时间',\n" +
            "\t\t\t\t\t\tDATE_SUB(DATE_SUB(s.datStatistics,INTERVAL 1 YEAR),INTERVAL 1 MONTH) AS '去年环比时间'\n" +
            "            from WD_StatisticsCircuit s,WD_Circuit c WHERE s.numCircuitID=c.numCircuitID ) s1\n" +
            "            LEFT join WD_StatisticsCircuit s2 ON s1.`环比时间`=s2.datStatistics and s1.numCircuitID=s2.numCircuitID\n" +
            "            LEFT join WD_StatisticsCircuit s3 ON s1.`同比时间`=s3.datStatistics and s1.numCircuitID=s3.numCircuitID\n" +
            "\t\t\t\t\t\tLEFT join WD_StatisticsCircuit s4 ON s1.`去年环比时间`=s4.datStatistics and s1.numCircuitID=s4.numCircuitID\n" +
            "    WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND s1.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "  AND s1.numCircuitID IN (${numCircuitID})\n" +
            " </if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s1.currentDate &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s1.currentDate &lt;= #{end}\n" +
            " </if>  \n" +

            "${sql}\n"+
            "</script>\n" })
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select({"<script>\n" +
            "SELECT COUNT(1)\n" +
            "FROM WD_StatisticsCircuit s\n" +
            "LEFT JOIN WD_Circuit c ON s.numCircuitID=c.numCircuitID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "AND s.numCircuitID IN (${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "</script>\n" })
    int getAllCount(HashMap<String, Object> data);
    @Select({"<script>\n" +
            "select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,b.varCircuitName,a.numValue\n" +
            "from WD_StatisticsCircuit a, WD_Circuit b \n" +
            "where a.numCircuitID=b.numCircuitID\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND b.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "AND a.numCircuitID IN (${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "</script>\n" })
    List<HashMap> listAll(HashMap<String, Object> data);
    @Select({"<script>\n" +
            "select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,b.varCircuitName,a.numValue\n" +
            "from WD_StatisticsCircuit a, WD_Circuit b \n" +
            "where a.numCircuitID=b.numCircuitID\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND b.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "AND a.numCircuitID IN (${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "</script>\n" })
    List<HashMap> getchartdata(HashMap<String, Object> data);//曲线图


    @Select("SELECT s1.beginningOfMonth,DATE_FORMAT(s1.currentTime,'%Y-%m-%d %H:%m:%s') AS currentTime,s1.varCircuitName,s1.numCircuitID,s1.valuee\n" +
            "FROM \n" +
            "(select SUM(s.numValue) AS 'valuee',c.varCircuitName,s.datStatistics,(DATE_FORMAT(s.datStatistics,'%Y-%m-01'))AS 'beginningOfMonth',NOW() AS 'currentTime',s.numCircuitID\n" +
            "FROM WD_StatisticsCircuit s \n" +
            "LEFT JOIN WD_Circuit c ON s.numCircuitID=c.numCircuitID\n" +
            "GROUP BY s.numCircuitID\n" +
            ") s1\n" +
            "WHERE 1=1\n" +
            "AND s1.numCircuitID=#{numCircuitID} ${sql}")
    List<HashMap> listByBeginningOfMonth(HashMap<String, Object> data);

    @Select({"<script>\n" +
            "SELECT s1.varCircuitName, s1.numCircuitID,s1.`currentDate`,s1.numValue,s2.numValue AS 'chainValue',s3.numValue AS 'onYearValue',s4.numValue AS 'onYearChainValue' from (\n" +
            "            SELECT c.varCircuitName,s.datStatistics AS 'currentDate',s.numValue,s.numCircuitID,c.numOrganizeID,\n" +
            "            DATE_SUB(s.datStatistics,INTERVAL 1 MONTH) AS '环比时间',\n" +
            "            DATE_SUB(s.datStatistics,INTERVAL 1 YEAR) AS '同比时间',\n" +
            "\t\t\t\t\t\tDATE_SUB(DATE_SUB(s.datStatistics,INTERVAL 1 YEAR),INTERVAL 1 MONTH) AS '去年环比时间'\n" +
            "            from WD_StatisticsCircuit s,WD_Circuit c WHERE s.numCircuitID=c.numCircuitID ) s1\n" +
            "            LEFT join WD_StatisticsCircuit s2 ON s1.`环比时间`=s2.datStatistics and s1.numCircuitID=s2.numCircuitID\n" +
            "            LEFT join WD_StatisticsCircuit s3 ON s1.`同比时间`=s3.datStatistics and s1.numCircuitID=s3.numCircuitID\n" +
            "\t\t\t\t\t\tLEFT join WD_StatisticsCircuit s4 ON s1.`去年环比时间`=s4.datStatistics and s1.numCircuitID=s4.numCircuitID\n" +
            "    WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND s1.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "<if test='numCircuitID!= null and numCircuitID!=\"\"'>\n" +
            "  AND s1.numCircuitID IN (${numCircuitID})\n" +
            " </if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s1.currentDate &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s1.currentDate &lt;= #{end}\n" +
            " </if>  \n" +
            "ORDER BY s1.`currentDate`\n" +
            "</script>\n" })
    List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);
}
