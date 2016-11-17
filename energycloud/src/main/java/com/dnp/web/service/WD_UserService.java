package com.dnp.web.service;

import com.dnp.util.JsonUtil;
import com.dnp.util.MD5Util;
import com.dnp.web.mapper.WD_UserMapper;
import com.dnp.web.mapper.WD_UserRefFuncUGMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by luozl on 2016/9/6.
 */
@Component
public class WD_UserService {
    @Autowired
    WD_UserMapper wd_userMapper;
    @Autowired
    WD_UserRefFuncUGMapper wd_userRefFuncUGMapper;
    public List<HashMap> getUserByLoginNameAndPassword(HashMap<String,Object> data)
    {
        return  wd_userMapper.getUserByLoginNameAndPassword(data);
    }
    public List<HashMap> getFunctionByUserID(HashMap<String,Object> data)
    {
        return  wd_userMapper.getFunctionByUserID(data);
    }

    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_userMapper.getAll(data);
    }
    public int getAllCount(HashMap<String, Object> data) {
        return wd_userMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            //默认赋劫色
            data.put("numFunctionUserGroupID","36");
            //加密
            data.put("varPassword", MD5Util.getMD5String(data.get("varPassword").toString()));
            wd_userMapper.insert(data);
          //  data.put("numUserID",wd_userMapper.insert(data));
            //添加用户是默认赋角色：enco角色（前台展示）
        return     wd_userRefFuncUGMapper.insert(data);
        } else if (updateMethod.equals("update")) {
          //   wd_userMapper.updatePassword(data);
            if(!data.get("varPassword").equals("")) {
                data.put("varPassword", MD5Util.getMD5String(data.get("varPassword").toString()));
                wd_userMapper.updatePassword(data);
            }
            return wd_userMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            //删除关联表中的数据
            wd_userMapper.deleteDataUserGroup(data); //删除用户组
            wd_userMapper.deleteUserFunctionGroup(data);//删除用户职能
            wd_userMapper.deleteUserOrganize(data);//删除用户组织机构
            return wd_userMapper.delete(data);
        }
        return 1;
    }
public int updateOrganize(HashMap<String, Object> data)
{
   return wd_userMapper.updateOrganize(data); //删除用户组
}
    public List<HashMap> getDataUGBynumUserID(HashMap<String, Object> data)
    {
        return wd_userMapper.getDataUGBynumUserID(data);
    }
    public List<HashMap> getFuncUGBynumUserID(HashMap<String, Object> data)
    {
        return wd_userMapper.getFuncUGBynumUserID(data);
    }
    public List<HashMap> listAll()
    {
        return wd_userMapper.listAll();
    }
    //按照numUserID获取用户有权限的部门以及子部门
    public  String getOrganizeIDBynumUserID(HashMap<String, Object> data)
    {
        List<HashMap> o=wd_userMapper.getOrganizeIDBynumUserID(data);
        ArrayList<String> list0 = new ArrayList<String>();
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> idlist = new ArrayList<String>();
        //获取用户所在部门以及所在部门子部门的id的集合
        for(int j=0;j<o.size();j++)
        {
            if(o.get(j).get("numUserID")!=null)
            {
                list1.add(o.get(j).get("numOrganizeID").toString());
                idlist.add(o.get(j).get("numOrganizeID").toString());
            }

        }
        list0= (ArrayList<String>) list1.clone();
        list1.clear();
        while (list0.size()>0)
        {
            for(int i=0;i<list0.size();i++)
            {
                for(int j=0;j<o.size();j++)
                {
                    if(list0.get(i).equals(o.get(j).get("numPOrganizeID").toString()))
                    {
                        list1.add(o.get(j).get("numOrganizeID").toString());
                        idlist.add(o.get(j).get("numOrganizeID").toString());
                    }
                }
            }
            list0=(ArrayList<String>) list1.clone();
            list1.clear();
        }
        String ret="";
        for(int i=0;i<idlist.size();i++)
        {
            ret+=ret.equals("")?idlist.get(i):","+idlist.get(i);
        }
        return ret;
    }
}
