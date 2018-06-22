### spring security总结：
org.springframework.security.core.userdetails包中的
- interface UserDetails
- class User implements UserDetails, CredentialsContainer
- interface UserDetailsService

1. 配置获取用户信息的方式，和密码加密的方式
通过实现UserDetailsService接口中的loadUserByUsername()方法  
将数据库中查询来的用户信息（通过用户名、手机号等查询），封装到上述的User类中  
然后将UserDetailsServiceImpl和密码加密的方式（BCryptPasswordEncoder）  
设置到AuthenticationManagerBuilder中

2. 注册时密码用BCryptPasswordEncoder加密

3. 登录时，验证输入密码和数据库中加密的密码是否相同  
验证成功后，使用用户名（可以是手机号或其他）和密码（未加密的）生成一个Authentication对象  
将这个Authentication对象放到SecurityContextHolder.getContext().setAuthentication中  
用这个Authentication对象和Jwts.builder()方法生成一个JWT，并将这个JWT反回给前台

4. 前台拿到token，请求受限资源，首先经过自定义Filter
解析token，并生成一个UsernamePasswordAuthenticationToken对象
将这个对象放到SecurityContextHolder.getContext().setAuthentication中
自定义的Filter在过滤器链中的位置在
UsernamePasswordAuthenticationFilter之前，过滤器链继续执行
UsernamePasswordAuthenticationFilter对UsernamePasswordAuthenticationToken
进行验证，这样完成一个完整的验证过程

### 问题
- token的刷新和过期

- 为什么spring security中禁用的session，响应信息中还是有JSESSIONID











http://lxgandlz.cn/category/spring/spring-boot

当登录成功，获取到JWT之后，在查询用户信息时，使用同一个token，
可以查询出不同ID的用户信息，这是一个漏洞
参考
https://www.jianshu.com/p/6307c89fe3fa
完善