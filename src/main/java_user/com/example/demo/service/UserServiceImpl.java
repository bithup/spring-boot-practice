package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByTelephone(String tel) {
        return userRepository.findByTelephone(tel);
    }

    @Override
    public User findById(Long id) {
        Optional<User> o = userRepository.findById(id);
        //如果查询不到结果，Optional的get()方法会报异常，使用orElse()方法
        User u = o.orElse(null);
        return u;
    }
}
