package com.example.beike;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DemoService {
    @Autowired
    private DemoDao dao;
public int insertgroup(JSONObject jsonObject){
    int insertcount=-1;
    if(jsonObject.getBooleanValue("success")){
        Group group =  new Group( "ggname", "232435436546", new Date(), new Date());
        insertcount=dao.insertGroup(group);
    }


    return insertcount;
}

}
