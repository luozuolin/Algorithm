package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public interface GetDataMapper {
    // {numCircuitGroupID:$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val(),end:$("#date-range201").val()}
   // @Select("select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics ${sql}")
    @Select({"<script>\n" +
            "SELECT cg.varCircuitGroupName,DATE_FORMAT(a.datStatistics,'%Y-%m-%d') AS datStatistics,a.numValue,cg.numOrganizeID\n" +
            "FROM \n" +
            "(SELECT DISTINCT s.datStatistics,c.numCircuitID,crg.numCircuitGroupID,SUM(s.numValue) AS numValue\n" +
            "FROM WD_StatisticsCircuit s\n" +
            "LEFT JOIN WD_Circuit c ON s.numCircuitID=c.numCircuitID\n" +
            "LEFT JOIN WD_CircuitRefGroup crg ON s.numCircuitID=crg.numCircuitID\n" +
            "GROUP  BY crg.numCircuitGroupID) a\n" +
            "LEFT JOIN WD_CircuitGroup cg ON a.numCircuitGroupID=cg.numCircuitGroupID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND cg.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test = 'numCircuitGroupID != null and numCircuitGroupID != \"\"'>\n" +
            "and cg.numCircuitGroupID in(${numCircuitGroupID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "${sql}\n" +
            "</script>"})
    List<HashMap> getAll(HashMap<String, Object> data);
   // @Select("select count(*) from (select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics) a ")
    @Select({"<script>\n" +
            "SELECT count(1)\n" +
            "FROM \n" +
            "(SELECT DISTINCT s.datStatistics,c.numCircuitID,crg.numCircuitGroupID,SUM(s.numValue) AS numValue,c.numOrganizeID\n" +
            "FROM WD_StatisticsCircuit s\n" +
            "LEFT JOIN WD_Circuit c ON s.numCircuitID=c.numCircuitID\n" +
            "LEFT JOIN WD_CircuitRefGroup crg ON s.numCircuitID=crg.numCircuitID\n" +
            "GROUP  BY crg.numCircuitGroupID) a\n" +
            "LEFT JOIN WD_CircuitGroup cg ON a.numCircuitGroupID=cg.numCircuitGroupID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND a.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test = 'numCircuitGroupID != null and numCircuitGroupID != \"\"'>\n" +
            "and cg.numCircuitGroupID in(${numCircuitGroupID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND a.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND a.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "</script>"})
    int getAllCount(HashMap<String, Object> data);

 @Select({"<script>\n" +
         "SELECT cg.varCircuitGroupName,DATE_FORMAT(a.datStatistics,'%Y-%m-%d') AS datStatistics,a.numValue,cg.numOrganizeID\n" +
         "FROM \n" +
         "(SELECT DISTINCT s.datStatistics,c.numCircuitID,crg.numCircuitGroupID,SUM(s.numValue) AS numValue\n" +
         "FROM WD_StatisticsCircuit s\n" +
         "LEFT JOIN WD_Circuit c ON s.numCircuitID=c.numCircuitID\n" +
         "LEFT JOIN WD_CircuitRefGroup crg ON s.numCircuitID=crg.numCircuitID\n" +
         "GROUP  BY crg.numCircuitGroupID) a\n" +
         "LEFT JOIN WD_CircuitGroup cg ON a.numCircuitGroupID=cg.numCircuitGroupID\n" +
         "WHERE 1=1\n" +
         "<if test='isadmin==false.toString()'>\n" +
         "            AND cg.numOrganizeID in(${numOrganizeIDs})\n" +
         "    </if>\n" +
         "<if test = 'numCircuitGroupID != null and numCircuitGroupID != \"\"'>\n" +
         "and cg.numCircuitGroupID in(${numCircuitGroupID})\n" +
         "</if>\n" +
         "<if test= 'start!= null and start!=\"\"'>\n" +
         "     AND a.datStatistics &gt;= #{start}\n" +
         "</if>\n" +
         " <if test='end!= null and end!=\"\"'>\n" +
         "    AND a.datStatistics &lt;= #{end}\n" +
         " </if>  \n" +
         "</script>"})
    List<HashMap> listAll(HashMap<String, Object> data);//曲线图

    @Select({"<script>\n" +
            "SELECT s.numCircuitID,  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue\n" +
            "from WD_StatisticsCircuit s\n" +
            "left join WD_Circuit c on s.numCircuitID=c.numCircuitID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test = 'numCircuitID != null and numCircuitID != \"\"'>\n" +
            "and s.numCircuitID in(${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "ORDER BY c.varCircuitName,datStatistics ${sql}\n" +
            "</script>"})
    List<HashMap> getAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);
    // @Select("select count(*) from (select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics) a ")
    @Select({"<script>\n" +
            "select count(*) from (SELECT  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue\n" +
            "from WD_StatisticsCircuit s left join WD_Circuit c on s.numCircuitID=c.numCircuitID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test = 'numCircuitID != null and numCircuitID != \"\"'>\n" +
            "and s.numCircuitID in(${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "ORDER BY c.varCircuitName,datStatistics) a \n" +
            "</script>"})
    int getAllCountByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);
    @Select({"<script>\n" +
            "SELECT  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue\n" +
            "from WD_StatisticsCircuit s\n" +
            "left join WD_Circuit c on s.numCircuitID=c.numCircuitID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "<if test = 'numCircuitID != null and numCircuitID != \"\"'>\n" +
            "and s.numCircuitID in(${numCircuitID})\n" +
            "</if>\n" +
            "<if test= 'start!= null and start!=\"\"'>\n" +
            "     AND s.datStatistics &gt;= #{start}\n" +
            "</if>\n" +
            " <if test='end!= null and end!=\"\"'>\n" +
            "    AND s.datStatistics &lt;= #{end}\n" +
            " </if>  \n" +
            "ORDER BY c.varCircuitName,datStatistics\n" +
            "</script>"})
    List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);//曲线图

    @Select("SELECT   t2.numValue-t1.numValue numValue,   DATE_FORMAT(t2.datBuild,'%Y-%m-%d %H:%i:%s') datBuild ,c.varCircuitName   from " +
            "(SELECT    td.*, (@i:=@i+1) as i  from (SELECT   td.* from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c" +
            " WHERE   datBuild BETWEEN  #{datStart}  and #{datEnd}  and numColID=10792 " +
            " and r.numCircuitID=c.numCircuitID and td.numTableID=r.NumTableID  and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) td,(select @i:=0) as i) t1," +
            " (SELECT    td.*, (@j:=@j+1) as i  from (SELECT   td.*,r.numCircuitID  from WD_TableData td,WD_TableRefCircuit r" +
            " WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) td ,(select @j:=0) as j) t2,WD_Circuit c " +
            " WHERE t1.i=t2.i-1 and t2.numCircuitID=c.numCircuitID ORDER BY  t2.datBuild;")
    List<HashMap> getCircuitDatasByPerFiveMinutesAndnumCircuitlistAll(HashMap<String, Object> data);

    @Select("SELECT   t2.numValue-t1.numValue numValue,   t2.datBuild ,c.varCircuitName   from " +
            "(SELECT   td.*, (@i:=@i+1) as i from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c,(select @i:=0) as i " +
            "WHERE   datBuild BETWEEN  #{datStart}  and #{datEnd}  and numColID=10792 and r.numCircuitID=c.numCircuitID and td.numTableID=r.NumTableID  and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) t1," +
            "(SELECT   td.*,r.numCircuitID ,(@j:=@j+1) as i from WD_TableData td,WD_TableRefCircuit r,(select @j:=0) as i " +
            "WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) t2,WD_Circuit c " +
            "WHERE t1.i=t2.i-1 and t2.numCircuitID=c.numCircuitID ${sql}")
    List<HashMap> getCircuitDatasAllByPerFiveMinutesAndnumCircuit(HashMap<String, Object> data);
    @Select("SELECT   count(*)   from " +
            "(SELECT   td.*, (@i:=@i+1) as i from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c,(select @i:=0) as i " +
            "WHERE   datBuild BETWEEN  #{datStart}  and #{datEnd}  and numColID=10792 and r.numCircuitID=c.numCircuitID and td.numTableID=r.NumTableID  and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) t1," +
            "(SELECT   td.*,r.numCircuitID ,(@j:=@j+1) as i from WD_TableData td,WD_TableRefCircuit r,(select @j:=0) as i " +
            "WHERE   datBuild BETWEEN  #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID and r.numCircuitID=#{numCircuitID} ORDER BY  datBuild) t2,WD_Circuit c " +
            "WHERE t1.i=t2.i-1 and t2.numCircuitID=c.numCircuitID; ")
    int getCircuitDatasAllCountByPerFiveMinutesAndnumCircuit(HashMap<String, Object> data);


}
