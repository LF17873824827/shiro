package com.zking.shiro.service.impl;

import com.zking.shiro.mapper.UserMapper;
import com.zking.shiro.model.User;
import com.zking.shiro.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User login(String username) {
        return userMapper.login(username);
    }


    @Override
    public Set<String> findRoles(String username) {
        return userMapper.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userMapper.findPermissions(username);
    }
}
