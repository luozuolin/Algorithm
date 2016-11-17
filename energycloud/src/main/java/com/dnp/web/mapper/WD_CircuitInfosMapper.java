package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/9/28 8:37
*/
@Component
public interface WD_CircuitInfosMapper {
    @Select({"<script>\n" +
            "SELECT c.numCircuitID,c.varCircuitName,c.varID,c.numOrganizeID,o.varOrganizeName\n" +
            "FROM WD_Circuit c\n" +
            "LEFT JOIN WD_Organize o ON c.numOrganizeID=o.numOrganizeID\n" +
            "    WHERE 1=1\n" +
            "    <if test='varCircuitName!= null and varCircuitName!=\"\"'>\n" +
            "            AND c.varCircuitName LIKE '%${varCircuitName}%' \n" +
            "    </if>\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "ORDER BY c.numCircuitID DESC ${sql}\n" +
            "</script>\n" })
    List<HashMap> getAll(HashMap<String, Object> data);


@Select({"<script>\n" +
        "        SELECT  count(*) from WD_Circuit\n" +
        "    WHERE 1=1\n" +
        "         <if test = 'varCircuitName != null and varCircuitName != \"\"'>\n" +
        "          and varCircuitName like '%${varCircuitName}%'\n" +
        "        </if>\n" +
        "<if test='isadmin==false.toString()'>\n" +
        "            and numOrganizeID in(${numOrganizeIDs})\n" +
        "    </if>\n" +
        "</script>"})
    int getAllCount(HashMap<String, Object> data);



    @Select("SELECT  c.numCircuitID,c.varCircuitName,c.varID,o.varOrganizeName from WD_Circuit c inner join WD_CircuitRefGroup r on  c.numCircuitID=r.numCircuitID LEFT JOIN WD_Organize o ON c.numOrganizeID=o.numOrganizeID WHERE   r.numCircuitGroupID=#{numCircuitGroupID}\n" +
            "            ORDER BY c.numOrganizeID ASC ${sql}")
    List<HashMap> getAllBynumCircuitGroupID(HashMap<String, Object> data);



    @Select("SELECT  count(*) from WD_Circuit c inner join WD_CircuitRefGroup r on  c.numCircuitID=r.numCircuitID LEFT JOIN WD_Organize o ON c.numOrganizeID=o.numOrganizeID WHERE   r.numCircuitGroupID=#{numCircuitGroupID}  ORDER BY c.numOrganizeID ASC")
    int getAllCountBynumCircuitGroupID(HashMap<String, Object> data);


    @Select("SELECT c.*,o.varOrganizeName from WD_Circuit c,WD_Organize o WHERE c.numOrganizeID=#{numOrganizeID}  and c.numOrganizeID=o.numOrganizeID  ORDER BY numCircuitID ASC ${sql}")
    List<HashMap> getAllBynumOrganizeID(HashMap<String, Object> data);



    @Select("SELECT  count(*) from WD_Circuit c,WD_Organize o WHERE c.numOrganizeID=#{numOrganizeID}  and c.numOrganizeID=o.numOrganizeID  ORDER BY numCircuitID ASC")
    int getAllCountBynumOrganizeID(HashMap<String, Object> data);

    @Insert("insert WD_Circuit(varCircuitName,varID,numOrganizeID) values (#{varCircuitName},#{varID},#{numOrganizeID})")
    @Options(useGeneratedKeys = true, keyProperty = "numCircuitID", keyColumn = "numCircuitID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_Circuit set varCircuitName=#{varCircuitName},varID=#{varID},numOrganizeID=#{numOrganizeID} where numCircuitID=#{numCircuitID}")
    int update(HashMap<String, Object> data);

    @Delete("DELETE from WD_TableRefCircuit WHERE numCircuitID =#{numCircuitID}")
    int deleteTableRefCircuit(HashMap<String, Object> data);

    @Delete("DELETE from WD_StatisticsCircuit WHERE numCircuitID =#{numCircuitID}")
    int deleteStatisticsCircuit(HashMap<String, Object> data);

    @Delete("DELETE from WD_TransRefCircuit WHERE numCircuitID =#{numCircuitID}")
    int deleteTransRefCircuit(HashMap<String, Object> data);

    @Delete("DELETE FROM WD_CircuitParents WHERE numCircuitID=#{numCircuitID}")
    int deleteCircuitParents(HashMap<String, Object> data);

    @Delete("DELETE FROM WD_CircuitRefGroup  WHERE numCircuitID=#{numCircuitID}")
    int deleteCircuitRefGroup(HashMap<String, Object> data);

    @Delete("delete from WD_Circuit where numCircuitID=#{numCircuitID}")
    int delete(HashMap<String, Object> data);

    @Select({"<script>\n" +
            "select * from WD_Circuit\n" +
            "    WHERE 1=1\n" +
            "         <if test = 'varCircuitName != null and varCircuitName != \"\"'>\n" +
            "          and varCircuitName like '%${varCircuitName}%'\n" +
            "        </if>\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            and numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "</script>"})
    List<HashMap> listAll(HashMap<String, Object> data);


    @Select("SELECT c.varCircuitName\n" +
            "FROM WD_Circuit c\n" +
            "WHERE c.varCircuitName LIKE '%${varCircuitName}%'")
    List<HashMap> getCircuitNameByLike();

    @Select("select c.numCircuitID AS numOutCircuitIDOriginal,c.varCircuitName AS varOutCircuitNameOriginal\n" +
            "from WD_Circuit c")
    List<HashMap> listOriginal();
/*
    @Select({"<script>",
            "SELECT  count(*) from WD_User u  left join WD_Position p on u.numPositionID=p.numPositionID where 1=1 " +
                    "<if test='varUserName!= null and varUserName!=\"\"'>",
            " and  varUserName like '%${varUserName}%' ",
            "</if>",
            "<if test='numOrganizeID!=0 and numOrganizeID!= null and numOrganizeID!=\"\"'>",
            "  and  u.numOrganizeID=#{numOrganizeID} ",
            "</if>",
            " </script>"})
*/


    @Select({"<script>",
            "SELECT  * from  WD_Circuit " +
           "<if test='isadmin!=\"true\"'>",
            "  WHERE numOrganizeID in(${numOrganizeIDs}) ",
            "</if>",
            " </script>"})
  //  @Select("SELECT  * from  WD_Circuit WHERE numOrganizeID in(${numOrganizeIDs})")
    List<HashMap> getCircuitsBynumOrganizeIDs(HashMap<String, Object> data);


}
