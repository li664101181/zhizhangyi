package com.user.service;

import com.user.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李昶
 * @since 2022-06-05
 */
public interface TUserService extends IService<TUser> {
    TUser selectByUsername(String username);

    TUser addUser(String username, String password);

    Boolean change(String s, String username, String password);

    List<TUser> findUserBytime(String starttime, String endtime) throws ParseException;
}
