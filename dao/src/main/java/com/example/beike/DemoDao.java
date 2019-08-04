package com.example.beike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public class DemoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertGroup(Group group){
        int insertconunt=-1;
        String sql = "insert into tb_group (group_name,creator_uc_id,ctime,mtime) values (?,?,?,?)";
        insertconunt=jdbcTemplate.update(sql,group.getGroup_name(),group.getCreator_uc_id(),group.getCtime(),group.getMtime());
        return insertconunt;
    }
}
