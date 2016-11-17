package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/9.
 */
@Component
public interface WD_FunctionMapper {
    @Select({"<script>",
            "SELECT   f1.numFunctionID,f1.varFunctionCode,f1.varFunctionURL,f1.numPFunctionID,f2.varFunctionCode  varPFunctionCode from WD_Function f1 LEFT   join WD_Function  f2 on f1.numPFunctionID=f2.numFunctionID  where 1=1 " +
            "<if test='varFunctionCode!= null and varFunctionCode!=\"\"'>",
            " and   f1.varFunctionCode like '%${varFunctionCode}%' ",
            "</if>",
            " ${sql}",
            " </script>"})
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select({"<script>",
            "SELECT   count(*) from WD_Function f1 LEFT   join WD_Function  f2 on f1.numPFunctionID=f2.numFunctionID  where 1=1 " +
            "<if test='varFunctionCode!= null and varFunctionCode!=\"\"'>",
             " and   f1.varFunctionCode like '%${varFunctionCode}%' ",
            "</if>",
            " </script>"})
    int getAllCount(HashMap<String, Object> data);


    @Insert("INSERT INTO WD_Function (varFunctionCode,varFunctionURL,numPFunctionID) VALUES(#{varFunctionCode},#{varFunctionURL},#{numPFunctionID}) ")
    @Options(useGeneratedKeys = true, keyProperty = "numFunctionID", keyColumn = "numFunctionID")
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_Function SET varFunctionCode=#{varFunctionCode},varFunctionURL=#{varFunctionURL},numPFunctionID=#{numPFunctionID}  where numFunctionID = #{numFunctionID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from  WD_Function where numFunctionID = #{numFunctionID}")
    int delete(HashMap<String, Object> data);

    @Select("SELECT   * from   WD_Function")
    List<HashMap> listAll();


    @Select("SELECT   g.*, IFNULL(r.numFunctionUserGroupID,-1)  selected  from WD_FunctionUserGroup g left join WD_FuncUGRefFunc r on g.numFunctionUserGroupID=r.numFunctionUserGroupID and r.numFunctionID=#{numFunctionID};")
    List<HashMap> getFunctionUserGroupBynumFunctionID(HashMap<String, Object> data);




}
