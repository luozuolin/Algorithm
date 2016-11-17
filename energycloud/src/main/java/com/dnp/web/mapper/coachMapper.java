package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/8/16.
 */
@Component
public interface coachMapper {
    @Select("SELECT  * from coach")
    List<HashMap> getAll();
}
