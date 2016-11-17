package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther Tao
 * @date:2016/9/28 13:47
 */
@Component
public interface WD_CircuitGroupMapper {


    @Select({"<script>\n" +
            "SELECT cg.numCircuitGroupID,cg.varCircuitGroupName,(CASE WHEN cg.varSubComFlag=1 THEN '是' WHEN cg.varSubComFlag=2 THEN '否'  END) AS varSubComFlag ,e.numEnergyTypeId,e.varEnergyTypeName,o.numOrganizeID,o.varOrganizeName\n" +
            "FROM WD_CircuitGroup cg\n" +
            "LEFT JOIN WD_EnergyType e ON cg.numEnergyTypeID=e.numEnergyTypeId\n" +
            "LEFT JOIN WD_Organize o ON cg.numOrganizeID=o.numOrganizeID\n" +
            "    WHERE 1=1\n" +
            "<if test='varCircuitGroupName!= null and varCircuitGroupName!=\"\"'>\n" +
            "and cg.varCircuitGroupName LIKE '%${varCircuitGroupName}%'\n" +
            "</if>\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "            AND cg.numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "ORDER BY cg.numCircuitGroupID DESC ${sql}\n" +
            "</script>"})
    List<HashMap> getAll(HashMap<String, Object> data);


    @Select({"<script>\n" +
            "SELECT  count(1) from WD_CircuitGroup\n" +
            "    WHERE 1=1\n" +
            "<if test='varCircuitGroupName!= null and varCircuitGroupName!=\"\"'>\n" +
            "and varCircuitGroupName LIKE '%${varCircuitGroupName}%'\n" +
            "</if>\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND numOrganizeID in(${numOrganizeIDs})\n" +
            "    </if>\n" +
            "</script>"})
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert INTO WD_CircuitGroup(varCircuitGroupName,varSubComFlag,numEnergyTypeID,numOrganizeID) values(#{varCircuitGroupName},#{varSubComFlag},#{numEnergyTypeId},#{numOrganizeID})")
    @Options(useGeneratedKeys = true, keyProperty = "numCircuitGroupID", keyColumn = "numCircuitGroupID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_CircuitGroup set varCircuitGroupName=#{varCircuitGroupName},varSubComFlag=#{varSubComFlag},numEnergyTypeID=#{numEnergyTypeId},numOrganizeID=#{numOrganizeID} where numCircuitGroupID=#{numCircuitGroupID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_CircuitGroup where numCircuitGroupID=#{numCircuitGroupID}")
    int delete(HashMap<String, Object> data);
    @Delete("DELETE FROM WD_CircuitRefGroup  WHERE numCircuitGroupID=#{numCircuitGroupID}")
    int deleteCircuitRefGroup(HashMap<String, Object> data);
    @Delete("DELETE FROM WD_DataUGRefCG  WHERE numCircuitGroupID=#{numCircuitGroupID}")
    int deleteDataUGRefCG(HashMap<String, Object> data);


    @Select("SELECT c.numCircuitGroupID,c.varCircuitGroupName,c.varSubComFlag,c.numEnergyTypeID\n" +
            " FROM WD_CircuitGroup c ORDER BY c.numCircuitGroupID ASC")
    List<HashMap> listAll();
    /////////////////////////////回路///////////////////////////////
    @Select({"<script>\n" +
            "SELECT c.numCircuitID,c.varCircuitName,IFNULL(crg.numCircuitGroupID,-1) AS numCircuitGroupID\n" +
            "FROM WD_Circuit c\n" +
            "LEFT JOIN WD_CircuitRefGroup crg ON c.numCircuitID=crg.numCircuitID AND crg.numCircuitGroupID=#{numCircuitGroupID}\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "</script>"})
    List<HashMap> addCGCircuitByCircuitGroupID(HashMap<String, Object> data);

    @Delete("delete from  WD_CircuitRefGroup WHERE numCircuitGroupID=#{numCircuitGroupID}")
    int deleteBynumRefID(HashMap<String, Object> data);

    @Insert("insert into WD_CircuitRefGroup(numCircuitID,numCircuitGroupID) values(#{numCircuitID},#{numCircuitGroupID})")
    @Options(useGeneratedKeys = true, keyProperty = "numC2GID", keyColumn = "numC2GID")
    int insertBynumRefID(HashMap<String, Object> data);

//    //根据回路组id查询对应回路关联信息(主要是在删除回路组是判断有关联信息的不能被删除)
//    @Select("select count(*) from WD_CircuitRefGroup where numCircuitGroupId=#{numCircuitGroupId}")
//     int getBynumRefID(HashMap<String, Object> data);

    // //根据回路组id查询对应数据用户组关联信息(主要是在删除回路组是判断有关联信息的不能被删除)
    @Select("select count(1) from WD_CircuitRefGroup where numCircuitGroupID=#{numCircuitGroupID}")
    int getCircuitRefGroupBynumCircuitGroupID(HashMap<String, Object> data);

    //////////////////////////////////数据用户组/////////////////////////////////////////////////
//    @Select({"<script>\n" +
//            "SELECT durc.numRefID,dug.numDataUserGroupID,dug.varDataUserGroupName,IFNULL(cg.numCircuitGroupID,-1) AS numCircuitGroupID,cg.varCircuitGroupName\n" +
//            "            FROM WD_DataUGRefCG durc\n" +
//            "            LEFT JOIN WD_DataUserGroup dug ON durc.numDataUserGroupID=dug.numDataUserGroupID\n" +
//            "            LEFT JOIN WD_CircuitGroup cg ON durc.numCircuitGroupID=cg.numCircuitGroupID\n" +
//            "            AND cg.numCircuitGroupID=#{numCircuitGroupID}\n" +
//            "WHERE 1=1\n" +
//            "<if test='isadmin==false.toString()'>\n" +
//            "AND cg.numOrganizeID in(${numOrganizeIDs})\n" +
//            "</if>\n" +
//            "</script>"})
//
//    List<HashMap> addDataUGRefCgByCircuitGroupID1(HashMap<String, Object> data);


    @Select("SELECT  dug.numDataUserGroupID,dug.varDataUserGroupName,IFNULL(durc.numCircuitGroupID,-1) AS numCircuitGroupID\n" +
            "from WD_DataUserGroup dug \n" +
            "LEFT  join WD_DataUGRefCG durc ON durc.numDataUserGroupID=dug.numDataUserGroupID \n" +
            "and durc.numCircuitGroupID=#{numCircuitGroupID}")
    List<HashMap> addDataUGRefCgByCircuitGroupID(HashMap<String, Object> data);

    @Delete("delete from  WD_DataUGRefCG WHERE numCircuitGroupID=#{numCircuitGroupID}")
    int deleteDataUGRefCGByNumCircuitGroupID(HashMap<String, Object> data);

    @Insert("insert into WD_DataUGRefCG(numDataUserGroupID,numCircuitGroupID) values(#{numDataUserGroupID},#{numCircuitGroupID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insertDataUGRefCGByNumCircuitGroupID(HashMap<String, Object> data);


    // //根据回路组id查询对应数据用户组关联信息(主要是在删除回路组是判断有关联信息的不能被删除)
    @Select("select count(1) from WD_DataUGRefCG where numCircuitGroupID=#{numCircuitGroupID}")
    int getDataUGRefCgByNumCircuitGroupID(HashMap<String, Object> data);
}
