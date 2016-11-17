package com.dnp.web.encosaas.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/13.
 */
@Component
public interface FirstPageMapper {

    @Select({"<script>SELECT tmax.numValue-tmin.numValue numValue,tmax.varCircuitName from ("+
            "SELECT  d.numCircuitID,d.varCircuitName,t.numValue from ("+
            "SELECT    max(datBuild) maxdatBuild,min(datBuild) mindatBuild,c.varCircuitName,c.numCircuitID,td.NumTableID from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c"+
            " WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID  and r.numCircuitID=c.numCircuitID"+
            " <if test='isadmin==false.toString()'>" +
            "  and c.numOrganizeID in(${numOrganizeIDs})" +
            " </if>"+
            "and r.numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents  WHERE numPCircuitID!=-1)"+
    "GROUP BY numCircuitID,c.varCircuitName) d,WD_TableData t WHERE d.maxdatBuild=t.datBuild and t.NumTableID=d.NumTableID and t.numColID=10792) tmax,"+
     "       ("+
    "SELECT  d.numCircuitID,t.numValue from ("+
     "      SELECT    max(datBuild) maxdatBuild,min(datBuild) mindatBuild,c.varCircuitName,c.numCircuitID,td.NumTableID from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c"+
    " WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID  and r.numCircuitID=c.numCircuitID"+
    " <if test='isadmin==false.toString()'>" +
            "  and c.numOrganizeID in(${numOrganizeIDs})" +
            " </if>"+
    "and r.numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents  WHERE numPCircuitID!=-1)"+
    "GROUP BY numCircuitID,c.varCircuitName) d,WD_TableData t WHERE d.mindatBuild=t.datBuild and t.NumTableID=d.NumTableID and t.numColID=10792) tmin WHERE tmax.numCircuitID=tmin.numCircuitID"+
"</script>" })
    List<HashMap> getAll(HashMap<String,Object> data);
    @Select({"<script>SELECT DATE_FORMAT(datStatistics,'%Y-%m-%d') datStatistics ,SUM(numValue) total from WD_StatisticsCircuit WHERE datStatistics BETWEEN date_add(#{datStart}, interval - day(#{datStart}) + 1 day) and #{datStart} and numCircuitID in(\n" +
            "SELECT  numCircuitID from WD_Circuit WHERE    numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents WHERE numPCircuitID!=-1) " +
            "  <if test='isadmin==false.toString()'>" +
            " and numOrganizeID in(${numOrganizeIDs})" +
            "  </if>" +
            ") GROUP BY datStatistics;" +
            " </script>"})
    List<HashMap> getMonth(HashMap<String,Object> data);
    @Select({"<script>SELECT   CONCAT(d2.datBuild,':00') datBuild,d2.numValue-d1.numValue numValue from (" +
            "SELECT    DATE_FORMAT(td.datBuild,'%Y-%m-%d %H:%i') datBuild,sum(numValue) numValue, (@i:=@i+1) as i from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c,(select @i:=0) as i " +
            "WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID  and r.numCircuitID=c.numCircuitID  " +
            "<if test='isadmin==false.toString()'>" +
            " and c.numOrganizeID in(${numOrganizeIDs})" +
            "  </if> " +
            "and r.numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents  WHERE numPCircuitID!=-1)" +
            "GROUP BY datBuild) d1," +
            "(SELECT    DATE_FORMAT(td.datBuild,'%Y-%m-%d %H:%i') datBuild,sum(numValue) numValue, (@j:=@j+1) as i from WD_TableData td,WD_TableRefCircuit r,WD_Circuit c,(select @j:=0) as i " +
            "WHERE   datBuild BETWEEN #{datStart}  and #{datEnd} and numColID=10792 and td.numTableID=r.NumTableID  and r.numCircuitID=c.numCircuitID  " +
            "<if test='isadmin==false.toString()'>" +
            " and c.numOrganizeID in(${numOrganizeIDs})" +
            "  </if> " +
            "and r.numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents  WHERE numPCircuitID!=-1)" +
            "GROUP BY datBuild) d2 WHERE d1.i=d2.i-1</script>"})
    List<HashMap> getDay(HashMap<String,Object> data);
    @Select("SELECT  * from WD_Organize WHERE numPOrganizeID=#{numPOrganizeID}")
    List<HashMap> getStation(HashMap<String,Object> data);
    @Select("SELECT  *  from WD_Config WHERE numCircuitGroupID=#{numCircuitGroupID} and numConfigTypeID=2")
    List<HashMap> getXML(HashMap<String,Object> data);


    @Select("SELECT g.*,p.numPCircuitID,c.varID,c.varCircuitName from WD_CircuitRefGroup g left join WD_Circuit c on g.numCircuitID=c.numCircuitID left join  WD_CircuitParents p on g.numCircuitID=p.numCircuitID WHERE g.numCircuitGroupID=#{numCircuitGroupID}")
    List<HashMap> getCircuitsBynumCircuitGroupID(HashMap<String,Object> data);

}
