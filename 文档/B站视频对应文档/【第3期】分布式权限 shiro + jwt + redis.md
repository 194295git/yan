这个分布式权限应用于我的开源项目中
[git项目地址 【分布式工具箱】](https://github.com/194295git/yan)
[sprinboot单体项目升级成springcloud项目](https://blog.csdn.net/qq_21561833/article/details/127348148)
[给为服务添加运维模块 统一管理（第四期）](https://blog.csdn.net/qq_21561833/article/details/127821543)
[微服务数据库模块（第五期）](https://blog.csdn.net/qq_21561833/article/details/131315983)

### 1.分布式权限技术选型
使用的是JWT +Shiro + Redis
关于springSecurity 做分布式可能挺多。但是自己这个项目最开始使用的shiro，而且权限对应的角色，菜单都已经写好了，所以主体采用的shiro。使用jwt 标识每个用户的身份。使用redis 存储每个用户的权限。然后每次都从redis里面读取权限。
### 2.理解jwt
JWT由3部分组成：标头(Header)、有效载荷(Payload)和签名(Signature)。在传输的时候，会将JWT的3部分分别进行Base64编码后用.进行连接形成最终传输的字符串。
在payload部分放上存储用户的id。然后可以从jwt字符串中解析出用户的id。

### 3.目前的登录逻辑

```java
/**
	 * 登录
	 */
	@PostMapping("/sys/login")
	public GenericResponse login(@RequestBody SysLoginForm form)throws IOException {
		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
		CommonUser commonUser = new CommonUser();
		commonUser.setOpenid(user.getOpenid());
		commonUser.setUserId(user.getUserId());
		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return GenericResponse.response(ServiceError.LOGIN_ERROR_USERNAME);

		}
		String token = "";
		try {
			token = JwtTokenUtil.generateToken(commonUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Set<String> permsSet = shiroService.getUserPermissions(user.getUserId());
		Gson gson = new Gson();
		redisTemplate.opsForValue().set(user.getUserId().toString(), gson.toJson(permsSet));
		return GenericResponse.response(ServiceError.NORMAL, token);
	}

```
生成token，将用户id和锁拥有的权限存储进入redis
### 4.shiro所做的配置
#### 1.ShiroConfig
```java
 */
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }



    //============================到目前为止是新加入的东西，具体还需要看效果

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //这里放置自己通行的静态资源
//        filterMap.put("/", "anon");
        filterMap.put("/downloadFile","anon");
        filterMap.put("/parseByName","anon");

        //静态资源不拦截
        filterMap.put("/202108*/**", "anon");
        filterMap.put("/config/**", "anon");
//        是不是必须加上/代表是static 目录下面的
        filterMap.put("/index.html", "anon");
        //================================//
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/shrio/login", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/sys/registByWeb", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/aaa.txt", "anon");
//        filterMap.put("/**", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}

```
securityManager配置里面使用自定的realm
####  4.2 OAuth2Realm

```java
/**
 * 认证
 *
 * @author Mark 1942951600@qq.com
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Set<String> permsSet = user.getPermissions();
        //用户权限列表
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }



    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        Long userId;
        try {
            Claims claims = JwtTokenUtil.parseJWT(accessToken);
            userId =  Long.valueOf(claims.get("userid").toString());
            Gson gson = new Gson();
            String  permsSetString = ( String )redisTemplate.opsForValue().get(userId.toString());
            Set<String> permsSet=  gson.fromJson(permsSetString,Set.class);
            SysUserEntity user = new SysUserEntity();
            user.setUserId(userId);
            user.setPermissions(permsSet);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
            return info;
        } catch (Exception e) {
            e.printStackTrace();

            throw new IncorrectCredentialsException("token失效，请重新登录");

        }
    }
}

```
在这个realm里面有认证和授权的逻辑。认证是先去出token。利用jwt校验token真实性。然后往AuthenticationInfo存入权限信息。然后再授权方法中取出。
这个用户类也添加了Permission字段用来存储。

```java
	private Set<String> permissions;
```
### 4.4 剩下的就是一些其他配置
比如redis 的配置

```java
/**
 * Redis配置
 *
 * @author Mark 1942951600@qq.com
 */
@Configuration
public class RedisConfig {
    @Autowired
    private RedisConnectionFactory factory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public ValueOperations<String, String> valueOperations(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}

```
定义shiro的过滤器

```java
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isBlank(token)){
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

            String json = new Gson().toJson(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));

            httpResponse.getWriter().print(json);

            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }

        return token;
    }


}

```
#### 4.5 还有最重要的需要让别的模块可以引入。

![在这里插入图片描述](https://img-blog.csdnimg.cn/d1aa497634d6409fb576ba6e275f9d73.png)
在META-INFO里面这样配置。
```java
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.rose.permission.config.ShiroConfig,\
  com.rose.permission.oauth2.OAuth2Realm,\
  com.rose.permission.config.RedisConfig

```


