package com.dnp.web.service;

import com.dnp.web.pojo.player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/6/16.
 */
@Component
public class playerService {
    @Autowired
    com.dnp.web.mapper.playerMapper playerMapper;
    public List<player> getAll(HashMap<String,Object> data)
    {
        return  playerMapper.getAll(data);
    }
   /* public int getAllCount()
    {
        return  playerMapper.getAllCount();
    }*/
    public int getAllCount(HashMap<String,Object> data)
    {
        return  playerMapper.getAllCount(data);
    }
    public int insert(player p)
    {
        return  playerMapper.insert(p);
    }
    public int update(player p)
    {
        return  playerMapper.update(p);
    }
    public int delete(player p)
    {
        return  playerMapper.delete(p);
    }
}
