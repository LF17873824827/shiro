package com.zking.shiro.service;

import com.zking.shiro.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface IUserService {


    /**
     * 注册
     * @param record
     * @return
     */
    int insert(User record);


    /**
     * 登录
     * @param
     * @return
     */
    @Transactional(readOnly = true)
    User login(String username);


    @Transactional(readOnly = true)
    Set<String> findRoles(String username);

    //根据username查询他所拥有的权限信息，用于权限判断
    @Transactional(readOnly = true)
    Set<String> findPermissions(String username);
}