package com.rainbowsea.imperial.court.test;


import com.rainbowsea.imperial.court.entity.User;
import com.rainbowsea.imperial.court.entity.UserExample;
import com.rainbowsea.imperial.court.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// Spring 和 Junit5整合：注意：是 Junit5 不是 4
@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring-persist.xml"})
public class ImperialCourtTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testUserMapper() {
        List<User> users = userMapper.selectByExample(new UserExample());

        for (User user : users) {
            System.out.println("user = " + user);
        }
    }








    @Autowired  // DataSource 在 Mybatis 当中已经交给 Spring IOC 容器管理了
    private DataSource dataSource;


    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection: " + connection);
    }

}
