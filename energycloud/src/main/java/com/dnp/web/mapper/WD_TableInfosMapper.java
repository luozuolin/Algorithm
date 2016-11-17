package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_TableInfosMapper {
    @Select({"<script>\n" +
            "SELECT t.numTableID,t.varIP,t.numPort,t.numDeviceAddress,t.numBandRate,oo.numOrganizeID as numPOrganizeID,oo.varOrganizeName as varPOrganizeName,\n" +
            "t.varStatus,t.varCom,e.numEnergyTypeId,e.varEnergyTypeName,t.varName\n" +
            "FROM WD_Table t \n" +
            "LEFT JOIN WD_EnergyType e ON t.numEnergyTypeID=e.numEnergyTypeId\n" +
            "LEFT JOIN WD_Organize oo ON t.numOrganizeID=oo.numOrganizeID\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND t.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            " ORDER BY t.numTableID DESC ${sql}\n" +
            "</script>"})
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select({"<script>\n" +
            "SELECT  count(1) from WD_Table\n" +
            "where 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND numOrganizeID IN (${numOrganizeIDs})\n" +
            "</if>\n" +
            "</script>"})
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert  WD_Table(varIP,numPort,numDeviceAddress,numBandRate,varStatus,numEnergyTypeID,varCom,numOrganizeID,varName)" +
            " values(#{varIP},#{numPort},#{numDeviceAddress},#{numBandRate},'正常',#{numEnergyTypeId},#{varCom},#{numPOrganizeID},#{varName})")
    @Options(useGeneratedKeys = true, keyProperty = "numTableID", keyColumn = "numTableID")
    int insert(HashMap<String, Object> data);





    @Update("update WD_Table set varIP=#{varIP},numPort=#{numPort},numDeviceAddress=#{numDeviceAddress},numBandRate=#{numBandRate},varStatus=#{varStatus},numOrganizeID=#{numPOrganizeID},numEnergyTypeID=#{numEnergyTypeId},varCom=#{varCom},varName=#{varName}" +
            " where numTableID=#{numTableID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_Table where numTableID=#{numTableID}")
    int delete(HashMap<String, Object> data);


    @Select({"<script>\n" +
            "SELECT  t.numTableID,t.varName,IFNULL(trc.numCircuitID,-1) AS numCircuitID,t.numOrganizeID\n" +
            "            FROM WD_Table t\n" +
            "            LEFT JOIN  WD_TableRefCircuit trc ON t.numTableID=trc.NumTableID\n" +
            "            AND trc.numCircuitID=#{numCircuitID}\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND t.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "</script>"})
    List<HashMap> listAll(HashMap<String, Object> data); //查询出ip

    @Delete("delete from  WD_TableRefCircuit WHERE numCircuitID=#{numCircuitID}")
    int deleteBynumRefID(HashMap<String, Object> data);
    @Delete("delete from  WD_TableRefCircuit WHERE NumTableID=#{numTableID}")
    int deleteByNumTableID(HashMap<String, Object> data);
    @Insert("insert into WD_TableRefCircuit(datStart,datEnd,NumTableID,numCircuitID) values\n" +
            "(NOW(),NOW()-1,#{numTableID},#{numCircuitID})")
    @Options(useGeneratedKeys = true, keyProperty = "numT2CID", keyColumn = "numT2CID")
    int insertBynumRefID(HashMap<String, Object> data);

    @Select({"<script>\n" +
            "SELECT c.numCircuitID,c.varCircuitName,IFNULL(trc.NumTableID,-1) AS numTableID\n" +
            "FROM WD_Circuit c\n" +
            "LEFT JOIN WD_TableRefCircuit trc ON c.numCircuitID=trc.numCircuitID\n" +
            "AND trc.NumTableID=#{numTableID}\n" +
            "WHERE 1=1\n" +
            "<if test='isadmin==false.toString()'>\n" +
            "AND c.numOrganizeID in(${numOrganizeIDs})\n" +
            "</if>\n" +
            "</script>"})
    List<HashMap> addCircuitByNumTableID(HashMap<String, Object> data);//添加回路
}
