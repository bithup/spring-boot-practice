package com.example.demo.config.security.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工厂类来由领域对象User创建UserDetailsImpl
 */
public class UserDetailsFactory {
    private UserDetailsFactory() {
    }

    public static UserDetailsImpl create(User user) {
        return new UserDetailsImpl(
                user.getId().toString(),
                user.getUsername(),
                user.getPassword(),
                user.getTelephone(),
                user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    /**
     * 角色转换
     * @param authoritie
     * @return
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authoritie) {

        List<String> authorities = new ArrayList<String>();
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
