package com.dnp.web.mapper;

import com.dnp.web.pojo.player;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/6/16.
 */
@Component
public interface playerMapper {

    @Select({"<script>",
            "SELECT   p.*,s.`name` sportname,c.`name` coachname,o.`name` orgname from player p,sport s,coach c,org o WHERE p.sport_id=s.id and p.org_id=o.id and p.coach_id=c.id ",
            "<if test='name!=\"\"'>",
            " and p.name  like CONCAT('%',#{name},'%' ) ",
            "</if>",
            "<if test='!(sportid==\"\" || sportid==\"-1\")'>",
            " and p.sport_id =#{sportid} ",
            "</if>",
            "${sql} </script>"})
    List<player> getAll(HashMap<String, Object> data);
    @Select({"<script>",
            "SELECT  count(*) from player p,sport s,coach c,org o WHERE p.sport_id=s.id and p.org_id=o.id and p.coach_id=c.id ",
            "<if test='name!=\"\"'>",
            " and p.name  like CONCAT('%',#{name},'%' ) ",
            "</if>",
            "<if test='!(sportid==\"\" || sportid==\"-1\")'>",
            " and p.sport_id =#{sportid} ",
            "</if>",
            "</script>"})
    int getAllCount(HashMap<String, Object> data);
    @Insert("INSERT INTO player (name,sex,born,sport_id,coach_id,age_group,org_id,isimportant) VALUES(#{name},#{sex},#{born},#{sportid},#{coachid},#{agegroup},#{orgid},#{isimportant}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(player p);

    @Update("UPDATE player SET name=#{name},sex=#{sex},born=#{born},sport_id=#{sportid},coach_id=#{coachid},org_id=#{orgid},age_group=#{agegroup} ,isimportant=#{isimportant} where id = #{id}")
    int update(player p);
    @Delete("update player set status=0 WHERE id=#{id}")
    int delete(player p);

}
