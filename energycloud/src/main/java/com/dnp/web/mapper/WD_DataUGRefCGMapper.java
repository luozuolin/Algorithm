package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by luozl on 2016/9/12.
 */
@Component
public interface WD_DataUGRefCGMapper {



    @Delete("delete from WD_DataUGRefCG where numDataUserGroupID=#{numDataUserGroupID}")
    int deleteBynumDataUserGroupId(HashMap<String, Object> data);

    @Insert("insert into WD_DataUGRefCG(numDataUserGroupID,numCircuitGroupID) values(#{numDataUserGroupID},#{numCircuitGroupID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);
}
