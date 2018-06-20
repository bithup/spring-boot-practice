package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    /**
     * 添加用户
     * @return 返回保存的记录
     */
    User save(User user);

    /**
     * 根据手机号查询
     * @return 返回user
     */
    User findByTelephone(String tel);

    User findById(Long id);
}
