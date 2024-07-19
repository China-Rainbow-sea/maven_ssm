package com.rainbowsea.imperial.court.service.impl;

import com.rainbowsea.imperial.court.entity.User;
import com.rainbowsea.imperial.court.entity.UserExample;
import com.rainbowsea.imperial.court.mapper.UserMapper;
import com.rainbowsea.imperial.court.service.api.UserService;
import com.rainbowsea.imperial.court.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)  // 事务管理
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByLogin(String loginAccount, String loginPassword) {

        // 1、密码加密
        String encodeLoginPassword = MD5Util.encode(loginPassword);

        // 2、通过 QBC 查询方式封装查询条件
        UserExample example = new UserExample();

        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(loginAccount).andPasswordEqualTo(loginPassword);

        List<User> users = userMapper.selectByExample(example);
        if (users != null && users.size() > 0) {

            // 3、返回查询结果
            return users.get(0);
        }

        return null;
    }
}
