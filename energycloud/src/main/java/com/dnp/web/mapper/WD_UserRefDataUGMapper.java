package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */
@Component
public interface WD_UserRefDataUGMapper {
    @Delete("delete from WD_UserRefDataUG where numDataUserGroupID=#{numDataUserGroupID}")
    int deleteBynumDataUserGroupId(HashMap<String, Object> data);
    @Delete("delete from WD_UserRefDataUG where numUserID=#{numUserID}")
    int deleteBynumUserID(HashMap<String, Object> data);


    @Select("SELECT   r.numRefID,r.numUserID,u.varUserName,r.numDataUserGroupID,d.varDataUserGroupName from WD_UserRefDataUG r,WD_User u,WD_DataUserGroup d WHERE  r.numDataUserGroupID=d.numDataUserGroupID and r.numUserID=u.numUserID  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT  count(*) from WD_UserRefDataUG r,WD_User u,WD_DataUserGroup d WHERE  r.numDataUserGroupID=d.numDataUserGroupID and r.numUserID=u.numUserID ")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert into WD_UserRefDataUG(numDataUserGroupID,numUserID) values(#{numDataUserGroupID},#{numUserID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_UserRefDataUG SET numDataUserGroupID=#{numDataUserGroupID},numUserID=#{numUserID} where numRefID = #{numRefID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_UserRefDataUG where numRefID = #{numRefID}")
    int delete(HashMap<String, Object> data);
}
