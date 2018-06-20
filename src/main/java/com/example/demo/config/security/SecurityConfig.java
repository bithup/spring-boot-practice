package com.example.demo.config.security;

import com.example.demo.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 解决新版本中AuthenticationManager不能自动注入的问题
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //实现UserDetailService接口
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //或者使用自定义身份验证组件
        //auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/user", "/auth/admin").permitAll()
                .antMatchers(HttpMethod.POST, "/signout/user", "/logout/admin").permitAll()
                //通过角色和权限限制请求
                //.antMatchers( "/admin/**").hasRole("ADMIN" )                    3
                //.antMatchers( "/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                //swagger begin
                .antMatchers("/trace/users/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                //swagger end
                .antMatchers("/user/auth/*").permitAll()
                //静态资源
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .anyRequest().authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();

        //restful接口不需要formlogin
        //httpSecurity.formLogin()
                //.loginPage("/loginPage.html")
                //.usernameParameter("username")
                //.passwordParameter("password")
                //.defaultSuccessUrl("/swagger-ui.html")
                //.successForwardUrl("")
                //.failureForwardUrl("")
                //.and()
                //.logout().permitAll();

        //JwtLoginFilter继承自UsernamePasswordAuthenticationFilter
        //可不可以用filter取代登录接口的功能呢
        //这里配置jwtLoginFilter不生效，请求不进入，
        //httpSecurity.addFilter(new JwtLoginFilter(authenticationManager()));
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        //httpSecurity.addFilter()
        //httpSecurity.addFilterAt()
        //httpSecurity.addFilterAfter()
        //httpSecurity.headers()

    }

//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/css/**", "/js/**");
//    }



}
