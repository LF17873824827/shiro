package com.zking.shiro.service.impl;

import com.zking.shiro.model.User;
import com.zking.shiro.service.IUserService;
import com.zking.shiro.utils.PasswordHelper;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceImplTest extends BaseTestCase{

    @Resource
    private IUserService userService;
    private User user;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        user=new User();
    }

    @Test
    public void insert() {
        user.setUsername("zs");
        String salt = PasswordHelper.createSalt();
        String pwd = PasswordHelper.createCredentials("888888", salt);
        user.setPassword(pwd);
        user.setSalt(salt);
        userService.insert(user);
    }

    @Test
    public void findRoles() {
        Set<String> zs = userService.findRoles("zs");
        System.out.println(zs);
    }


    @Test
    public void findPermissions() {
        Set<String> zs = userService.findPermissions("zhangsan");
        for (String z : zs) {
            System.out.println(z);
        }
    }
}