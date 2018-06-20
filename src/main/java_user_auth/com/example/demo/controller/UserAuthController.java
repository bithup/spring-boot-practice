package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.ResponseData;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.common.base.CharMatcher;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

import static com.example.demo.util.RegExUtil.matcher;

@RestController
@RequestMapping(value = "/user/auth")
@Api(value = "UserAuthController",description = "用户认证授权接口", tags = {"b"})

public class UserAuthController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录获取token
     * 支持
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "用户登录",notes = "手机号登录，登录成功返回token")
    public ResponseData login(
            @ApiParam(name = "telephone",value = "手机号",required = true)@RequestParam(name = "telephone") String telephone,
            @ApiParam(name = "password",value = "密码",required = true)@RequestParam(name = "password") String password
    ) {
        User user = userService.findByTelephone(telephone);
        if (user == null) {
            return failureData("用户不存在");
        } else {
            //bCryptPasswordEncoder对同一密码每次加密的结果都不同，不能使用MD5校验密码的方式
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                //TODO : 验证成功生成token,将token放到响应头中
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                telephone,
                                password,//这里注意是加密前的密码，不是加密后的密码
                                new ArrayList())
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
                String token = Jwts.builder()
                        .setSubject(auth.getName())
                        .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                        .signWith(SignatureAlgorithm.HS512, "e32dfe0u5h98v85u4l53ffd9d5b7t88u")
                        .compact();
                return successData("登录成功",token);
            } else {
                return failureData("密码错误");
            }
        }
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "注册新用户")
    public ResponseData signUp(@ApiParam(name = "user",value = "用户信息",required = true) @RequestBody User user) {
        System.out.println(bCryptPasswordEncoder.encode(user.getPassword()).length());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return successData("添加成功", userService.save(user));
    }

    /**
     * 忘记密码，修改密码
     */
}
