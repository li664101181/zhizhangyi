package com.user.service.impl;

import com.user.entity.TUser;
import com.user.mapper.TUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created By lichang on 5/31
 */
@Service
public class CustomUserServiceImpl implements UserDetailsService {
    @Resource
    public TUserMapper tUserMapper;


    @Override
    public UserDetails loadUserByUsername(String s)throws UsernameNotFoundException {
        if (s == null){  //如果用户名不存在，就抛出下面的信息
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //用户存在的话就返回hr对象
        TUser tUser = tUserMapper.loadUserByUsername(s);
        return tUser;
    }
}
