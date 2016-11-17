package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_UserRefFuncUGMapper {
    @Delete("delete from WD_UserRefFuncUG where numFunctionUserGroupID=#{numFunctionUserGroupID}")
    int deleteBynumFunctionUserGroupID(HashMap<String, Object> data);

    @Delete("delete from WD_UserRefFuncUG where numUserID=#{numUserID}")
    int deleteBynumUserID(HashMap<String, Object> data);




    @Select("SELECT   r.numRefID,r.numUserID,u.varUserName,r.numFunctionUserGroupID,f.varFunctionUserGroupName from WD_UserRefFuncUG  r, WD_User u, WD_FunctionUserGroup f where r.numFunctionUserGroupID=f.numFunctionUserGroupID and r.numUserID=u.numUserID   ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT count(*) from WD_UserRefFuncUG  r, WD_User u, WD_FunctionUserGroup f where r.numFunctionUserGroupID=f.numFunctionUserGroupID and r.numUserID=u.numUserID  ")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert into WD_UserRefFuncUG(numFunctionUserGroupID,numUserID) values(#{numFunctionUserGroupID},#{numUserID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_UserRefFuncUG SET numFunctionUserGroupID=#{numFunctionUserGroupID},numUserID=#{numUserID} where numRefID = #{numRefID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_FuncUGRefFunc where numRefID = #{numRefID}")
    int delete(HashMap<String, Object> data);
}
