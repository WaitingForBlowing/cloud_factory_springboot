package com.moozlee.cloud_factory.config.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moozlee.cloud_factory.mapper.IUserMapper;
import com.moozlee.cloud_factory.po.JWTToken;
import com.moozlee.cloud_factory.po.Role;
import com.moozlee.cloud_factory.po.User;
import com.moozlee.cloud_factory.utils.TokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomRealm extends AuthorizingRealm {

    private final IUserMapper userMapper;

    public CustomRealm(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户授权");
        String username= TokenUtil.getAccount(principalCollection.toString());
        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
        assert username != null;
        Role r = userMapper.findRoleByUsername(username);
        System.out.println(r);
        Set<String> role=new HashSet<>();
        role.add(r.getIdentifier());
        info.setRoles(role);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证");
        String token= (String) authenticationToken.getCredentials();
        String username = TokenUtil.getAccount(token);
        System.out.println(username);

        QueryWrapper<User> query= new QueryWrapper<User>();
        query.eq("account", username);
        User user = userMapper.selectOne(query);

        if (user == null){
            throw new AuthenticationException("认证失败！");
        }

        System.out.println("认证成功！");
        return new SimpleAuthenticationInfo(token, token,"MyRealm");
    }
}
