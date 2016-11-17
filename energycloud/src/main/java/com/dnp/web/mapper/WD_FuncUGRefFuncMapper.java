package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_FuncUGRefFuncMapper {
    @Delete("delete from WD_FuncUGRefFunc where numFunctionUserGroupID=#{numFunctionUserGroupID}")
    int deleteBynumFunctionUserGroupID(HashMap<String, Object> data);

    @Delete("delete from WD_FuncUGRefFunc where numFunctionID=#{numFunctionID}")
    int deleteBynnumFunctionID(HashMap<String, Object> data);

    @Select("SELECT   r.numRefID,r.numFunctionID,r.numFunctionCode,f.varFunctionCode,r.numFunctionUserGroupID,g.varFunctionUserGroupName from WD_FuncUGRefFunc r,WD_FunctionUserGroup g,WD_Function f WHERE  r.numFunctionID=f.numFunctionID and r.numFunctionUserGroupID=g.numFunctionUserGroupID  ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);


    @Select("SELECT  count(*) from WD_FuncUGRefFunc r,WD_FunctionUserGroup g,WD_Function f WHERE  r.numFunctionID=f.numFunctionID and r.numFunctionUserGroupID=g.numFunctionUserGroupID ")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert into WD_FuncUGRefFunc(numFunctionUserGroupID,numFunctionID) values(#{numFunctionUserGroupID},#{numFunctionID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_FuncUGRefFunc SET numFunctionUserGroupID=#{numFunctionUserGroupID},numFunctionCode=#{numFunctionCode},numFunctionID=#{numFunctionID} where numRefID = #{numRefID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_FuncUGRefFunc where numRefID = #{numRefID}")
    int delete(HashMap<String, Object> data);

}
