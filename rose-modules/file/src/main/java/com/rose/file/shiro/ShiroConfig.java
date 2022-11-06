//package com.rose.file.shiro;
//
///**
// * @author rose
// * @create 2022/10/19
// */
//import com.rose.MyCacheManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//
//
//    // ShiroFilterFactoryBean
//    @Bean(name = "shiroFilterFactoryBean")
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        // 添加shiro的内置过滤器
//        /*
//           anon: 无需认证就能访问
//           authc: 必须认证了才能访问
//           UserController: 必须拥有 记住我 功能才能访问
//           perms: 拥有某个资源权限才能访问
//           role: 拥有某个角色权限才能访问
//         */
//        // 拦截
//        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        // 授权
//        // filterMap.put("/UserController/add", "perms[UserController:add]");
//        filterMap.put("/user/testFunc", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//
//        // 设置未授权页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unAuth");
//        // 设置登录的请求
//        // shiroFilterFactoryBean.setLoginUrl("/user/index");
//
//        return shiroFilterFactoryBean;
//    }
//
//    // DefaultWebSecurityManager
//    // @Qualifier中可以直接是bean的方法名，也可以给bean设置一个name，比如@Bean(name="myRealm")，在@Qulifier中就可以通过name来获取这个bean
//    @Bean(name = "SecurityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm,
//                                                                  @Qualifier("myDefaultWebSessionManager") DefaultWebSessionManager defaultWebSessionManager,
//                                                                  @Qualifier("myCacheManager") MyCacheManager myCacheManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 关联UserRealm
//        securityManager.setRealm(userRealm);
//        securityManager.setCacheManager(myCacheManager);
//        securityManager.setSessionManager(defaultWebSessionManager);
//
//        return securityManager;
//    }
//    /**
//     * 下面DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor必须定义，
//     * 否则不能使用@RequiresRoles和@RequiresPermissions
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
//        defaultAAP.setProxyTargetClass(true);
//        return defaultAAP;
//    }
//
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    /**
//     * 设置自定义session管理器
//     */
//    @Bean
//    public DefaultWebSessionManager myDefaultWebSessionManager(SimpleCookie simpleCookie) {
//        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        defaultWebSessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
//        defaultWebSessionManager.setSessionIdCookie(simpleCookie);
//        return defaultWebSessionManager;
//    }
//
//    @Bean
//    public SimpleCookie simpleCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("myCookie");
//        simpleCookie.setPath("/");
//        simpleCookie.setMaxAge(30);
//        return simpleCookie;
//    }
//
//}
//
