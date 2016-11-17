package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther Tao
 * @date:2016/9/28 8:36
 */
@Component
public interface WD_OrganizeMapper {
    @Select("SELECT o.numOrganizeID,o.varOrganizeName,r.numOrganizeID numPOrganizeID, r.varOrganizeName varPOrganizeName\n" +
            " FROM WD_Organize o\n" +
            " LEFT JOIN WD_Organize r ON o.numPOrganizeID=r.numOrganizeID\n" +
            "ORDER BY o.numOrganizeID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_Organize")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_Organize(varOrganizeName,numPOrganizeID) values(#{varOrganizeName},#{numPOrganizeID})")
    @Options(useGeneratedKeys = true, keyProperty = "numOrganizeID", keyColumn = "numOrganizeID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_Organize set varOrganizeName=#{varOrganizeName},numPOrganizeID=#{numPOrganizeID} where numOrganizeID=#{numOrganizeID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_Organize where numOrganizeID=#{numOrganizeID}")
    int delete(HashMap<String, Object> data);


    //    @Select("SELECT o.numOrganizeID numPOrganizeID,o.varOrganizeName varPOrganizeName\n" +
//            "FROM WD_Organize o")
    @Select({"<script>\n" +
            "SELECT   o.numOrganizeID AS numPOrganizeID,o.varOrganizeName AS varPOrganizeName\n" +
            "      FROM WD_Organize o\n" +
            "      WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "    AND o.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "ORDER BY o.numOrganizeID DESC\n"+
            "</script>\n" })
    List<HashMap> listAll(HashMap<String,Object> data);

    @Select({"<script>\n" +
            "SELECT  o.numOrganizeID,o.varOrganizeName\n" +
            "      FROM WD_Organize o\n" +
            "      WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "    AND o.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "    ORDER BY o.numOrganizeID DESC\n"+
            "</script>\n" })
    List<HashMap> OrganizelistAll(HashMap<String,Object> data);
    @Select("SELECT o.numOrganizeID, o.varOrganizeName,o.numPOrganizeID,IFNULL(u.numUserID,-1)  selected  from WD_Organize  o LEFT join WD_User  u on o.numOrganizeID=u.numOrganizeID and u.numUserID=#{numUserID};")
    List<HashMap> getBynumUserID(HashMap<String,Object> data);

    @Select("SELECT  g.*,IFNULL(r.numCircuitGroupID,-1)  selected  from WD_CircuitGroup g  LEFT  join WD_DataUGRefCG r on g.numCircuitGroupID=r.numCircuitGroupID and  r.numDataUserGroupID=#{numDataUserGroupID};")
    List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data);


}
