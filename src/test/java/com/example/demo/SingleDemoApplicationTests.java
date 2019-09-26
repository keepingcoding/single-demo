package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SingleDemoApplication.class})
public class SingleDemoApplicationTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryJdbcTemplate;


    @Test
    public void contextLoads() {
        List<Map<String, Object>> maps = this.primaryJdbcTemplate.queryForList("select * from person");
        for (Map<String, Object> map : maps) {
            System.err.println(map);
        }

        System.err.println("==============");

        List<Map<String, Object>> maps1 = this.secondaryJdbcTemplate.queryForList("select * from `order`");
        for (Map<String, Object> map : maps1) {
            System.err.println(map);
        }
    }

}
