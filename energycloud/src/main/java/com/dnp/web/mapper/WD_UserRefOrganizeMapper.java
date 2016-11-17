package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/26.
 */
@Component
public interface WD_UserRefOrganizeMapper {
    @Select("SELECT o.numOrganizeID, o.varOrganizeName,o.numPOrganizeID,IFNULL(u.numUserID,-1)  selected  from WD_Organize  o LEFT join WD_UserRefOrganize  u on o.numOrganizeID=u.numOrganizeID and u.numUserID=#{numUserID};")
    List<HashMap> getOrganizeBynumUserID(HashMap<String, Object> data);

    @Delete("delete from WD_UserRefOrganize where numUserID=#{numUserID}")
    int deleteBynumUserID(HashMap<String, Object> data);
    @Insert("insert into WD_UserRefOrganize(numOrganizeID,numUserID) values(#{numOrganizeID},#{numUserID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);
}
