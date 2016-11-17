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
public interface WD_ImplementManageMapper {
    @Select({"<script>\n" +
            "SELECT p.numImplementID,p.varImplementName,p.varImplementUser,p.numOrganizeID,o.varOrganizeName,p.datUpdate,p.datStart,p.datEnd,p.numEffort,p.varPhone,(CASE WHEN p.numStatus=1 THEN '提交' WHEN p.numStatus=2 THEN '实施' ELSE '完成' END) AS numStatus\n" +
            "                       FROM WD_Implement p\n" +
            "LEFT JOIN WD_Organize o ON p.numOrganizeID=o.numOrganizeID\n" +
            "    WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "<if test='varImplementName!= null and varImplementName!=\"\"'>\n" +
            "and p.varImplementName like '%${varImplementName}%'\n" +
            "</if>\n" +
            "AND p.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "\n" +
            "                       ORDER BY p.numImplementID DESC ${sql}\n"+
            "</script>"})

    List<HashMap> getAll(HashMap<String, Object> data);


    @Select({"<script>\n" +
            "SELECT  count(1) from WD_Implement" +
            "    WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "<if test='numImplementID!= null and numImplementID!=\"\"'>\n" +
            "and varImplementName IN(${varImplementName})\n" +
            "</if>\n" +
            "            AND numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "</script>"})
    int getAllCount(HashMap<String, Object> data);

//    @Select("SELECT  count(1) from WD_Implement P\n" +
//            "WHERE P.numImplementID=#{numImplementID}")
//    int getAllCountByID(HashMap<String, Object> data);


    @Insert("insert WD_Implement(varImplementName,varImplementUser,datUpdate,datStart,datEnd,numEffort,varPhone,numStatus,numOrganizeID) values(#{varImplementName},#{varImplementUser},NOW(),#{datStart},#{datEnd},#{numEffort},#{varPhone},#{numStatus},#{numOrganizeID})")
    @Options(useGeneratedKeys = true, keyProperty = "numImplementID", keyColumn = "numImplementID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_Implement set varImplementName=#{varImplementName},varImplementUser=#{varImplementUser},datUpdate=NOW(),datStart=#{datStart},datEnd=#{datEnd},numEffort=#{numEffort},varPhone=#{varPhone},numStatus=#{numStatus},numOrganizeID=#{numOrganizeID} where numImplementID=#{numImplementID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_Implement where numImplementID=#{numImplementID}")
    int delete(HashMap<String, Object> data);

    @Select("SELECT p.varplan\n" +
            "FROM WD_Implement p WHERE numImplementID=#{0}\n")
    String getuploadUrl(int numImplementID);


    @Update("update WD_Implement set varplan=#{0} where numImplementID=#{1}")
    int updateUrl(String varplan,int numImplementID);


    @Select({"<script>\n" +
            "SELECT *\n" +
            "from   WD_Implement p " +
            "    WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND numOrganizeID =#{numOrganizeIDs}\n" +
            "    </if>\n" +
            "</script>"})
    List<HashMap> listAll(HashMap<String, Object> data);

    @Select("SELECT   *\n" +
            "from WD_Implement p\n" +
            "WHERE p.numImplementID=#{numImplementID};")
    List<HashMap> getImplementByID(HashMap<String, Object> data);

}
