package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Component
public interface WD_TableMapper {
//    @Select("select tab2.numOrganizeID as numOrganizeID,tab2.varOrganizeName as varOrganizeName,tab3.varOrganizeName as varPOrganizeName,tab2.numPOrganizeID as numPOrganizeID from\n" +
//            "           WD_Organize tab2  join (select tab1.varOrganizeName,tab1.numOrganizeID from WD_Organize tab1 where numOrganizeID in(select tab.numPOrganizeID\n" +
//            "        from WD_Organize tab group by tab.numPOrganizeID)) tab3 on tab2.numPOrganizeID=tab3.numOrganizeID ORDER BY numOrganizeID DESC ${sql}")
//    List<HashMap> getAll(HashMap<String, Object> data);
//
//    @Select("SELECT  count(1) from WD_Organize")
//    int getAllCount(HashMap<String, Object> data);
//
//
//    @Insert("insert WD_Organize(varOrganizeName,numPOrganizeID) values(#{varOrganizeName},#{numPOrganizeID})")
//    @Options(useGeneratedKeys = true, keyProperty = "numOrganizeID", keyColumn = "numOrganizeID")
//    int insert(HashMap<String, Object> data);
//
//    @Update("update WD_Organize set varOrganizeName=#{varOrganizeName},numPOrganizeID=#{numPOrganizeID} where numOrganizeID=#{numOrganizeID}")
//    int update(HashMap<String, Object> data);
//    @Delete("delete from WD_Organize where numOrganizeID=#{numOrganizeID}")
//    int delete(HashMap<String, Object> data);
    @Select("SELECT t.numTableID,t.varIP,t.numPort,t.numDeviceAddress,t.numBandRate,\n" +
            "t.varStatus,t.numEnergyTypeID,t.varCom\n" +
            "FROM WD_Table t\n" +
            "ORDER BY t.numTableID ASC")
   List<HashMap> listAll();

//   @Select("SELECT  g.*,IFNULL(r.numCircuitGroupID,-1)  selected  from WD_CircuitGroup g  LEFT  join WD_DataUGRefCG r on g.numCircuitGroupID=r.numCircuitGroupID and  r.numDataUserGroupID=#{numDataUserGroupID};")
//    List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data);


}
