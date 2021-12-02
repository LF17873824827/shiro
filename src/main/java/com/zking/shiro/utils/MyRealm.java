package com.zking.shiro.utils;

import com.zking.shiro.model.User;
import com.zking.shiro.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Resource
    private IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取到用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
       //根据username查询该用户的所有角色
        Set<String> roles = userService.findRoles(username);
        //根据username查询他所拥有的权限信息
        Set<String> permissions = userService.findPermissions(username);

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);


        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = authenticationToken.getPrincipal().toString();
        //获取密码
        String password = authenticationToken.getCredentials().toString();

        //通过用户名查询用户信息
        User u = userService.login(username);

        if(null==u){
            throw new RuntimeException("用户名不存在");
        }

        //保存用户认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                u.getUsername(),
                u.getPassword(),
                ByteSource.Util.bytes(u.getSalt()),
                this.getName()
        );

        return simpleAuthenticationInfo;
    }
}
