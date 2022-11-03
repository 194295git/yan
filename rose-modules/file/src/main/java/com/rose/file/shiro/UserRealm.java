//package com.rose.file.shiro;
//
///**
// * @author rose
// * @create 2022/10/19
// */
//
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserRealm extends AuthorizingRealm {
//
//
//    // 授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//
//        //获取用户名
//        String userName = (String) principalCollection.getPrimaryPrincipal();
//        System.out.println("userName=" + userName);
//        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
//        //给用户设置角色
//        authenticationInfo.setRoles(userService.selectRolesByUsername(userName));
//        //给用户设置权限
//        authenticationInfo.setStringPermissions(userService.selectPermissionByUsername(userName));
//        return authenticationInfo;
//    }
//
//
//    // 认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String userName = (String) authenticationToken.getPrincipal();
//        User user = userService.selectByUsername(userName);
//        if (user != null) {
//            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "myRealm");
//            return authenticationInfo;
//        }
//        return null;
//    }
//}
//
