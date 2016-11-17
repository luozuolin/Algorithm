package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/6.
 */
@Component
public interface FunctionUserGroupInfosMapper {
    @Select("select * from WD_FunctionUserGroup  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("select * from WD_FunctionUserGroup ")
    List<HashMap> listAll();
    @Select("select count(*) from WD_FunctionUserGroup  ")
    int getAllCount(HashMap<String, Object> data);

    @Insert("INSERT INTO WD_FunctionUserGroup (varFunctionUserGroupName) VALUES(#{varFunctionUserGroupName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numFunctionUserGroupID", keyColumn = "numFunctionUserGroupID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_FunctionUserGroup SET varFunctionUserGroupName=#{varFunctionUserGroupName}  where numFunctionUserGroupID = #{numFunctionUserGroupID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_FunctionUserGroup where numFunctionUserGroupID = #{numFunctionUserGroupID}")
    int delete(HashMap<String, Object> data);
    //查询出所有的用户权限
//SELECT    f.*,u.numFunctionID numFunctionIDselected from  WD_Function f LEFT   join  WD_FuncUGRefFunc u on f.numFunctionID=u.numFunctionID and u.numFunctionUserGroupID=17 ORDER BY numPFunctionID;
//numFunctionIDselected为null时表示没有选择，为具体数字时表示选中
    @Select("SELECT    f.*,IFNULL(u.numFunctionID,-1) as  selected from  WD_Function f LEFT   join  WD_FuncUGRefFunc u on f.numFunctionID=u.numFunctionID and u.numFunctionUserGroupID=#{numFunctionUserGroupID} ORDER BY numPFunctionID")
    List<HashMap> getFunctionBynumFunctionUserGroupID(HashMap<String, Object> data);

    @Select("SELECT DISTINCT u.numUserID,varUserName,IFNULL(r.numFunctionUserGroupID,-1)  as  selected   from WD_User u left join WD_UserRefFuncUG  r on u.numUserID=r.numUserID and r.numFunctionUserGroupID=#{numFunctionUserGroupID}")
    List<HashMap> getUsersBynumFunctionUserGroupID(HashMap<String, Object> data);
}
