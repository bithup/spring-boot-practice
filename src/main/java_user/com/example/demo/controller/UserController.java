package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.base.ResponseData;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
//@PreAuthorize("hasRole('USER')")//这个注解表示必须拥有USER角色才能访问
@Api(value = "UserController",description = "用户信息操作接口", tags = {"a"})
public class UserController extends BaseController {

    @Resource(name = "objectRedisTemplate")
    private RedisTemplate objectRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 添加用户，对用户密码加密
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST,produces = "application/json")
    @ApiOperation(value = "创建新用户",notes = "操作提示")
    public ResponseData addUser(@ApiParam(name = "user",value = "用户信息",required = true) @RequestBody User user) {
        stringRedisTemplate.opsForValue().set("aaaaaa","aaaaa");
        objectRedisTemplate.opsForValue().set("user",user);
        return successData("添加成功", userService.save(user));
    }

    /**
     * 更新用户,更新用户信息是，password肯定是不能有值的，不然就修改了密码
     * 可以将用户登录相关的信息，如绑定的手机号等放在一个表中
     * 将其他的用户信息，如昵称，等放在另一个表中
     * @return
     */
    @PutMapping()
    @ApiOperation("更新用户信息")
    public ResponseData updateUser(@ApiParam(name = "user", value = "用户信息")@RequestBody @Valid User user) {
        return successData("更新成功", userService.save(user));
    }

    /**
     * 查询用户
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("通过用户ID查询用户信息")
    public ResponseData selectUser(
            @ApiParam(value = "用户ID") @PathVariable(name = "id") Long id
    ) {
        return successData("查询成功", userService.findById(id));
    }

    /**
     * 删除用户
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public ResponseData deleteUser(@ApiParam(name = "id", value = "用户ID", allowableValues = "range[1,100]",required = true) @PathVariable long id) {
        return successData("删除成功",id);
    }

    /*
    @ApiImplicitParams
    @ApiImplicitParam
     */
    /*
    @ApiResponses
     */



}
