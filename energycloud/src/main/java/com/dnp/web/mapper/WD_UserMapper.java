package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/6.
 */
@Component
public interface WD_UserMapper {
    @Select("SELECT  * from WD_User where varLoginName=#{varLoginName} and varPassword=#{varPassword}")
    List<HashMap> getUserByLoginNameAndPassword(HashMap<String, Object> data);

    @Select("select DISTINCT d.*  from WD_User a ,WD_UserRefFuncUG b, WD_FunctionUserGroup c, WD_Function d,WD_FuncUGRefFunc e\n" +
            "where a.numUserID=b.numUserID and b.numFunctionUserGroupID=c.numFunctionUserGroupID and c.numFunctionUserGroupID=e.numFunctionUserGroupID\n" +
            "and e.numFunctionID=d.numFunctionID and a.numUserID=#{numUserID} union  SELECT  *    from WD_Function WHERE  varFunctionURL='' ")
    List<HashMap> getFunctionByUserID(HashMap<String, Object> data);
    @Select({"<script>",
            "SELECT   u.numUserID,u.varUserName,u.varLoginName,u.datCreate,' ' varPassword,u.numPositionID,u.numOrganizeID,p.varPositionName,u.isadmin ,o.varOrganizeName  from WD_User u left join  WD_Organize o  on o.numOrganizeID=u.numOrganizeID left join WD_Position p on u.numPositionID=p.numPositionID where 1=1" +
            "<if test='varUserName!= null and varUserName!=\"\"'>",
            " and  varUserName like '%${varUserName}%' ",
            "</if>",
            "<if test='isadmin==false.toString()'>\n" +
                    "            AND u.numOrganizeID in(${numOrganizeIDs})\n" +
                    "    </if>" +
            " ${sql} </script>"})
    List<HashMap> getAll(HashMap<String, Object> data);



    @Select({"<script>",
            "SELECT  count(*) from WD_User u  left join WD_Position p on u.numPositionID=p.numPositionID where 1=1 " +
             "<if test='varUserName!= null and varUserName!=\"\"'>",
                " and  varUserName like '%${varUserName}%' ",
            "</if>",
            "<if test='isadmin==false.toString()'>\n" +
                    "            AND u.numOrganizeID in(${numOrganizeIDs})\n" +
                    "    </if>" +
            " </script>"})
    int getAllCount(HashMap<String, Object> data);

    @Insert("INSERT INTO WD_User (varUserName,varLoginName,datCreate,numPositionID,varPassword) VALUES(#{varUserName},#{varLoginName},#{datCreate},#{numPositionID},#{varPassword}) ")
    @SelectKey(statement="SELECT max(numUserID) numUserID from WD_User", keyProperty="numUserID",keyColumn = "numUserID",  before=false, resultType=int.class)
    int insert(HashMap<String, Object> data);

    @Update("UPDATE WD_User SET varUserName=#{varUserName},varLoginName=#{varLoginName},datCreate=#{datCreate},numPositionID=#{numPositionID},isadmin=#{isadmin}  where numUserID = #{numUserID}")
    int update(HashMap<String, Object> data);

    @Update("UPDATE WD_User SET varPassword=#{varPassword}  where numUserID = #{numUserID}")
    int updatePassword(HashMap<String, Object> data);
    @Delete("delete from  WD_User where numUserID = #{numUserID}")
    int delete(HashMap<String, Object> data);
    @Delete("DELETE FROM WD_UserRefDataUG WHERE numUserID=  #{numUserID}")
    int deleteDataUserGroup(HashMap<String, Object> data);//删除用户组
    @Delete("DELETE FROM WD_UserRefFuncUG WHERE numUserID= #{numUserID}")
    int deleteUserFunctionGroup(HashMap<String, Object> data);//删除用户组
    @Delete("DELETE FROM WD_UserRefOrganize WHERE numUserID= #{numUserID}")
    int deleteUserOrganize(HashMap<String, Object> data);//删除组织机构



    @Select("SELECT   d.numDataUserGroupID,d.varDataUserGroupName,IFNULL(r.numUserID,-1) selected  from WD_DataUserGroup d LEFT join WD_UserRefDataUG r on d.numDataUserGroupID=r.numDataUserGroupID and r.numUserID=#{numUserID};")
    List<HashMap> getDataUGBynumUserID(HashMap<String, Object> data);

    @Select("SELECT   f.numFunctionUserGroupID,f.varFunctionUserGroupName,IFNULL(r.numUserID,-1)  selected  from WD_FunctionUserGroup f LEFT join WD_UserRefFuncUG r on f.numFunctionUserGroupID=r.numFunctionUserGroupID and r.numUserID=#{numUserID};")
    List<HashMap> getFuncUGBynumUserID(HashMap<String, Object> data);

    @Select("SELECT   u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numPositionID,p.varPositionName from WD_User u  left join WD_Position p on u.numPositionID=p.numPositionID")
    List<HashMap> listAll();
    @Select("SELECT o.*,u.numUserID from WD_Organize o LEFT  join WD_User  u on o.numOrganizeID=u.numOrganizeID and  u.numUserID=#{numUserID};")
    List<HashMap> getOrganizeIDBynumUserID(HashMap<String, Object> data);


    @Update("UPDATE WD_User SET numOrganizeID=#{numOrganizeID}  where numUserID = #{numUserID}")
    int updateOrganize(HashMap<String, Object> data);//删除组织机构



    @Select("SELECT u.numOrganizeID\n" +
            "FROM WD_User u\n" +
            "WHERE u.numUserID=#{userId}")
    int getUserOrganizeID(HashMap<String, Object> data);

    /*通过父回路ID查询出所有的子部门*/
    @Select("SELECT o.numOrganizeID\n" +
            "FROM WD_Organize o\n" +
            "WHERE o.numPOrganizeID=46\n")
    int getUserPOrganizeID(HashMap<String, Object> data);
}
