package com.zking.shiro.utils;

import com.zking.shiro.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class PhoneRealm extends AuthorizingRealm {

    @Resource
    IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       PhoneToken token=null;
       //如果是PhoneToken,则强转，获取Phone,否则不处理
        if(authenticationToken instanceof PhoneToken){
            token=(PhoneToken) authenticationToken;
        }else{
            return null;
        }
        String phone=(String)token.getPrincipal();



        return null;
    }

    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof PhoneToken;
    }
}
