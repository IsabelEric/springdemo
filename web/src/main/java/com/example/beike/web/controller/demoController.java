package com.example.beike.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.beike.DemoService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


@RestController
@RequestMapping("/demodb")
public class demoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DemoService demoService;


    /**
     * 查询tb_folder
     *
     * @return json
     */
    @RequestMapping("/getfloders")
    public List<Map<String, Object>> getfloders() {
        String sql = "select * from tb_folder";
        List<Map<String, Object>> floders = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> folder : floders) {
            Set<Map.Entry<String, Object>> entries = folder.entrySet();
            if (entries != null) {
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry e = (Map.Entry<String, Object>) iterator.next();
                    System.out.println(e.getKey() + ":" + e.getValue());
                }
            }
        }
        return floders;
    }

    /**
     * update group实体
     *
     * @return 1
     */
    @RequestMapping("/updategroup")
    public int updatefloders() {

        final String  group_name = "kdkdkk";
        final String creator_uc_id = "932792389892";
        final Date ctime = new Date();
        final Date mtime = new Date();
        final int group_id = 89;

        PreparedStatementSetter setparam = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1, group_name);
                preparedStatement.setObject(2, creator_uc_id);
                preparedStatement.setObject(3, ctime);
                preparedStatement.setObject(4, mtime);
                preparedStatement.setInt(5, group_id);
            }
        };
        String sql = "update tb_group set group_name=?,creator_uc_id=?,ctime=?,mtime=? where group_id=?";
        int updatecount = jdbcTemplate.update(sql, setparam);
        System.out.print("更新条数：" + updatecount);
        return updatecount;
    }


    /**
     * delete tb_group group_id=?一行
     *
     * @return resultset
     */
    @RequestMapping("/deletegroup")
    public int deleteGroup() {
        String sql = "delete from tb_group where group_id=?";
        int id = 55;
        int delectcount = jdbcTemplate.update(sql, id);
        System.out.println("删除id:" + id);
        System.out.println("删除结果：" + delectcount);
        System.out.println("随便看看");
        return delectcount;
    }

    /**
     * insert tb_group 一行
     *
     * @return resultset
     */
    @RequestMapping("/insertgroup")
    public int insertGroup() {
        String sql = "insert into tb_group (group_name,creator_uc_id,ctime,mtime) values (?,?,?,?)";
        int insertcount = jdbcTemplate.update(sql, "ggname", "232435436546", new Date(), new Date());
        return insertcount;
    }


    /**
     * 从权限接口调用与解析
     * 角色已授权的资源列表
     * 接口 url
     * http://auth.api.lianjia.com/auth/soa/dataauth/findGeneralAllDataRuleAuth.do
     * <p>
     * {"method":"postJson","param":{"projectValue":"dtarch","systemSource":"odin","resMarks":[2020276100000000000],"resourceTypeValue":"odindataauth","option":0,"ucid":"1000000026036451"}
     * <p>
     * {"projectValue":"dtarch","systemSource":"odin","resMarks":[2020276100000000000],"resourceTypeValue":"odindataauth","option":0,"ucid":"1000000026036451"}
     */
    @RequestMapping("/role")
    public int getCustomRoleListByProjectId() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = null;
        HttpPost httpPost = new HttpPost();
        int result=-1;
        try {

            String param = "{\"projectValue\":\"dtarch\",\"systemSource\":\"odin\",\"resMarks\":[2020276100000000000],\"resourceTypeValue\":\"odindataauth\",\"option\":0,\"ucid\":\"1000000026036451\"}";
            StringEntity entity = new StringEntity(param, "UTF-8");
            uri = new URIBuilder("http://auth.api.lianjia.com/auth/soa/dataauth/findGeneralAllDataRuleAuth.do").build();
            httpPost.setURI(uri);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态：" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应长度：" + responseEntity.getContentLength());
                System.out.println("实体内容：");
                // String bejson=(new EntityUtils().toString(responseEntity.getContent()));
                //JSONObject json1 = new JSONObject(bejson);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"), 8 * 1024);
                StringBuilder entityStringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    entityStringBuilder.append(line + "\n");
                }
                // 利用从responseEntity中得到的String生成JsonObject
                // JSONObject resultJsonObject = new JSONObject(entityStringBuilder.toString());

                JSONObject jsonObject = JSON.parseObject(entityStringBuilder.toString());
                System.out.println(jsonObject);
                result=demoService.insertgroup(jsonObject);
            }
            if (response != null) {
                response.close();
            }
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}

