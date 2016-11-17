package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */
@Component
public interface WD_DataUserGroupMapper {
    @Select("SELECT   numDataUserGroupID,varDataUserGroupName from WD_DataUserGroup  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT  count(*) from WD_DataUserGroup  ")
    int getAllCount(HashMap<String, Object> data);


    @Insert("INSERT INTO WD_DataUserGroup (varDataUserGroupName) VALUES(#{varDataUserGroupName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numDataUserGroupId", keyColumn = "numDataUserGroupId")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_DataUserGroup SET varDataUserGroupName=#{varDataUserGroupName}  where numDataUserGroupId = #{numDataUserGroupId}")
    int update(HashMap<String, Object> data);

    @Delete("delete from  WD_DataUserGroup where numDataUserGroupId = #{numDataUserGroupId}")
    int delete(HashMap<String, Object> data);

    @Select("select * from WD_DataUserGroup ")
    List<HashMap> listAll();

  //  SELECT  g.*,IFNULL(r.numCircuitGroupID,-1)  selected  from WD_CircuitGroup g  LEFT  join WD_DataUGRefCG r on g.numCircuitGroupID=r.numCircuitGroupID and  r.numCircuitGroupID=2;
  //  SELECT  u.*,IFNULL(r.numUserID,-1)  selected  from WD_User u   LEFT  join WD_UserRefDataUG r on r.numUserID=u.numUserID and  r.numDataUserGroupID=27;
    @Select("SELECT  g.*,IFNULL(r.numCircuitGroupID,-1)  selected  from WD_CircuitGroup g  LEFT  join WD_DataUGRefCG r on g.numCircuitGroupID=r.numCircuitGroupID and  r.numDataUserGroupID=#{numDataUserGroupID};")
    List<HashMap> getCircuitGroupBynumDataUserGroupID(HashMap<String, Object> data);

    @Select("SELECT  u.*,IFNULL(r.numUserID,-1)  selected  from WD_User u   LEFT  join WD_UserRefDataUG r on r.numUserID=u.numUserID and  r.numDataUserGroupID=#{numDataUserGroupID}")
    List<HashMap> getUserBynumDataUserGroupID(HashMap<String, Object> data);
}
