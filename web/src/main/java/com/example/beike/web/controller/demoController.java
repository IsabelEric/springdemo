package com.example.beike.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;





@RestController
@RequestMapping("/demodb")
public class demoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
@RequestMapping("/getfloders")
    public List<Map<String,Object>> getfloders(){
    String sql="select * from tb_folder";
    List<Map<String,Object>> floders=jdbcTemplate.queryForList(sql);
    for(Map<String ,Object> folder:floders){
        Set<Map.Entry<String ,Object>>entries=folder.entrySet();
        if(entries!=null){
            Iterator iterator=entries.iterator();
            while (iterator.hasNext()){
                Map.Entry e=(Map.Entry<String, Object>)iterator.next();
                System.out.println(e.getKey()+":"+e.getValue());
            }
        }
    }
    return floders;
}
}
